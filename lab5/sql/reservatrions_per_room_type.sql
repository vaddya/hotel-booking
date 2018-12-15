SELECT room_type_id AS room_type_id,
       COUNT(*)     AS reservations_count
FROM reservation
    JOIN room ON reservation.room_id = room.id
    JOIN room_type ON room.room_type_id = room_type.id
WHERE date_part('Y', now()) - date_part('Y', reservation.from) <= 2
    AND hotel_id = 1
GROUP BY room_type_id
