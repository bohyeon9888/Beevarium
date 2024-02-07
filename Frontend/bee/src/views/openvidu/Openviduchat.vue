<template>
  <ov-videoconference
    @onSessionCreated="onSessionCreated"
    :tokens="tokens"
    :toolbarDisplaySessionName="false"
  >
    <div v-ovChatPanel id="my-panel">
      <h3>Chat</h3>
      <div>
        <ul>
          <li v-for="msg in messages" :key="msg">{{ msg }}</li>
        </ul>
      </div>
      <input v-model="message" />
      <button @click="send(message)">Send</button>
    </div>
  </ov-videoconference>
</template>

<script setup>
import { ref } from "vue";
import axios from "axios";
import { OT } from "@opentok/client";

const API_SERVER_URL = import.meta.env.VITE_API_SERVER_URL;
const sessionId = "chat-panel-directive-example";
let tokens;

const messages = ref([]);
let message = "";

let session;

const getToken = async () => {
  // Requesting tokens to the server application
  const response = await axios.post(
    `${API_SERVER_URL}openvidu/api/sessions/${sessionId}/connection`,
    {
      type: "WEBRTC",
      data: "My Server Data",
      record: true,
      role: "PUBLISHER",
      kurentoOptions: {
        videoMaxRecvBandwidth: 1000,
        videoMinRecvBandwidth: 300,
        videoMaxSendBandwidth: 1000,
        videoMinSendBandwidth: 300,
        allowedFilters: ["GStreamerFilter", "ZBarFilter"],
      },
    }
  );
  return response.data;
};

const onSessionCreated = (newSession) => {
  session = newSession;
  session.on("signal:chat", (event) => {
    const msg = JSON.parse(event.data).message;
    messages.value.push(msg);
  });
};

const send = (message) => {
  const signalOptions = {
    data: JSON.stringify({ message }),
    type: "chat",
    to: undefined,
  };
  session.signal(signalOptions);
};

// Initialize the tokens
(async () => {
  tokens = {
    webcam: (await getToken()).webcam,
    screen: (await getToken()).screen,
  };
})();
</script>
