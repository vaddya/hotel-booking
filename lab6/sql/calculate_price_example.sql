SELECT * FROM price
WHERE room_id = 1;
-- id,    from,       to,         price,       room_id
-- 35019, 2017-01-01, 2017-11-08, "$2,951.00", 1
-- 35020, 2017-11-09, 2018-04-26, "$2,666.00", 1
-- 35021, 2018-04-27, 2019-01-26, "$6,319.00", 1

INSERT INTO reservation
VALUES (DEFAULT, 1, 1, '2018-04-20', '2018-04-30', NULL, TRUE);

SELECT * FROM reservation
WHERE id = (SELECT MAX(id) FROM reservation);
-- id,     room_id, user_id, from,       to,         price,        is_paid
-- 100031, 1,       1,       2018-04-20, 2018-04-30, "$37,619.00", true
