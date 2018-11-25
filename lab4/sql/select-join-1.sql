SELECT "from", "to", type, capacity
FROM reservation
     JOIN room ON reservation.room_id = room.id
     JOIN room_type ON room.room_type_id = room_type.id
LIMIT 10;
