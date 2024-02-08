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

const streamingEnd = async (accessToken, success, fail) => {
  await local
    .get(`/live/end`, {
      headers: {
        Authorization: accessToken,
      },
    })
    .then(success)
    .catch(fail);
};

export { streamingStart, streamingEnd };
