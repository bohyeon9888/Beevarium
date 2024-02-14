import { ref, watch } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import { useAuthStore } from "./user";
import { storeToRefs } from "pinia";
import { useAiStore } from "@/stores/ai";

export const useOVPStore = defineStore(
  "OVPStore",
  () => {
    const authStore = useAuthStore();
    const aIStore = useAiStore();
    const { user } = storeToRefs(authStore);
    var OV = new OpenVidu();
    var session;
    var mainstreamer;
    axios.defaults.headers.post["Content-Type"] = "application/json";

    const API_SERVER_URL = import.meta.env.VITE_OPENVIDU_API_URL;
    const sessionId = ref("");
    const connectId = ref("");

    const messagee = ref("");
    const donation = ref("");
    const follow = ref("");

    const addDonate = (point) => {
      donation.value = point;
      console.log(donation.value);
    };

    const addFollow = (name) => {
      follow.value = name;
      console.log(follow.value);
    };

    // 메시지를 추가하는 함수
    const addMessage = (message) => {
      console.log("몇번 호출?");
      messagee.value = message;
      console.log(messagee.value);
    };

    const openSession = async () => {
      try {
        // 성공적으로 통신시 클라이언트측 세션 초기화
        if (session && session.connection) {
          await session.disconnect(); // 이전 세션 연결 해제
          OV = new OpenVidu(); // OpenVidu 객체 초기화
        }
        // 새 세션을 초기화합니다.
        session = OV.initSession();
        const response = await axios.post(
          `${API_SERVER_URL}openvidu/api/sessions`,
          {
            mediaMode: "ROUTED",
            recordingMode: "MANUAL",
            customSessionId: "CUSTOM_SESSION_ID3",
            forcedVideoCodec: "VP8",
            allowTranscoding: false,
            defaultRecordingProperties: {
              name: "MyRecording",
              hasAudio: true,
              hasVideo: true,
              outputMode: "COMPOSED",
              recordingLayout: "BEST_FIT",
              resolution: "1280x720",
              frameRate: 25,
              shmSize: 536870912,
            },
          }
        );
        console.log("세션 생성됨", response.data);
        sessionId.value = response.data;
        console.log(sessionId.value);
        // 세션 열기 성공시, 자동으로 publisher로 연결
        await connectSession("PUBLISHER");
        await aIStore.ai_connect();
      } catch (error) {
        console.error("Error!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11", error);
      }
    };

    const connectSession = async (selectedMicrophoneId) => {
      try {
        const response = await axios.post(
          `${API_SERVER_URL}openvidu/api/sessions/${sessionId.value}/connection`,
          {
            type: "WEBRTC",
            data: "My Server Data",
            record: true,
            role: "PUBLISHER",
          }
        );

        connectId.value = response.data.connectionId;
        console.log("세션 connection", response.data);
        const token = response.data.connectionToken;

        // 클라이언트 단 세션 join (with token)
        session
          .connect(token)
          .then(() => {
            console.log("클라이언트측 세션 연결 성공");

            // 퍼블리셔의 카메라 및 화면 공유 설정
            var publisher = OV.initPublisher("my-video", {
              videoSource: "screen",
              // videoDimensions: '{"width":890, "height":493}',
              // 카메라와 화면 공유 설정
              audioSource: selectedMicrophoneId.value,
              publishAudio: true, // 오디오 발행 활성화
              publishVideo: true, // 비디오 발행 활성화
            });
            publisher.on("accessAllowed", () => {
              //발행한 스트림에 오디오트랙이 포함되어 있는지 확인
              const audioTracks = publisher.stream
                .getMediaStream()
                .getAudioTracks();
              console.log("오디오 트랙 정보", audioTracks);
            });

            publisher.on("videoElementCreated", (event) => {
              // 비디오 엘리먼트에 접근
              var videoElement = event.element;

              // 비디오 엘리먼트의 크기를 고정된 값으로 설정
              videoElement.style.width = "890px"; // 너비를 640픽셀로 설정W
              videoElement.style.height = "493px"; // 높이를 480픽셀로 설정
            });

            mainstreamer = publisher;

            session
              .publish(publisher)
              .then(() => {
                console.log(
                  "화면 및 카메라 공유 스트림 발생 성공",
                  sessionId.value
                );
                session
                  .subscribeToSpeechToText(publisher.stream, "ko-KR")
                  .then(() => {
                    console.log("Speech-to-Text 구독 성공");
                    // STT 구독이 성공적이라면
                    // session.on("speechToTextMessage", (event) => {
                    //   console.log("음성 변환 인식됨");
                    //   console.log(event);
                    // axios
                    //   .post(`https://2778-112-166-150-139.ngrok-free.app`, {
                    //     prompt: event.text,
                    //   })
                    //   .then((response) => {
                    //     console.log(response);
                    //   })
                    //   .catch((error) => {
                    //     console.error("변환 실패", error);
                    //   });
                    // });
                    let lastSentMessage = ""; // 마지막으로 전송된 메시지를 저장할 변수
                    const subtitleBuffer = ref("");
                    let subtitleTimeout;

                    session.on("speechToTextMessage", (event) => {
                      console.log(event);
                      subtitleBuffer.value = event.text;
                      if (subtitleTimeout) {
                        clearTimeout(subtitleTimeout);
                      }
                      subtitleTimeout = setTimeout(async () => {
                        const trimmedText = subtitleBuffer.value.trim();
                        const finalText = trimmedText.replace(/\.\s*$/, "");
                        if (
                          finalText.length > 0 &&
                          lastSentMessage !== finalText
                        ) {
                          // session 객체가 null이 아닌지 확인
                          if (session && session.connection) {
                            try {
                              await session.signal({
                                data: finalText,
                                type: "subtitles",
                              });
                              console.log(finalText);
                              console.log("send ai_messsage");
                              aIStore.ai_sendMessage(finalText);
                              lastSentMessage = finalText; // 마지막으로 전송된 메시지 업데이트
                              subtitleBuffer.value = ""; // 요청 후 subtitleBuffer 초기화
                            } catch (error) {
                              console.error("변환 실패", error);
                            }
                          } else {
                            console.log("세션 연결이 유효하지 않습니다.");
                          }
                        }
                      }, 1000);
                    });
                  })
                  .catch((error) => {
                    console.error("Speech-to-Text 구독 실패:", error);
                  });
              })

              .catch((error) => {
                console.error("화면 및 카메라 공유 스트림 발행 실패", error);
              });

            watch(
              () => aIStore.ai_subtitle,
              (newSubtitle, oldSubtitle) => {
                if (session && session.connection) {
                  session
                    .signal({
                      data: JSON.stringify({ text: newSubtitle }), // 변화된 자막 데이터
                      type: "ai_subtitles", // 신호 유형
                    })
                    .then(() => {
                      console.log(
                        "자막 데이터가 세션 참가자들과 성공적으로 공유되었습니다."
                      );
                    })
                    .catch((error) => {
                      console.error("자막 데이터 공유 중 오류 발생:", error);
                    });
                }
              },
              { immediate: true }
            );

            session.on("signal:my-chat", (event) => {
              console.log(event.data);
              console.log(event.name); // Message
              addMessage(event.data); // The type of message
            });
            session.on("signal:donate", (event) => {
              console.log(event.data);
              console.log(event.name); // Message
              addDonate(event.data); // The type of message
            });
            session.on("signal:follow", (event) => {
              console.log(event.data);
              console.log(event.name); // Message
              addFollow(event.data); // The type of message
            });
          })
          .catch((error) => {
            console.error("클라이언트측 세션 연결 실패", error);
          });
      } catch (error) {
        console.error("Error", error);
      }
    };

    // 세션 닫기
    const closeSession = async () => {
      try {
        if (session && session.connection) {
          await axios.delete(
            `${API_SERVER_URL}openvidu/api/sessions/${sessionId.value}`
          );
          session = null; // 세션 객체 초기화
        }
        console.log("세션 닫힘");
        messagee.value = "";

        //클라이언트측 세션 닫기 -> 필요없나?
      } catch (error) {
        console.error("Error", error);
      }
    };

    const sendMessage1 = async (message) => {
      try {
        const currentTime = new Date(); // 현재 시간 가져오기
        const messageData = {
          message: message,
          name: user.value.name,
          time: currentTime.toISOString(), // ISO 형식으로 시간을 문자열로 변환
        };

        // 메시지 브로드캐스트
        await session.signal({
          data: JSON.stringify(messageData),
          to: [], // 모든 연결에게 브로드캐스트
          type: "my-chat",
        });
        console.log("Message successfully sent");
      } catch (error) {
        console.error("Error sending message:", error);
      }
    };

    // 채팅 메시지 수신하기
    const receiveMessage = () => {
      console.log("11");
      session.on("signal:my-chat", (event) => {
        console.log(event);
        console.log("Received message:", event.data); // 수신된 메시지 출력
        console.log("Sender:", event.from); // 메시지 보낸 사용자 정보
        console.log("Message type:", event.type); // 메시지 타입 출력
      });
    };

    return {
      openSession,
      connectSession,
      closeSession,
      sendMessage1,
      receiveMessage,
      addMessage,
      addDonate,
      addFollow,
      donation,
      follow,
      messagee,
      sessionId,
      connectId,
    };
  },
  {
    persist: true,
  }
);
