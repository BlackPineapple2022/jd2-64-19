ALTER TABLE flight_scanner_journal
    CHANGE COLUMN id id BIGINT(20) NOT NULL AUTO_INCREMENT ,
    CHANGE COLUMN is_active is_active TINYINT NULL ;
