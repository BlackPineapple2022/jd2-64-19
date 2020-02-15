CREATE TABLE favourite
(
    id             bigint PRIMARY KEY AUTO_INCREMENT,
    favourite_name varchar(50) NOT NULL UNIQUE,
    user_id bigint NOT NULL,
        CONSTRAINT favourite_fk FOREIGN KEY (user_id)
        REFERENCES user (id) ON DELETE CASCADE
);

INSERT INTO favourite(favourite_name, user_id)
VALUES ('skibidi_favourite',1);
