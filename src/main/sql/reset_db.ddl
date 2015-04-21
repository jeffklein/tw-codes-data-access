DROP DATABASE IF EXISTS twcodes;

CREATE DATABASE IF NOT EXISTS twcodes;

USE twcodes;

-- reg_code
-- reg_code_used
-- reg_code_added

CREATE TABLE temp_code_api_response (
    id INT NOT NULL AUTO_INCREMENT,
    created_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    payload_ts DATETIME NOT NULL,
    next_update DATETIME NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY (payload_ts, next_update)
);

CREATE TABLE temp_code (
    id INT NOT NULL AUTO_INCREMENT,
    created_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    expires DATETIME NOT NULL,
    code VARCHAR(8) NOT NULL,
    api_response_id INT NOT NULL,
    FOREIGN KEY (api_response_id) REFERENCES temp_code_api_response (id) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (id),
    UNIQUE KEY (code)
);

CREATE TABLE user (
    id INT NOT NULL AUTO_INCREMENT,
    created_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    username VARCHAR(60) NOT NULL,
    password VARCHAR(41) NOT NULL,
    email VARCHAR(60) NULL DEFAULT NULL,
    tw_name VARCHAR(60) NULL DEFAULT NULL,
    tw_invite_code varchar(20) NULL DEFAULT NULL,
    last_login DATETIME NOT NULL,
    pref_timezone VARCHAR(60) NOT NULL DEFAULT 'UST',
    pref_hide_used boolean NOT NULL DEFAULT 1,
    PRIMARY KEY (id),
    UNIQUE KEY (username)
);

CREATE TABLE temp_code_used (
    id INT NOT NULL AUTO_INCREMENT,
    created_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    temp_code_id INT NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY (temp_code_id) REFERENCES temp_code (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (id),
    UNIQUE KEY (temp_code_id, user_id)
);

