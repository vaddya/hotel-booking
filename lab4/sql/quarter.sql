WITH summary AS (
    WITH quarter_summary AS (
        SELECT city.id                      AS city_id,
               EXTRACT(QUARTER FROM "from") AS quarter,
               COUNT(*)                     AS count
        FROM reservation
            JOIN room ON room.id = reservation.room_id
            JOIN room_type ON room_type.id = room.room_type_id
            JOIN hotel ON hotel.id = room_type.hotel_id
            JOIN city ON hotel.city_id = city.id
        GROUP BY city.id, quarter
    )
    SELECT ROW_NUMBER() OVER (PARTITION BY quarter ORDER BY SUM(count) DESC) AS row_num,
           city_id                                                           AS city_id,
           quarter                                                           AS quarter,
           SUM(count)                                                        AS reservations
    FROM quarter_summary
    GROUP BY quarter, city_id
)
SELECT summary.quarter      AS quarter,
       city.name            AS city_name,
       country.name         AS country_name,
       summary.reservations AS reservations
FROM summary
         JOIN city ON summary.city_id = city.id
         JOIN country ON city.country_id = country.id
WHERE summary.row_num < 2
ORDER BY summary.quarter, summary.reservations DESC;
