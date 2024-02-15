import { localAxios } from "@/util/http-commons";

const local = localAxios();

const search = async (keyword, success, fail) => {
  local.get(`/search/${keyword}`).then(success).catch(fail);
};

export { search };
