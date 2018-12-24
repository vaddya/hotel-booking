CREATE OR REPLACE FUNCTION review_cancellation()
    RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
BEGIN
    DELETE FROM review
    WHERE review.reservation_id = new.reservation_id;
    RETURN new;
END;
$$;

CREATE TRIGGER review_cancellation
    AFTER INSERT
    ON cancellation
    FOR EACH ROW EXECUTE PROCEDURE review_cancellation();
