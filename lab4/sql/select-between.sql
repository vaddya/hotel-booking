SELECT *
FROM reservation
WHERE price < 100 :: money
  AND "from" BETWEEN to_date('2017-01-01', 'YYYY-MM-DD')
          AND to_date('2017-01-31', 'YYYY-MM-DD');
