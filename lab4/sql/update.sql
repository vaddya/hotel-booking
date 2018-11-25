SELECT *
FROM reservation
WHERE price = 0 :: money;

UPDATE reservation
SET price = 0 :: money
WHERE price = 1 :: money;

SELECT *
FROM reservation
WHERE price = 0 :: money;
