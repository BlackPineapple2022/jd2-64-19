CREATE TABLE direction
(
    id             bigint PRIMARY KEY AUTO_INCREMENT,
    direction_name varchar(20) NOT NULL UNIQUE
);

INSERT INTO direction(direction_name)
VALUES ('Direct'),
       ('Return');