import { localAxios } from "@/util/http-commons";

const local = localAxios();

async function socialLogin(coperation, code, success, fail) {
  await local
    .get(`http://localhost:8080/oauth/callback/${coperation}?code=${code}`)
    .then(success)
    .catch(fail);
}

export { socialLogin };
