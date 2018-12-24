SELECT * FROM reservation
WHERE id = (SELECT MAX(id) FROM reservation);
-- id,     room_id, user_id, from,       to,         price,        is_paid
-- 100031, 1,       1,       2018-04-20, 2018-04-30, "$37,619.00", true

INSERT INTO reservation
VALUES (DEFAULT, 1, 1, '2018-04-01', '2018-04-25', NULL, TRUE);
-- [P0001] ERROR: Room is not available. Conflicts: {100031}
-- Where: PL/pgSQL function reservation_validate_availability() line 14 at RAISE

INSERT INTO reservation
VALUES (DEFAULT, 1, 1, '2018-04-01', '2018-05-31', NULL, TRUE);
-- [P0001] ERROR: Room is not available. Conflicts: {26126,75734,100031}
-- Where: PL/pgSQL function reservation_validate_availability() line 14 at RAISE

INSERT INTO reservation
VALUES (DEFAULT, 1, 1, '2018-04-25', '2018-05-31', NULL, TRUE);
-- [P0001] ERROR: Room is not available. Conflicts: {26126,75734,100031}
-- Where: PL/pgSQL function reservation_validate_availability() line 14 at RAISE

INSERT INTO reservation
VALUES (DEFAULT, 1, 1, '2018-04-25', '2018-04-26', NULL, TRUE);
-- [P0001] ERROR: Room is not available. Conflicts: {100031}
-- Where: PL/pgSQL function reservation_validate_availability() line 14 at RAISE
