CREATE TABLE user_role
(
    id        int PRIMARY KEY AUTO_INCREMENT,
    role_name varchar(20) NOT NULL UNIQUE
);

INSERT INTO user_role(role_name)
VALUES ('ADMIN'),
       ('USER');