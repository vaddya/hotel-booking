ALTER TABLE IF EXISTS price
    DROP CONSTRAINT price_room_type_id_fkey,
    DROP COLUMN room_type_id,
    ADD COLUMN room_id BIGINT,
    ADD FOREIGN KEY (room_id) REFERENCES room (id);

ALTER TABLE IF EXISTS room_facility
    DROP CONSTRAINT room_facility_room_type_id_fkey,
    DROP COLUMN room_type_id,
    ADD COLUMN room_id BIGINT,
    ADD FOREIGN KEY (room_id) REFERENCES room (id);

ALTER TABLE IF EXISTS room_type
    DROP CONSTRAINT room_type_hotel_id_fkey,
    DROP COLUMN hotel_id;

ALTER TABLE IF EXISTS room
    ADD COLUMN hotel_id INTEGER,
    ADD FOREIGN KEY (hotel_id) REFERENCES hotel (id);
