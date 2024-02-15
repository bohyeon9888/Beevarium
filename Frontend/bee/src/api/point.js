import { localAxios } from "@/util/http-commons";

const local = localAxios();

const getPoint = async (accessToken, success, fail) => {
  await local
    .get(`/user/point`, {
      headers: {
        Authorization: accessToken,
      },
    })
    .then(success)
    .catch(fail);
};

const addPoint = async (accessToken, success, fail) => {
  await local
    .post(`/user/point`, {
      headers: {
        Authorization: accessToken,
      },
    })
    .then(success)
    .catch(fail);
};

const donatePoint = async (accessToken, changeLog, success, fail) => {
  await local
    .post(`/relation/save`, changeLog, {
      headers: {
        Authorization: accessToken,
      },
    })
    .then(success)
    .catch(fail);
};
export { getPoint, addPoint, donatePoint };
