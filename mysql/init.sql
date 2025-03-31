CREATE DATABASE IF NOT EXISTS appointment_service;
CREATE TABLE APPOINTMENTS (
                             id BIGINT PRIMARY KEY AUTO_INCREMENT,
                             appointment_date DATE NOT NULL,
                             hp_name VARCHAR(255) NOT NULL,
                             hp_department VARCHAR(255) NOT NULL
);
