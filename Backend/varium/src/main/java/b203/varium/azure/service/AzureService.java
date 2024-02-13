package b203.varium.azure.service;

//@Service
//@RequiredArgsConstructor
public class AzureService {

//    private final WebClient webClient;
//    private final SimpMessagingTemplate messagingTemplate;
//    private final Set<String> processingSet = Collections.newSetFromMap(new ConcurrentHashMap<>());
//    private final Executor executor = Executors.newFixedThreadPool(10); // 동시에 처리할 작업 수에 따라 조정
//
//    @Value("${AI_NGROK_URL}")
//    private String AI_NGROK_URL;
//
//    public void addTextToSet(String text) {
//        boolean added = processingSet.add(text);
//        if (added) {
//            CompletableFuture.runAsync(() -> processText(text), executor);
//        }
//    }
//
//    private void processText(String text) {
//        callAIAPIToProcessText(text)
//                .subscribe(processedText -> {
//                    // 결과를 프론트엔드로 전송
//                    sendResultToFrontend(processedText);
//                    // 처리가 완료된 후에는 Set에서 해당 텍스트를 제거
//                    processingSet.remove(text);
//                });
//    }
//
//    private Mono<String> callAIAPIToProcessText(String text) {
//        return webClient.post()
//                .uri(AI_NGROK_URL)
//                .bodyValue(Map.of("prompt", text))
//                .retrieve()
//                .bodyToMono(String.class)
//                .onErrorReturn("Error processing text with AI API.");
//    }
//
//    private void sendResultToFrontend(String processedText) {
//        messagingTemplate.convertAndSend("/AzureWave/processedText", processedText);
//    }

}
