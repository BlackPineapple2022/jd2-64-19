CREATE TABLE user
(
    id                BIGINT AUTO_INCREMENT,
    user_name         VARCHAR(255) NOT NULL UNIQUE,
    password          VARCHAR(150) NOT NULL,
    salt              varchar(50)  NOT NULL,
    role_id           int          NOT NULL DEFAULT 2,
    created_date_time datetime     not null default now(),
    CONSTRAINT user_pk PRIMARY KEY (id),
    CONSTRAINT role_fk FOREIGN KEY (role_id)
        REFERENCES user_role (id) ON DELETE CASCADE
);


ALTER TABLE user
    AUTO_INCREMENT = 100;

INSERT INTO user (id, user_name, password, salt, role_id)
VALUES (1, 'skibidi', '89f797f47ee2032b7d5580025401e4386c00a00d2666bb08b8c298669b0e0918', 'lCyS0M+S8RXNrgAe2VPtQdp6lsA=',
        1),
       (2, 'black', 'e93c5efc1922220ce69d8552dc97d4f8e3f78c12195985a08c8b380a6781031b', '6wU222Efsb9G61jX2gjvmlnTUT8=',
        2),
       (3, 'admin', 'e22c5ef22973d20ce69d8222dc97d4f8e3f78c12195985a08c8b380a6781031b', '6wUzpf22b9G61jX2gjvmlnTUT8=',
        2);

