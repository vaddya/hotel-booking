SELECT room.id AS room_id
FROM reservation
    JOIN room ON reservation.room_id = room.id
    JOIN review ON review.reservation_id = reservation.id
WHERE reservation.user_id = 6
    AND review.rating >= 4
