import { defineStore } from "pinia";
import { ref } from "vue";
export const useAiStore = defineStore(
  "aIStore",
  () => {
    let stompClient = null;
    const ai_subtitle = ref("");

    function ai_connect() {
      var socket = new SockJS("https://api.beevarium.site/ws");
      stompClient = Stomp.over(socket);
      stompClient.connect({}, function (frame) {
        console.log("Connected: " + frame);
        stompClient.subscribe(
          "/AzureWave/processedText",
          function (messageOutput) {
            console.log(messageOutput);
            ai_subtitle.value = JSON.parse(messageOutput.body).text;
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
