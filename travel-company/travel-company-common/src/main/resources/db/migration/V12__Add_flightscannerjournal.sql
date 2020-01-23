CREATE TABLE flight_scanner_journal
(
    id           bigint primary key not null,
    routemap_id  bigint             not null,
    scanned_time datetime           not null default now(),
    is_active    int                         default 1,
        CONSTRAINT flight_scanner_journal_fk FOREIGN KEY (routemap_id)
        REFERENCES routemap (id) ON DELETE CASCADE
);