CREATE TABLE roundtrip
(
    id               BIGINT PRIMARY KEY AUTO_INCREMENT,
    direct_flight_id BIGINT         NOT NULL,
    return_flight_id BIGINT         NOT NULL,
    price            DECIMAL(10, 2) NOT NULL,

    CONSTRAINT direct_flight_fk FOREIGN KEY (direct_flight_id)
        REFERENCES flight (id) ON DELETE CASCADE,
    CONSTRAINT return_flight_fk FOREIGN KEY (return_flight_id)
        REFERENCES flight (id) ON DELETE CASCADE
);