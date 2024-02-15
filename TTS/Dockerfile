# Node.js 공식 이미지를 사용하는 베이스 이미지 설정
FROM node:alpine

# 앱 디렉토리 생성
WORKDIR /usr/src/app

# 앱 의존성 설치
# package.json과 package-lock.json을 모두 복사
COPY package*.json ./

RUN npm install
# 프로덕션을 위한 코드인 경우
# RUN npm ci --only=production

# 앱 소스 추가
COPY . .

# # SSL/TLS 인증서 파일 복사
# COPY ./192.168.31.163.crt ./
# COPY ./192.168.31.163.key ./

# # 앱이 HTTPS 포트에서 실행됨을 알림
# EXPOSE 80

# 앱 실행
CMD [ "node", "index.js" ]
