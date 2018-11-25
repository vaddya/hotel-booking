SELECT COUNT(*)                       AS count,
       AVG("to" - "from")             AS avg_duration,
       AVG(price :: numeric) :: money AS avg_price,
       MAX(price)                     AS max_price,
       SUM(price)                     AS sum_price
FROM reservation;
