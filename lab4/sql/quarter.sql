WITH summary AS (SELECT city,
                        quarter,
                        SUM(rcount)                                                        AS qcount,
                        ROW_NUMBER() OVER (PARTITION BY quarter ORDER BY SUM(rcount) DESC) AS row
                 FROM (SELECT city.id                                AS id,
                              city.name                              AS city,
                              EXTRACT(QUARTER FROM reservation.from) AS quarter,
                              COUNT(1)                               AS rcount
                       FROM reservation
                                JOIN room ON room.id = reservation.room_id
                                JOIN room_type ON room_type.id = room.room_type_id
                                JOIN hotel ON hotel.id = room_type.hotel_id
                                JOIN city ON hotel.city_id = city.id
                       GROUP BY city.id, quarter) AS t1
                 GROUP BY quarter, city
                 ORDER BY quarter, qcount DESC)
SELECT quarter, city, qcount
FROM summary s
WHERE s.row = 1
