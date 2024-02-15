package b203.varium.azure.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.Instant;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
@RequiredArgsConstructor
public class AzureService {

    private final WebClient webClient;
    private final SimpMessagingTemplate messagingTemplate;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor(); // 직렬 처리를 위한 단일 스레드 풀
    private final ConcurrentLinkedQueue<String> processingQueue = new ConcurrentLinkedQueue<>();
    private final Map<Integer, String> processedResults = new ConcurrentHashMap<>();
    private final AtomicInteger orderCounter = new AtomicInteger(0);
    private final AtomicInteger processedCounter = new AtomicInteger(0);
    private final Map<Integer, Instant> requestTimestamps = new ConcurrentHashMap<>();
    @Value("${AI_NGROK_URL}")
    private String AI_NGROK_URL;

    public void addTextToProcess(String text) {
        processingQueue.add(text); // 텍스트를 큐에 추가
        processNextText(); // 다음 텍스트 처리 시작
    }

    private synchronized void processNextText() {
        if (!processingQueue.isEmpty() && processedCounter.get() == orderCounter.get()) {
            String text = processingQueue.poll(); // 큐에서 하나의 텍스트를 꺼냄
            int order = orderCounter.getAndIncrement(); // 작업에 순서 번호 할당
            Instant timestamp = Instant.now(); // 현재 시간 저장
            requestTimestamps.put(order, timestamp); // 요청 시간 저장
            executorService.submit(() -> processText(text, order));
        }
    }

    private void processText(String text, int order) {
        log.info("Processing text: {}", text);
        callAIAPIToProcessText(text)
                .doOnNext(processedText -> {
                    processedResults.put(order, processedText);
                    while (processedResults.containsKey(processedCounter.get())) {
                        int currentOrder = processedCounter.getAndIncrement();
                        String result = processedResults.remove(currentOrder);
                        if (result != null) {
                            sendResultToFrontend(result, currentOrder);
                        }
                    }
                    processNextText(); // 다음 텍스트 처리
                })
                .doOnError(e -> log.error("Error processing text: {}", text, e))
                .subscribe();
    }

    private Mono<String> callAIAPIToProcessText(String text) {
        return webClient.post()
                .uri(AI_NGROK_URL)
                .bodyValue(Map.of("prompt", text))
                .retrieve()
                .bodyToMono(String.class);
    }

    private void sendResultToFrontend(String processedText, int order) {
        log.info("Original processed text: {}", processedText);
        try {
            JSONObject json = new JSONObject(processedText);
            String answer = json.getString("answer");
            // 불필요한 개행, 공백, </s> 태그 제거
            answer = answer.replaceAll("^\\s+|\\s+$|</s>", "").trim();

            // "Result: " 다음에 오는 텍스트가 있으면 그 부분만 추출
            int resultIndex = answer.indexOf("Result: \\");
            if (resultIndex != -1) {
                answer = answer.substring(resultIndex + "Result: \\".length());
            }

            // "Translation: " 다음에 오는 텍스트가 있으면 그 부분만 추출
            int translationIndex = answer.indexOf("Translation: ");
            if (translationIndex != -1) {
                answer = answer.substring(translationIndex + "Translation: ".length());
            }

            // "(" 이전에 오는 텍스트가 있으면 그 부분까지만 추출
            int bracketIndex = answer.indexOf(" (");
            if (bracketIndex != -1) {
                answer = answer.substring(0, bracketIndex);
            }

            // 결과 JSON 객체를 만듭니다.
            JSONObject resultJson = new JSONObject();
            resultJson.put("answer", answer);
            resultJson.put("requestTime", requestTimestamps.get(order).toString());

            log.info("Cleaned processed text: {}", resultJson);
            messagingTemplate.convertAndSend("/AzureWave/processedText", resultJson.toString());
        } catch (JSONException e) {
            log.error("Error parsing JSON response: {}", e.getMessage());
            messagingTemplate.convertAndSend("/AzureWave/processedText", "Error processing response.");
        } finally {
            requestTimestamps.remove(order);
        }
    }
}
