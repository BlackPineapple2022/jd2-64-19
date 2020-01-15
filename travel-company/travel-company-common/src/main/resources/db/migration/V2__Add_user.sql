CREATE TABLE user
(
    id        BIGINT AUTO_INCREMENT,
    user_name VARCHAR(255) NOT NULL UNIQUE,
    password  VARCHAR(150) NOT NULL,
    salt      varchar(50)  NOT NULL,
    role_id   int          NOT NULL DEFAULT 2,
    created   datetime     not null default now(),
    CONSTRAINT user_pk PRIMARY KEY (id),
    CONSTRAINT role_fk FOREIGN KEY (role_id)
        REFERENCES user_role (id) ON DELETE CASCADE
);


ALTER TABLE user
    AUTO_INCREMENT = 100;

INSERT INTO user (id, user_name, password, salt, role_id)
VALUES (1, 'black', 'ac7fe33f3adc323e9949361a877ecca6d674f061673b9e36256518e0f1e43802', 'sV39oJk7cYORM3xqjoKTcghKeW0=',
        1),
       (2, 'white', 'e93c5efc1973d20ce69d8552dc97d4f8e3f78c12195985a08c8b380a6781031b', '6wUzpfEfsb9G61jX2gjvmlnTUT8=',
        2);

