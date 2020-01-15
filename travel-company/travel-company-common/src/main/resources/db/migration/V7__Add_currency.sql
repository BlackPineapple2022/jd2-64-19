CREATE TABLE currency
(
    id            int primary key AUTO_INCREMENT,
    currency_code VARCHAR(10) NOT NULL UNIQUE
);

INSERT INTO currency (currency_code)
VALUES ('EUR'),
       ('PLN'),
       ('NOK'),
       ('GBP'),
       ('CHF'),
       ('ALL'),
       ('BAM'),
       ('BYN'),
       ('BGN'),
       ('CZK'),
       ('DKK'),
       ('HRK'),
       ('HUF'),
       ('RON'),
       ('SEK'),
       ('ISK'),
       ('MDL'),
       ('MKD'),
       ('RSD'),
       ('RUB'),
       ('UAH');