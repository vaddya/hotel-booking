SELECT room_type_id AS room_type_id, COUNT(*) AS room_count
FROM room
    JOIN room_type ON room.room_type_id = room_type.id
WHERE hotel_id = 5
GROUP BY room_type_id
ORDER BY room_type_id
