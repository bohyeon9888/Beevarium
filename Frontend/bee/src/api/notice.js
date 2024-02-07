import { localAxios } from "@/util/http-commons";

const local = localAxios();

const noticeList = async (stationNo, success, fail) => {
  await local
    .get(`/notice/broadcasting/${stationNo}`)
    .then(success)
    .catch(fail);
};

const noticeDetail = async (noticeNo, success, fail) => {
  await local
    .get(`/notice/broadcasting/board/${noticeNo}`)
    .then(success)
    .catch(fail);
};

const noticeCreate = async (accessToken, noticeData, success, fail) => {
  await local
    .post(`/notice/broadcasting/board/create`, noticeData, {
      headers: {
        Authorization: accessToken,
      },
    })
    .then(success)
    .catch(fail);
};

const noticeUpdate = async (accessToken, noticeData, success, fail) => {
  await local
    .put(`/notice/broadcasting/board/update`, noticeData, {
      headers: { Authorization: accessToken },
    })
    .then(success)
    .catch(fail);
};

const noticeDelete = async (accessToken, noticeNo, success, fail) => {
  await local
    .delete(`/notie/broadcasting/board/delete/${noticeNo}`, {
      headers: {
        Authorization: accessToken,
      },
    })
    .then(success)
    .catch(fail);
};

export { noticeList, noticeDetail, noticeCreate, noticeUpdate, noticeDelete };
