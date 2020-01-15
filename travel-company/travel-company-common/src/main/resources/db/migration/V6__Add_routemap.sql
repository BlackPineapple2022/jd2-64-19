CREATE TABLE routemap
(
    id                     int primary key AUTO_INCREMENT,
    airline_id             int NOT NULL,
    origin_airport_id      int NOT NULL,
    destination_airport_id int NOT NULL,
    direction_id           int NOT NULL,
    CONSTRAINT airline_fk FOREIGN KEY (airline_id)
        REFERENCES airline (id) ON DELETE CASCADE,
    CONSTRAINT origin_airport_fk FOREIGN KEY (origin_airport_id)
        REFERENCES airport (id) ON DELETE CASCADE,
    CONSTRAINT destination_airport_fk FOREIGN KEY (destination_airport_id)
        REFERENCES airport (id) ON DELETE CASCADE,
    CONSTRAINT direction_fk FOREIGN KEY (direction_id)
        REFERENCES direction (id) ON DELETE CASCADE
);