ALTER TABLE flight_scanner_journal
    CHANGE COLUMN scanned_time scanned_time DATETIME NULL DEFAULT CURRENT_TIMESTAMP ;
