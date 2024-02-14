import { localAxios } from "@/util/http-commons";

const local = localAxios();

const followChannel = async (accessToken, success, fail) => {
  await local
    .get(`/live/subscribe`, { headers: { Authorization: accessToken } })
    .then(success)
    .catch(fail);
};

export { followChannel };