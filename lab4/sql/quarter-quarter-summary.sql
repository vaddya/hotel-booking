SELECT city.id                      AS city_id,
       EXTRACT(QUARTER FROM "from") AS quarter,
       COUNT(*)                     AS count
FROM reservation
  JOIN room ON room.id = reservation.room_id
  JOIN hotel ON hotel.id = room.hotel_id
  JOIN city ON hotel.city_id = city.id
GROUP BY city.id, quarter
ORDER BY city.id, quarter
LIMIT 8