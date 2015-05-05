USE test;

DROP TABLE IF EXISTS test_datetime;

CREATE TABLE IF NOT EXISTS test_datetime (
    id INTEGER NOT NULL AUTO_INCREMENT,
    the_datetime DATETIME NOT NULL,
    PRIMARY KEY (id)
);