import { localAxios } from "@/util/http-commons";

const local = localAxios();

const getMyPage = async (accessToken, success, fail) => {
  local
    .get(`/user/mypage`, {
      headers: {
        Authorization: accessToken,
      },
    })
    .then(success)
    .catch(fail);
};

const checkNickname = async (accessToken, nickname, success, fail) => {
  local
    .get(`/user/check/${nickname}`, {
      headers: {
        Authorization: accessToken,
      },
    })
    .then(success)
    .catch(fail);
};

const changeNickname = async (accessToken, nickname, success, fail) => {
  local
    .post(`/user/check/nickname`, nickname, {
      headers: {
        Authorization: accessToken,
      },
    })
    .then(success)
    .catch(fail);
};

const changePassword = async (accessToken, password, success, fail) => {
  local
    .post(`user/update/pw`, password, {
      headers: {
        Authorization: accessToken,
      },
    })
    .then(success)
    .catch(fail);
};
export { getMyPage, checkNickname, changeNickname, changePassword };
