import { localAxios } from "@/util/http-commons";

const local = localAxios();

async function socialLogin(coperation, success, fail) {
  await local
    .get(`http://localhost:8080/oauth/callback/${coperation}`)
    .then(success)
    .catch(fail);
}

export { socialLogin };
