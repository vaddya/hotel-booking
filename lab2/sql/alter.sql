CREATE TABLE IF NOT EXISTS country (
    id   CHAR(3) PRIMARY KEY,
    name VARCHAR(128)
);

CREATE TABLE IF NOT EXISTS city (
    id         SERIAL PRIMARY KEY,
    country_id CHAR(3),
    name       VARCHAR(128),
    FOREIGN KEY (country_id) REFERENCES country (id)
);

CREATE TABLE IF NOT EXISTS bonus_penalty (
    id        SERIAL PRIMARY KEY,
    condition VARCHAR(256),
    price     MONEY
);

CREATE TABLE IF NOT EXISTS bonus_penalty_reservation (
    bonus_penalty_id INTEGER,
    reservation_id   BIGINT,
    FOREIGN KEY (bonus_penalty_id) REFERENCES bonus_penalty (id),
    FOREIGN KEY (reservation_id) REFERENCES reservation (id)
);

ALTER TABLE IF EXISTS "user"
    ADD COLUMN city_id INTEGER;
ALTER TABLE IF EXISTS "user"
    ADD FOREIGN KEY (city_id) REFERENCES city (id);

ALTER TABLE IF EXISTS hotel
    ADD COLUMN city_id INTEGER;
ALTER TABLE IF EXISTS hotel
    ADD FOREIGN KEY (city_id) REFERENCES city (id);