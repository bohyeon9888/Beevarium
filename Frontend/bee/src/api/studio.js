import { localAxios } from "@/util/http-commons";

const local = localAxios();

const studio = async (accessToken, streamerId, success, fail) => {
  await local
    .get(`/station/view/${streamerId}`, { headers: { Authorization: accessToken } })
    .then(success)
    .catch(fail);
};

export { studio };