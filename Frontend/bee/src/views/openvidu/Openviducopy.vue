<script setup>
import { ref } from "vue";
import { OpenVidu } from "openvidu-browser";
import axios from "axios";
import UserVideo from "./components/UserVideo.vue";

axios.defaults.headers.post["Content-Type"] = "application/json";

const APPLICATION_SERVER_URL = "https://api.beevarium.site/";

const OV = new OpenVidu();
const session = ref(null);
const sessionId = ref(null);
const streamId = ref(null);
const mainStreamManager = ref(null);
const publisher = ref(null);
const subscribers = ref([]);

const connection = () => {
  session.value.on("streamCreated", ({ stream }) => {
    const subscriber = session.value.subscribe(stream);
    streamId.value = subscriber.stream.streamId;
    subscribers.value.push(subscriber);
  });

  session.value.on("streamDestroyed", ({ stream }) => {
    const index = subscribers.value.indexOf(stream.streamManager, 0);
    if (index >= 0) {
      subscribers.value.splice(index, 1);
    }
  });

  session.value.on("exception", ({ exception }) => {
    console.warn(exception);
  });

  getToken(studioInfo.studio_id).then((token) => {
    session.value
      .connect(token, { clientData: user.myPageSimpleResponse.userNickName })
      .then(() => {
        OV.getUserMedia({
          audioSource: false,
          videoSource: undefined,
          resolution: "320x240",
          frameRate: 30,
        }).then((mediaStream) => {
          const videoTrack = mediaStream.getVideoTracks()[0];

          const newPublisher = OV.initPublisher(
            user.myPageSimpleResponse.userNickname,
            {
              audioSource: undefined,
              videoSource: videoTrack,
              publishAudio: true,
              publishVideo: true,
              resolution: "320x240",
              frameRate: 30,
              insertMode: "APPEND",
              mirror: false,
            }
          );
          newPublisher.once("accessAllowed", () => {
            session.value.publish(newPublisher);
            publisher.value = newPublisher;
          });
        });
      })
      .catch((error) => {
        console.log(
          "There was an error connecting to the session:",
          error.code,
          error.message
        );
      });
  });
};

const joinSession = () => {
  const OV = new OpenVidu();
  const session = OV.initSession();
  connection();
};

const leaveSession = () => {
  if (session.value) session.value.disconnect();

  session.value = null;
  mainStreamManager.value = null;
  publisher.value = null;
  subscribers.value = [];
};

const updateMainVideoStreamManager = (stream) => {
  if (mainStreamManager.value === stream) return;
  mainStreamManager.value = stream;
};

const getToken = async (mySessionId) => {
  mySessionId = `studio${mySessionId}`;
  await createSession(mySessionId);
  return await createToken(mySessionId);
};

const createSession = async (sessionId) => {
  await axios
    .post(
      `${APPLICATION_SERVER_URL}api/sessions`,
      { customSessionId: sessionId },
      {
        headers: {
          "Content-Type": "application/json",
          Authorization: "Basic T1BFTlZJRFVBUFA6TVlfU0VDUkVU",
        },
      }
    )
    .then((response) => {
      sessionId.value = response.data.id;
    })
    .catch((response) => {
      const err = { ...response };
      if (err?.response?.status === 409) {
        sessionId.value = sessionId;
      }
    });
};

const createToken = async (sessionId) => {
  const data = {};
  const openviduInstance = await axios.post(
    `${APPLICATION_SERVER_URL}api/sessions/${sessionId}/connection`,
    {
      headers: {
        "Content-Type": "application/json",
        Authorization: "Basic T1BFTlZJRFVBUFA6TVlfU0VDUkVU",
      },
      body: {
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
      },
    },
    data
  );
  return openviduInstance.data.token; // The token
};
</script>

<template>
  <div id="main-container" class="container">
    <div id="join" v-if="!session">
      <div id="join-dialog" class="jumbotron vertical-center">
        <div class="form-group">
          <div class="studio-tab__button-section">
            <button @click="joinSession()">화상 회의 참여하기</button>
          </div>
        </div>
      </div>
    </div>

    <div id="session" v-if="session">
      <div id="session-header">
        <div class="studio-tab__button-section">
          <button @click="leaveSession">화상 회의 떠나기</button>
        </div>
      </div>
      <div v-show="false">
        <div id="main-video" class="col-md-6">
          <UserVideo :stream-manager="mainStreamManager" />
        </div>
        <div id="video-container" class="col-md-6">
          <UserVideo
            :stream-manager="publisher"
            @click="updateMainVideoStreamManager(publisher)"
          />
        </div>
      </div>
      <div id="video-contaniner2" class="col-md-6">
        <div v-for="(sub, idx) in subscribers" :key="idx">
          <div class="studio_video-section">
            <UserVideo
              :key="sub.stream.connection.connectionId"
              :stream-manager="sub"
              @click="updateMainVideoStreamManager(sub)"
            />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.studio-tab__button-section {
  margin-top: 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.studio-tab__button-section > button {
  cursor: pointer;
  font-size: 14px;
  font-weight: 500;
  border: none;
  padding: 8px 30px;
  white-space: nowrap;
  background-color: #1e1e1e;
  color: white;
  border-radius: 8px;
}

.studio_video-section {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
</style>
