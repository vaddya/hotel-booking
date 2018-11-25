SELECT id, "to" - "from" AS duration, price / NULLIF("to" - "from", 0) AS per_night
FROM reservation
LIMIT 10