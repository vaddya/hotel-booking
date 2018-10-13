INSERT INTO country
VALUES ('RUS', 'Россия'),
       ('UKR', 'Украина'),
       ('BLR', 'Белоруссия');

INSERT INTO city
VALUES (DEFAULT, 'RUS', 'Москва'),
       (DEFAULT, 'RUS', 'Санкт-Петербург'),
       (DEFAULT, 'UKR', 'Киев'),
       (DEFAULT, 'BLR', 'Минск');

INSERT INTO bonus_penalty
VALUES (DEFAULT, 'Бронирование более чем на 7 дней', 1000),
       (DEFAULT, 'Отмена бронирования менее чем за сутки', -1000);

INSERT INTO bonus_penalty_reservation
VALUES (1, 1);

UPDATE "user"
SET city_id = 3
WHERE id = 1;

UPDATE hotel
SET city_id = 1
WHERE id = 1;