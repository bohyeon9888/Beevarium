import { localAxios } from "@/util/http-commons";

const local = localAxios();

const replayList = async (streamerId, success, fail) => {
  await local.get(`/video/list/replay/${streamerId}`).then(success).catch(fail);
};

const replayDetail = async (streamerId, replayNo, success, fail) => {
  await local.get(`/video/replay/${streamerId}?${replayNo}`).then(success).catch(fail);
};
