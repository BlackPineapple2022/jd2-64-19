CREATE TABLE airline
(
    id           int PRIMARY KEY AUTO_INCREMENT,
    airline_name varchar(20) NOT NULL UNIQUE
);

INSERT INTO airline(airline_name)
VALUES ('RY'),
       ('WIZZ');