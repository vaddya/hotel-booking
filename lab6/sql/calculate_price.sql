CREATE OR REPLACE FUNCTION calculate_price()
    RETURNS trigger
LANGUAGE plpgsql
AS $$
BEGIN
    SELECT SUM(price.price) INTO new.price
    FROM generate_series(new."from", new."to" - INTERVAL '1 day', '1 day') AS date
         JOIN price ON price.room_id = 1 AND date BETWEEN "from" AND "to";
    RETURN new;
END;
$$;

CREATE TRIGGER calculate_price
    BEFORE INSERT OR UPDATE
    ON reservation
    FOR EACH ROW EXECUTE PROCEDURE calculate_price();
