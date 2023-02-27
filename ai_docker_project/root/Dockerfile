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