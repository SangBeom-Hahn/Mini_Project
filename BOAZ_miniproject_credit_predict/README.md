# Credit Prediction project
BOAZ_base_session_project

## Description

본 프로젝트는 DACON 신용카드 사용자 연체 예측을 주제로 EDA, 전처리, 모델링을 진행한다.

https://dacon.io/competitions/official/235713/overview/description

## Files
- Dacon_Credit_Prediction_EDA.ipynb :  데이터 셋 EDA
- Dacon_Credit_Prediction_Modeling.ipynb : 데이터 전처리 및 연체 예측 진행
- 가설 리스트.xlsx : 데이터 분석 도중 생긴 가설들을 모아놓은 리스트

## EDA
1. 차량을 소유하지 않고 집을 소유한 사람의 신용도가 다른 경우에 비해 높았음
2. 자녀 수가 7명 이상으로 이상치를 보이는 고객 존재
3. 가족 규모가 자녀 수보다 작은 고객 존재
4. 가족 규모 - 자녀 수 = 1인 가정은 한부모 가정이라고 가정
5. 신용도가 낮은 고객일수록 연간 소득이 큰 사림이 많았음
6. 태어난 월 수보다 신용카드 발급 월 수가 많은 이상치로 보이는 고객 존재

## Preprocessing
1. 수입이 1200000 이상의 데이터는 1200000로 치환
2. 가족 규모가 8 이상의 값을 모두 8로 치환
3. 태어난 월 수는 년 단위로 치환
4. 일한 날짜 수는 월 단위로 치환
5. 자녀 수가 6 이상의 값을 모두 6으로 치환
6. 자녀수 - 가족 규모가 < 0은 값은 0으로 치환
7. 신용도 변동 여부 컬럼을 추가

## Modeling
### Loss - Logloss
이진 분류 과제에서 주로 사용하는 성능 평가 지표는 정오 행렬을 기반으로 한 정확도, 정밀도, 재현율 등이 존재함.
하지만 해당 과제는 다항 분류로, 성능 평가 지표 계산을 위해 3x3의 정오 행렬이 생성됨. 
또한 예측이 틀린 정도에 따라 경우에 패널티가 바뀌는 성능 평가 지표로 판단하고자 하였음.

![loss](https://github.com/SangBeom-Hahn/Mini_Project/blob/master/BOAZ_miniproject_credit_predict/logloss.jpg)


### Model Result
|LightGBM |CatBoost|XGBoost|
|:-:|:-:|:-:|
|0.73212|0.73823|0.78253|

Log loss 값이 낮은 LightGBM을 최종적으로 선택함




## Feedback
- 자녀수, 결혼 여부 등에 따른 이상치라고 가정하고 전처리한 전후 loss 결과를 비교해 보았을 때 Log loss 값이 커졌다는 아쉬움이 있다.
- 해당 데이터는 고객의 자산 포트폴리오와 관련된 컬럼만 존재했다. 현재 금융권에는 금융과 비금융 데이터를 결합한 데이터를 통해 연체 예측을 하고 있으므로 이후 결합 데이터로 분석을 시도하면 좋겠다.


## Author
👤 **SangBoem-Hahn**

- Github: [@SangBoem-Hahn](https://github.com/SangBeom-Hahn)
- Blog : [Tistory](https://hsb422.tistory.com/)
