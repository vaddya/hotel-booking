INSERT INTO reservation
VALUES (DEFAULT, 3, 1, '2017-01-01', '2017-01-02', 1234, TRUE);

SELECT * FROM reservation
WHERE id = (SELECT MAX(id) FROM reservation);
-- id,     room_id, user_id, from,       to,         price,       is_paid
-- 100044, 3,       1,       2017-01-01, 2017-01-02, "$2,951.00", true

INSERT INTO review
VALUES (DEFAULT, 100044, 'Advantages', 'Disadvantages', 5);

SELECT * FROM review
WHERE reservation_id = 100044;
-- id,    reservation_id, advantages, disadvantages, rating
-- 30001, 100044,         Advantages, Disadvantages, 5

INSERT INTO cancellation
VALUES (DEFAULT, 100044, 'COMPLETED');

SELECT * FROM review
WHERE reservation_id = 100044;
-- id,    reservation_id, advantages, disadvantages, rating
