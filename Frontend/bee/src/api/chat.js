import { localAxios } from "@/util/http-commons";

const local = localAxios();

const getChatLog = async (accessToken, username, success, fail) => {
  await local
    .get(`/chat/show/${username}`, {
      headers: {
        Authorization: accessToken,
      },
    })
    .then(success)
    .catch(fail);
};
export { getChatLog };
