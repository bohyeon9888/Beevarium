import { localAxios } from "@/util/http-commons";

const local = localAxios();

const replayList = async (streamerId, success, fail) => {
  await local.get(`/videos/list/replay/${streamerId}`).then(success).catch(fail);
};

const replayDetail = async (replayNo, success, fail) => {
  await local.get(`/videos/replay/${replayNo}`).then(success).catch(fail);
};

export { replayList, replayDetail };
