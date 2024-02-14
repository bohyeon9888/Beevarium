import { localAxios } from "@/util/http-commons";

const local = localAxios();

const subscribe = async (accessToken, stationNo, success, fail) => {
  await local
    .get(`follow/subscribe/${stationNo}`, {
      headers: {
        Authorization: accessToken,
      },
    })
    .then(success)
    .catch(fail);
};

const unSubscribe = async (accessToken, stationNo, success, fail) => {
  await local
    .delete(`follow/unsubscribe/${stationNo}`, {
      headers: {
        Authorization: accessToken,
      },
    })
    .then(success)
    .catch(fail);
};

export { subscribe, unSubscribe };
