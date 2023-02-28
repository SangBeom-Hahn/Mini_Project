# emotion recognition with docker
감정 인식 모델 도커 컨테이너로 배포해 보는 프로젝트

## Introduction
본 프로젝트는 K-bootcamp에서 진행한 실시간 감정 인식 프로젝트에서 개발한 AI 모델을 누구나 사용할 수 있도록 도커 파일로 만들어보고 컨테이너로 실행해 보는 실습을 진행한다. 현업에서는 인공지능을 도커로 많이 사용한다고 들었다. 이번 프로젝트로 컨테이너 환경에서의 AI을 이해하고 도커에 능숙해지는 능력을 얻을 수 있을 것이다.

## ✨ Screenshot

### container run
![shot1](https://github.com/SangBeom-Hahn/Sketch2Fashion/blob/main/assests/model.png)

### inference
![shot2](https://github.com/SangBeom-Hahn/Sketch2Fashion/blob/main/assests/model.png)

## Dataset

|Data|데이터 수|Train 데이터 수|Val 데이터 수|세부사항|
|:-:|:-:|:-:|:-:|:-:|
|1|10507|7853|2654|anger|
|2|9129|6952|2177|fear|
|3|10281|7624|2657|happiness|
|4|9880|7191|2689|neutral|
|5|10278|7499|2779|sadness|
|6|9177|6988|2189|surprise|

학습에는 aihub의 한국인 감정 인식을 위한 복합 영상 데이터 셋을 활용, 카테고리 별로 약 10000개로 구성된다.

출처 : https://aihub.or.kr/aihubdata/data/view.do?currMenu=115&topMenu=100&aihubDataSe=realm&dataSetSn=82

## Dockerfile
```
FROM python:3.10-slim-bullseye 

COPY app /app
WORKDIR /app
COPY requirements.txt .

RUN pip install --upgrade pip
RUN apt-get update -y
RUN pip install -r requirements.txt
RUN apt-get -y install libgl1-mesa-glx && apt-get -y install libglib2.0-0

EXPOSE 5000
CMD ["python", "app.py"]
```

## docker-compose.yml
```
version: "3.7"

services:
  emotion_flask:
    build:
      context: ./ai_docker_project
      dockerfile: Dockerfile
    image: emotion_flask:1.0
    container_name: emotion_flask
    hostname: myservice
    ports:
      - 8050:5000
```

```
root
├── Dockerfile
├── requirements.txt
└── app
    ├── models
    ├── static
    ├── templates
    └── views
docker-compose.yml
```

- root/Dockerfile : 도커 파일
- root/app : 어플리케이션 코드
- docker-compose.yml : 도커 컴포즈 yml 

## Requirements
```
tensorflow==2.x
Flask==2.2.2
h5py==3.8.0
Jinja2==3.1.2
keras==2.11.0
numpy==1.24.2
opencv-python==4.7.0.68
```

## reference
- [Facial Expression Recognition using Convolutional Neural Networks](https://arxiv.org/abs/1612.02903)
- [Affect Expression Behaviour Analysis in the Wild using Spatio-Channel Attention and Complementary Context Information](https://arxiv.org/pdf/2009.14440v2.pdf)

## Author

👤 **SangBoem-Hahn**

- Github: [@SangBoem-Hahn](https://github.com/SangBeom-Hahn)
- Blog : [Tistory(AI_with_docker)](https://hsb422.tistory.com/entry/%E3%85%81%EB%8F%84%EC%BB%A4-PART%EC%9D%B8%EA%B3%B5%EC%A7%80%EB%8A%A5-%EB%8F%84%EC%BB%A4-%ED%99%9C%EC%9A%A9SW-bootcamp)
