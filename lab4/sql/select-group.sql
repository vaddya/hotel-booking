SELECT user_id
FROM reservation
GROUP BY user_id
HAVING COUNT(1) > 30;
