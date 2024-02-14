import { defineStore } from "pinia";
import { ref } from "vue";
export const useAiStore = defineStore(
  "aIStore",
  () => {
    let stompClient = null;
    const ai_subtitle = ref([]);

    function ai_connect() {
      var socket = new SockJS("https://api.beevarium.site/ws");
      stompClient = Stomp.over(socket);
      stompClient.connect({}, function (frame) {
        console.log("Connected: " + frame);
        stompClient.subscribe(
          "/AzureWave/processedText",
          function (messageOutput) {
            console.log(messageOutput);
            // JSON 문자열을 객체로 파싱
            var messageData = JSON.parse(messageOutput.body);
            console.log(messageData);
            // requestTime과 answer를 포함하는 출력 포맷 만들기
            ai_subtitle.value = messageData;
            console.log(ai_subtitle.value);
          }
        );
      });
    }

    function ai_disconnect() {
      if (stompClient !== null) {
        stompClient.disconnect();
      }
      console.log("Disconnected");
    }

    function ai_sendMessage(text) {
      stompClient.send("/AI/change", {}, JSON.stringify({ text }));
    }

    return {
      ai_connect,
      ai_disconnect,
      ai_sendMessage,
      stompClient,
      ai_subtitle,
    };
  },
  {
    persist: true,
  }
);
