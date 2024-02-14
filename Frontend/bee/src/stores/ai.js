import { ref } from "vue";
import { defineStore } from "pinia";

export const useAiStore = defineStore(
  "aIStore",
  () => {
    var stompClient = null;

    function ai_connect() {
      var socket = new SockJS("https://api.beevarium.site:8080/ws");
      stompClient = Stomp.over(socket);
      stompClient.connect({}, function (frame) {
        console.log("Connected: " + frame);
        stompClient.subscribe(
          "/AzureWave/processedText",
          function (messageOutput) {
            console.log(messageOutput);
            // ai_showMessageOutput(messageOutput.body);
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

    function ai_showMessageOutput(messageOutput) {
      var responseDiv = document.getElementById("response");
      try {
        console.log(messageOutput);
        // JSON 문자열을 객체로 파싱
        var messageData = JSON.parse(messageOutput);
        // 원하는 데이터를 페이지에 추가
        console.log(messageData);
      } catch (e) {
        // 오류가 발생하면 예외 처리
        console.error("Error parsing message output:", e);
        responseDiv.innerHTML +=
          "<p>오류가 발생했습니다: " + e.toString() + "</p>";
      }
    }
    return {
      ai_connect,
      ai_disconnect,
      ai_sendMessage,
      ai_showMessageOutput,
      stompClient,
    };
  },
  {
    persist: true,
  }
);
