INSERT INTO house_rules
VALUES (DEFAULT, '15:00:00', '12:00:00', 'Предоплата не возвращается при отмене бронирования менее чем за сутки'),
       (DEFAULT, '16:00:00', '14:00:00', 'Предоплата возвращается');

INSERT INTO hotel
VALUES (DEFAULT, 'Гранд Будапешт', 1, 'Республика Зубровка', 5, 'Комфортабельный отель Гранд Будапешт');

INSERT INTO "user"
VALUES (DEFAULT,
        'traveller',
        'traveller@travel.com',
        '5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8');

INSERT INTO room_type
VALUES (DEFAULT, 1, 'double king-size', 2, 'Номер для некурящих с 2 кроватями размера king-size'),
       (DEFAULT, 1, 'single', 1, 'Номер для некурящих с 1 кроватью');

INSERT INTO room
VALUES (DEFAULT, 1, '№123'),
       (DEFAULT, 2, '№456'),
       (DEFAULT, 1, '№789');

INSERT INTO reservation
VALUES (DEFAULT, 1, 1, '2018-10-08', '2018-10-10', 1234, TRUE),
       (DEFAULT, 2, 1, '2018-10-10', '2018-10-15', 4321, FALSE);

INSERT INTO guest
VALUES (DEFAULT, 1, 'M. Gustave', FALSE),
       (DEFAULT, 1, 'Mr. Moustafa', FALSE),
       (DEFAULT, 2, 'Serge X.', FALSE);

INSERT INTO review
VALUES (DEFAULT, 1, 'Все хорошо', 'Все плохо', 3),
       (DEFAULT, 2, 'Все плохо', 'Все хорошо', 5);

INSERT INTO price
VALUES (DEFAULT, 1, '2018-10-01', '2018-10-31', 1234),
       (DEFAULT, 1, '2018-09-01', '2018-09-30', 1000),
       (DEFAULT, 2, '2018-10-01', '2018-10-31', 4321);

INSERT INTO facility
VALUES (DEFAULT, 'Wi-Fi'),
       (DEFAULT, 'Холодильник'),
       (DEFAULT, 'TV'),
       (DEFAULT, 'Душ');

INSERT INTO room_facility
VALUES (1, 2),
       (1, 3),
       (1, 4),
       (2, 1),
       (2, 4);
