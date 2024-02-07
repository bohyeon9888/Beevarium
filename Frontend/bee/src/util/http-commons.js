import axios from "axios";

const { VITE_BEEVARIUM_API_URL } = import.meta.env;

// local wannago api axios instance
function localAxios() {
  const instance = axios.create({
    baseURL: VITE_BEEVARIUM_API_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });
  return instance;
}

export { localAxios };