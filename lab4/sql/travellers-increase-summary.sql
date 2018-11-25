SELECT ROW_NUMBER() OVER (PARTITION BY user_id ORDER BY "from") AS row_num,
       price                                                    AS price,
       user_id                                                  AS user_id
FROM reservation
WHERE is_paid AND user_id IN (
    SELECT user_id
    FROM reservation
    WHERE is_paid
    GROUP BY user_id
    HAVING COUNT(*) > 10
)
LIMIT 10
