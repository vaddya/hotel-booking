CREATE TABLE IF NOT EXISTS house_rules (
  id                  SERIAL PRIMARY KEY,
  checkin_from        TIME NOT NULL,
  checkout_until      TIME NOT NULL,
  cancellation_policy TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS hotel (
  id             SERIAL PRIMARY KEY,
  name           VARCHAR(128) NOT NULL,
  house_rules_id INTEGER      NOT NULL,
  address        VARCHAR(256) NOT NULL,
  stars          SMALLINT     NOT NULL,
  description    TEXT,
  FOREIGN KEY (house_rules_id) REFERENCES house_rules (id)
);

CREATE TABLE IF NOT EXISTS "user" (
  id            SERIAL PRIMARY KEY,
  name          VARCHAR(128),
  email         VARCHAR(256) NOT NULL,
  password_hash CHAR(64)     NOT NULL,
  phone_number  VARCHAR(15)
);

CREATE TABLE IF NOT EXISTS room_type (
  id          BIGSERIAL PRIMARY KEY,
  hotel_id    INTEGER      NOT NULL,
  type        VARCHAR(128) NOT NULL,
  capacity    SMALLINT     NOT NULL,
  description VARCHAR(256) NOT NULL,
  FOREIGN KEY (hotel_id) REFERENCES hotel (id)
);

CREATE TABLE IF NOT EXISTS room (
  id           BIGSERIAL PRIMARY KEY,
  room_type_id BIGINT       NOT NULL,
  name         VARCHAR(128) NOT NULL,
  FOREIGN KEY (room_type_id) REFERENCES room_type (id)
);

CREATE TABLE IF NOT EXISTS reservation (
  id      BIGSERIAL PRIMARY KEY,
  room_id BIGINT  NOT NULL,
  user_id INTEGER NOT NULL,
  "from"  DATE    NOT NULL,
  "to"    DATE    NOT NULL,
  price   MONEY   NOT NULL,
  is_paid BOOLEAN NOT NULL,
  FOREIGN KEY (room_id) REFERENCES room (id),
  FOREIGN KEY (user_id) REFERENCES "user" (id)
);

CREATE TABLE IF NOT EXISTS guest (
  id             BIGSERIAL PRIMARY KEY,
  reservation_id BIGINT       NOT NULL,
  name           VARCHAR(128) NOT NULL,
  is_child       BOOLEAN      NOT NULL,
  FOREIGN KEY (reservation_id) REFERENCES reservation (id)
);

CREATE TABLE IF NOT EXISTS review (
  id             BIGSERIAL PRIMARY KEY,
  reservation_id BIGINT   NOT NULL,
  advantages     TEXT,
  disadvantages  TEXT,
  rating         SMALLINT NOT NULL,
  FOREIGN KEY (reservation_id) REFERENCES reservation (id)
);

CREATE TABLE IF NOT EXISTS cancellation (
  id             BIGSERIAL PRIMARY KEY,
  reservation_id BIGINT      NOT NULL,
  status         VARCHAR(32) NOT NULL,
  FOREIGN KEY (reservation_id) REFERENCES reservation (id)
);

CREATE TABLE IF NOT EXISTS price (
  id           BIGSERIAL PRIMARY KEY,
  room_type_id BIGINT NOT NULL,
  "from"       DATE   NOT NULL,
  "to"         DATE   NOT NULL,
  price        MONEY  NOT NULL,
  FOREIGN KEY (room_type_id) REFERENCES room_type (id)
);

CREATE TABLE IF NOT EXISTS facility (
  id   SERIAL PRIMARY KEY,
  name VARCHAR(256) NOT NULL
);

CREATE TABLE IF NOT EXISTS room_facility (
  room_type_id BIGINT  NOT NULL,
  facility_id  INTEGER NOT NULL,
  CONSTRAINT room_facility_pk PRIMARY KEY (room_type_id, facility_id),
  FOREIGN KEY (room_type_id) REFERENCES room_type (id),
  FOREIGN KEY (facility_id) REFERENCES facility (id)
);