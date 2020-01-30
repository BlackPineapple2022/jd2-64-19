CREATE TABLE flight
(
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    routemap_id         bigint         NOT NULL,
    departure_date_time DATETIME       NOT NULL,
    arrive_date_time    DATETIME       NOT NULL,
    flight_number       VARCHAR(20)    NOT NULL,
    currency_id         bigint         NOT NULL,
    price               DECIMAL(10, 2) NOT NULL,
    checked_date        DATETIME DEFAULT NOW(),
    search_id           BIGINT         NOT NULL,
    CONSTRAINT routemap_fk FOREIGN KEY (routemap_id)
        REFERENCES routemap (id) ON DELETE CASCADE,
    CONSTRAINT currency_fk FOREIGN KEY (currency_id)
        REFERENCES currency (id) ON DELETE CASCADE
);