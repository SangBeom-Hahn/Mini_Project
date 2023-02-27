from flask import Flask, render_template, request
from keras.models import load_model
import cv2
import numpy as np

# def create_app():
#     app = Flask(__name__)
    
#     from .views import main_views, image_views
#     app.register_blueprint(main_views.bp)
#     app.register_blueprint(image_views.bp)
    
#     return app

app = Flask(__name__)

# @app.route('/')
# def index():
#     return 'Pybo index'

@app.route("/")
def index():
    return render_template('index.html')


@app.route('/image_preprocess', methods=['POST'])
def preprocessing():
    if request.method == 'POST':
        file = request.files['uploaded_image']
        label = file.filename
        print(label)
        if not file: return render_template('index.html', label="No Files")

        file.save('static/'+label) # 그리고 파일을 서버에 저장한다.
        model = load_model('models/model.h5')
        img = cv2.imread('static/'+label, cv2.IMREAD_GRAYSCALE)
        img = cv2.resize(255-img, (48, 48))
        img = img / 255.0

        emotion_label_to_text = {
            0: 'anger',
            1: 'fear',
            2: 'happiness',
            3: 'sadness',
            4: 'surprise',
            5: 'neutral'
        }

        model.predict(np.expand_dims(img, axis=0))
        yhat_valid = model.predict(np.expand_dims(img, axis=0))
        yhat_valid = yhat_valid.argmax(axis = -1)
        label = emotion_label_to_text[yhat_valid[0]]
        # 결과 리턴
        return render_template('index.html', label=label)

# 도커 컨테이너를 띄우고 플라스크를 서버처럼 띄우기 위한 부분
# 5000번 포트에서 플라스크가 실행되도록 작성
if(__name__ == "__main__"):
    app.run(host="0.0.0.0", port=5000, debug = True)