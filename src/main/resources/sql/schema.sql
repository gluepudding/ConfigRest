DROP TABLE IF EXISTS config_json;

CREATE TABLE config_json(
    app_code VARCHAR(45) NOT NULL COMMENT 'application code',
    version VARCHAR(15) NOT NULL COMMENT 'version of config',
    hash_code CHAR(32) NOT NULL COMMENT 'hash of config content',
    json_conf VARCHAR(255) NOT NULL COMMENT 'json of config content',
    creation_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'added or updated time',
    PRIMARY KEY(app_code, version)
);