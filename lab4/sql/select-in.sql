SELECT *
FROM reservation
WHERE user_id IN (1, 2, 3)
  AND "from" < to_date('2017-01-31', 'YYYY-MM-DD');
