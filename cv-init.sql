CREATE DATABASE cv;
USE cv;
CREATE USER 'cv-api'@'localhost' IDENTIFIED BY 'resume';
GRANT ALL ON cv.* TO 'cv-api'@'localhost';