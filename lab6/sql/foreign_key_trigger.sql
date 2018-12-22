UPDATE room SET id = 5009 WHERE id = 1212
-- [23503] ERROR: update or delete on table "room" violates foreign key constraint "reservation_room_id_fkey" on table "reservation"
-- Detail: Key (id)=(1212) is still referenced from table "reservation".

DELETE FROM room WHERE id = 1212
-- [23503] ERROR: update or delete on table "room" violates foreign key constraint "reservation_room_id_fkey" on table "reservation"
-- Detail: Key (id)=(1212) is still referenced from table "reservation".
