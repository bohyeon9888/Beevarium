# 🐝 Beevarium

---

### 📄 개요

---

Beevarium은 실시간 번역과 필터링 기능을 제공하는 스트리밍 플랫폼입니다. 사용자는 전 세계 어디에서나 실시간으로 소통하며 다양한 콘텐츠를 경험할 수 있습니다. Beevarium을 통해 언어 장벽 없이 다양한 문화와 정보를 공유하고, 새로운 스트리밍 경험을 즐길 수 있습니다.


### 🛠 개발 환경

---

- **Management Tool**
    ![Jira](https://img.shields.io/badge/Jira-0052CC.svg?&style=for-the-badge&logo=jira&logoColor=white)
    ![GitLab](https://img.shields.io/badge/GitLab-FCA121.svg?&style=for-the-badge&logo=gitlab&logoColor=white)
    ![Gerrit](https://img.shields.io/badge/Gerrit-EEEEEE.svg?&style=for-the-badge&logo=gerrit&logoColor=black)
    ![Mattermost](https://img.shields.io/badge/Mattermost-0072C6.svg?&style=for-the-badge&logo=mattermost&logoColor=white)
    ![Notion](https://img.shields.io/badge/Notion-000000.svg?&style=for-the-badge&logo=notion&logoColor=white)
    ![Figma](https://img.shields.io/badge/Figma-F24E1E.svg?&style=for-the-badge&logo=figma&logoColor=white)
- **IDE**
    ![VSCode](https://img.shields.io/badge/VSCode-007ACC.svg?&style=for-the-badge&logo=visualstudiocode&logoColor=white)
    ![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?&style=for-the-badge&logo=intellijidea&logoColor=white)
- **Infra**
    ![Amazon S3](https://img.shields.io/badge/Amazon_S3-569A31.svg?&style=for-the-badge&logo=amazons3&logoColor=white)
    ![Nginx](https://img.shields.io/badge/Nginx-009639.svg?&style=for-the-badge&logo=nginx&logoColor=white)
    ![Docker](https://img.shields.io/badge/Docker-2496ED.svg?&style=for-the-badge&logo=docker&logoColor=white)
    ![Ubuntu](https://img.shields.io/badge/Ubuntu-E95420.svg?&style=for-the-badge&logo=ubuntu&logoColor=white)
- **Frontend**
    ![HTML5](https://img.shields.io/badge/HTML5-E34F26.svg?&style=for-the-badge&logo=html5&logoColor=white)
    ![CSS3](https://img.shields.io/badge/CSS3-1572B6.svg?&style=for-the-badge&logo=css3&logoColor=white)
    ![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E.svg?&style=for-the-badge&logo=javascript&logoColor=black)
    ![Vue.js](https://img.shields.io/badge/Vue.js-4FC08D.svg?&style=for-the-badge&logo=vue.js&logoColor=white)
- **Backend**
    ![Java](https://img.shields.io/badge/Java-007396.svg?&style=for-the-badge&logo=java&logoColor=white)
    ![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F.svg?&style=for-the-badge&logo=springboot&logoColor=white)
    ![Spring JPA](https://img.shields.io/badge/Spring_JPA-6DB33F.svg?&style=for-the-badge&logo=spring&logoColor=white)
    ![JWT](https://img.shields.io/badge/JWT-000000.svg?&style=for-the-badge&logo=jsonwebtokens&logoColor=white)
    ![Postman](https://img.shields.io/badge/Postman-FF6C37.svg?&style=for-the-badge&logo=postman&logoColor=white)
    ![MySQL](https://img.shields.io/badge/MySQL-4479A1.svg?&style=for-the-badge&logo=mysql&logoColor=white)

    

### 🖥️ 서비스 화면

---

**메인 페이지**

<img src="https://lab.ssafy.com/s10-webmobile1-sub2/S10P12B203/uploads/9cd8889658076304b8db8b32c4a1f97a/Untitled__1_.png">

- 다양한 영상이 한 화면에 제공되는 스트리밍 사이트의 특성에 맞게 다크테마를 적용하여 눈의 피로가 적도록 디자인하였습니다.
- 메인 컬러인 노란색과 배경화면의 검은이 보색대비를 이루어 포인트를 주도록 디자인하였습니다.
- 조회수가 높은 영상들 위주로 랜덤하게 노출되어 신규 유저의 관심을 끌도록 하였습니다.

**로그인 페이지**

<img src="https://lab.ssafy.com/s10-webmobile1-sub2/S10P12B203/uploads/2ee9a8e5a0f638435184de33227887f6/Untitled__2_.png">

- 메인 컬러인 노란색과 배경화면의 검은이 보색대비를 이루어 포인트를 주도록 디자인하였습니다.
- 일반 로그인과 소셜 로그인(구글, 네이버, 카카오)을 구현하여 사용자의 접근성을 높였습니다.

**방송국 페이지**

<img src="https://lab.ssafy.com/s10-webmobile1-sub2/S10P12B203/uploads/db74049a761eee990eb89fff964f01b9/Untitled__3_.png">

- 가입한 모든 회원에게 개인 방송국이 하나씩 개설됩니다.
- 팔로워 수를 확인할 수 있습니다.
- 해당 방송국의 유저는 글쓰기 버튼을 통해 방송국 공지사항을 작석해서 시청자들에게 자신을 소개할 수 있습니다.
- 방송국 페이지에는 가장 최근에 올린 공지사항이 노출되고, 왼쪽 메뉴바에서 공지사항 탭을 누르면 전체 공지사항을 볼 수 있습니다.
- 해당 방송국 유저가 과거에 했던 방송의 다시보기 영상들이 최신순으로 노출되고, 왼쪽 메뉴바에서 다시보기 탭을 누르면 해당 방송국의 전체 다시보기 영상을 조회할 수 있습니다.

**방송하기**

<img src="https://lab.ssafy.com/s10-webmobile1-sub2/S10P12B203/uploads/21c18d01608c51e5357dc42a085a1930/Untitled__4_.png">

- 우측 상단에 있는 방 송하기 버튼을 누르면 방송하기 페이지로 이동합니다.
- 방송 시작 버튼을 누르면 스트리밍이 시작됩니다.
- 스트리머는 뉴스피드에서 후원 내역과 팔로우 내역, 그리고 채팅을 확인할 수 있습니다.

**방송보기**

<img src="https://lab.ssafy.com/s10-webmobile1-sub2/S10P12B203/uploads/8ffd20168dd91ef968f25c4bc317daab/Untitled__5_.png">

<img src="https://lab.ssafy.com/s10-webmobile1-sub2/S10P12B203/uploads/79dc71d0ab95adb1d9d6e3d3d1a68eec/Untitled__6_.png">

- 스트리밍 중인 방송을 클릭하면 실시간으로 방송을 볼 수 있습니다.
- 스트리머의 프로필 옆에 현재 시청자 수가 표시됩니다.
- 자막 토글버튼을 누르면 번역된 자막을 볼 수 있습니다.
- 필터링 토글버튼을 누르면 방송화면 우측의 AI 스크립트에서 필터링 된 스크립트를 볼 수 있습니다.
- 팔로우 버튼으로 쉽게 스트리머를 팔로우할 수 있습니다.
- 시청자는 스트리머의 방송을 보며 채팅을 할 수 있습니다.
- 채팅창 우측의 버튼을 누르면 스트리머에게 후원을 할 수 있습니다.
- 후원하기 모달 창에는 현재 자신이 보유한 포인트를 조회할 수 있고, 후원할 포인트를 선택하고, 후원 메시지를 입력해서 스트리머에게 전달할 수 있습니다.

**마이페이지**

<img src="https://lab.ssafy.com/s10-webmobile1-sub2/S10P12B203/uploads/2d1caa43545754724e0be25734c94f08/Untitled__7_.png">

- 마이페이지에서 프로필 사진을 설정할 수 있습니다.
- 유저가 보유한 포인트를 확인할 수 있습니다.
- 팔로우 채널을 한 눈에 볼 수 있고 현재 라이브 중인 채널은 빨간색 live 아이콘이 표시됩니다.

<img src="https://lab.ssafy.com/s10-webmobile1-sub2/S10P12B203/uploads/6696d6c1c15e17b89cbb7018582ef3c4/Untitled__8_.png">

- 개인정보를 수정할 수 있다.

### 🔍 주요 기능

---

 

- 실시간 스트리밍 서비스
- 다국어 실시간 번역 기능
- 사용자 간의 실시간 채팅 및 상호작용
- 유해언어 필터링 및 관리 기능
- 사용자 프로필 및 스트리밍 설정 관리
- 관심 스트리머 팔로우 및 후원 기능

### 💡 기술 소개

---

 

Beevarium은 최신 webRTC 기술과 Openvidu를 활용하여 개발되었습니다. Vue.js를 기반으로 하는 프론트엔드와, Spring Boot를 이용한 RESTful API를 제공하는 백엔드로 구성되어 있습니다. 또한, 실시간 인터랙티브한 경험을 제공하기 위해 WebSocket과 같은 기술이 통합되어 있습니다.

### 📑 설계문서

---

기능명세서 : https://docs.google.com/spreadsheets/d/18t5pUKLjCZOapyWSf-9SBBJjBj9KFop6sAkmp9uc3H4/edit?usp=drive_link

API 규격서 : https://docs.google.com/spreadsheets/d/19S0yg2bQPOAXOiAtUd9l1tqRncbhVDSTfbo-pKvTfSE/edit?usp=drive_link

ERD : https://drive.google.com/file/d/1EiglXXVR7ngmAc7wKnEucF5C1oLoY2tm/view?usp=drive_link

시퀀스 다이어그램 : https://drive.google.com/file/d/1Yl4MjMpPris_2UKoVO3hEEhDugqXw0zQ/view?usp=drive_link

### 👥 팀원 소개

---

- 팀장: [김민건] - 백엔드 개발 & 인프라 구축 & 프로젝트 관리
- 팀원: [서보현] - 백엔드 개발 & 발표 & 문서화 작업
- 팀원: [이재문] - 프론트엔드 개발 & UI/UX 디자인
- 팀원: [장세영] - 프론트 개발 & 서기 & 영상편집
- 팀원: [차준석] - 프론트엔드 개발 & UI/UX 디자인
- 팀원: [한지원] - 백엔드 개발 & 문서화 작업
