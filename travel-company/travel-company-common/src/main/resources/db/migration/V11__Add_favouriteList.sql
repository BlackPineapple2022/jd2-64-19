CREATE TABLE favourite_list
(
    id             bigint PRIMARY KEY AUTO_INCREMENT,
    favourite_id bigint NOT NULL,
    trip_id bigint NOT NULL,
    CONSTRAINT favourite_list_fk FOREIGN KEY (favourite_id)
        REFERENCES favourite (id) ON DELETE CASCADE,
    CONSTRAINT trip_fk FOREIGN KEY (trip_id)
        REFERENCES roundtrip (id) ON DELETE CASCADE
);

