CREATE OR REPLACE FUNCTION recommend_hotel(p_user_id INTEGER)
    RETURNS TABLE(room_type_id BIGINT, increase BOOLEAN, decrease BOOLEAN)
LANGUAGE SQL
AS $$
SELECT *
FROM reservation
JOIN review ON review.reservation_id = reservation.id
WHERE reservation.user_id = 666
AND review.rating >= 4
$$;

SELECT *
FROM recommend_hotel(5, 3, 30, 15);


