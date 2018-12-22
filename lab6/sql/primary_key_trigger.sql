CREATE OR REPLACE FUNCTION room_type_primary_key()
    RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
BEGIN
    SELECT nextval('room_type_id_seq') + 100 INTO new.id;
    RETURN new;
END;
$$;

CREATE TRIGGER room_type_primary_key
    BEFORE INSERT
    ON room_type
    FOR EACH ROW EXECUTE PROCEDURE room_type_primary_key();

SELECT *
FROM room_type
WHERE id = (SELECT MAX(id) FROM room_type);
-- id, type,   capacity, description
-- 9,  Studio, 4,        A room with a studio bed - a couch that can be converted into a bed

INSERT INTO room_type
VALUES (DEFAULT, 'Twin', 2, 'A room with two beds');

SELECT *
FROM room_type
WHERE id = (SELECT MAX(id) FROM room_type);
-- id,  type, capacity, description
-- 110, Twin, 2,        A room with two beds
