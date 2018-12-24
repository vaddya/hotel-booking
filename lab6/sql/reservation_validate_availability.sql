CREATE OR REPLACE FUNCTION reservation_validate_availability()
    RETURNS trigger
LANGUAGE plpgsql
AS $$
DECLARE
    conflicts BIGINT [];
BEGIN
    conflicts := ARRAY(
        SELECT id
        FROM reservation
        WHERE id != new.id AND room_id = new.room_id
            AND "to" > new."from" AND "from" < new."to"
    );
    IF array_length(conflicts, 1) > 0 THEN
        RAISE EXCEPTION 'Room is not available. Conflicts: %', conflicts;
    END IF;
    RETURN new;
END;
$$;

CREATE TRIGGER reservation_validate_availability
    BEFORE INSERT OR UPDATE
    ON reservation
    FOR EACH ROW EXECUTE PROCEDURE reservation_validate_availability();
