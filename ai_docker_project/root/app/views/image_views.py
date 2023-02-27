from flask import Blueprint, render_template, request
from keras.models import load_model
import cv2
import numpy as np

bp = Blueprint('image', __name__, url_prefix='/image')

@bp.route("/")
def index():
    return render_template('index.html')


@bp.route('/image_preprocess', methods=['POST'])
def preprocessing():
    if request.method == 'POST':
        file = request.files['uploaded_image']
        label = file.filename
        print(label)
        if not file: return render_template('index.html', label="No Files")

        file.save('app/static/'+label) # 그리고 파일을 서버에 저장한다.
        model = load_model('app/models/model.h5')
        img = cv2.imread('app/static/'+label, cv2.IMREAD_GRAYSCALE)
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