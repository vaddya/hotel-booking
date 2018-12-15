WITH liked_rooms AS (
    SELECT room.id AS room_id
    FROM reservation
        JOIN room ON reservation.room_id = room.id
        JOIN review ON review.reservation_id = reservation.id
    WHERE reservation.user_id = 6
        AND review.rating >= 4
)
SELECT user_id AS user_id
FROM reservation
    JOIN room ON reservation.room_id = room.id
    JOIN hotel ON hotel.id = room.hotel_id
    JOIN liked_rooms ON liked_rooms.room_id = room.id
    JOIN review ON reservation.id = review.reservation_id
WHERE review.rating >= 4
    AND user_id != 6
