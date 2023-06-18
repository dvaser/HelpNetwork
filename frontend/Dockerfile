# Resmi Python imajını temel alın
FROM python:3.9

# Çalışma dizinini /code olarak belirleyin
WORKDIR /code

# Django'u kurun
RUN pip install django
RUN pip install requests

# Projenin geri kalanını kopyalayın
COPY . /code/

# Django uygulamasını çalıştırın
CMD ["python", "manage.py", "runserver", "0.0.0.0:8000"]

