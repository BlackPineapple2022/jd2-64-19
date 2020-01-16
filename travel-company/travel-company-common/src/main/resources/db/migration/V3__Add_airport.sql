CREATE TABLE airport
(
    id           bigint      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    airport_code VARCHAR(10) NOT NULL UNIQUE,
    country      VARCHAR(30) NOT NULL,
    city         VARCHAR(30) NOT NULL
)
;