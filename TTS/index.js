import "dotenv/config";
import express from "express";
import bodyParser from "body-parser";
import { PollyClient, SynthesizeSpeechCommand } from "@aws-sdk/client-polly";
import cors from "cors";
const app = express();
const port = 8999;

// AWS Polly 클라이언트 설정
const pollyClient = new PollyClient({
  region: process.env.AWS_REGION,
  credentials: {
    accessKeyId: process.env.AWS_ACCESS_KEY_ID,
    secretAccessKey: process.env.AWS_SECRET_ACCESS_KEY,
  },
});
app.use(cors()); // 모든 요청에 대해 CORS 허용
app.use(bodyParser.json()); // JSON 본문을 파싱하기 위한 미들웨어 설정

app.post("/synthesize", async (req, res) => {
  const textToSynthesize = req.body.text; // 클라이언트로부터 받은 텍스트

  const params = {
    Engine: "neural",
    LanguageCode: "ko-KR",
    OutputFormat: "mp3",
    Text: textToSynthesize,
    VoiceId: "Seoyeon",
  };

  try {
    const { AudioStream } = await pollyClient.send(
      new SynthesizeSpeechCommand(params)
    );
    const audioBuffer = await streamToBuffer(AudioStream);
    const base64Audio = audioBuffer.toString("base64");
    res.status(200).send({
      audioContent: base64Audio,
    });
  } catch (error) {
    console.error(error);
    res.status(500).send({ message: "음성 변환 실패" });
  }
});

app.listen(port, () => {
  console.log(`Server is running on http://localhost:${port}`);
});

const streamToBuffer = (stream) => {
  return new Promise((resolve, reject) => {
    const chunks = [];
    stream.on("data", (chunk) => chunks.push(chunk));
    stream.on("error", reject);
    stream.on("end", () => resolve(Buffer.concat(chunks)));
  });
};
