import { localAxios } from "@/util/http-commons";

const local = localAxios();

const streamingStart = async (accessToken, streamData, success, fail) => {
  await local
    .post(`/live/start`, streamData, {
      headers: {
        Authorization: accessToken,
      },
    })
    .then(success)
    .catch(fail);
};

const streamingEnd = async (accessToken, chatting, success, fail) => {
  await local
    .post(`/live/end`, chatting, {
      headers: {
        Authorization: accessToken,
      },
    })
    .then(success)
    .catch(fail);
};

const streamingEnter = async (accessToken, streamerId, success, fail) => {
  await local
    .get(`/live/enter/${streamerId}`, {
      headers: { Authorization: accessToken },
    })
    .then(success)
    .catch(fail);
};

export { streamingStart, streamingEnd, streamingEnter };
