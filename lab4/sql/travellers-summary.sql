WITH increase_summary AS (
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
)
SELECT curr.user_id                                       AS user_id,
       AVG((curr.price - prev.price) :: numeric) :: money AS avg_diff
FROM increase_summary curr
     JOIN increase_summary prev
     ON curr.user_id = prev.user_id AND curr.row_num = prev.row_num + 1
GROUP BY curr.user_id
ORDER BY avg_diff DESC
LIMIT 5