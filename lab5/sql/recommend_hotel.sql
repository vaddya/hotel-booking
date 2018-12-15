CREATE OR REPLACE FUNCTION recommend_hotel(p_user_id INTEGER, p_rating_threshold INTEGER)
    RETURNS TABLE(hotel_id INTEGER)
LANGUAGE SQL
AS $$
WITH similiar_users AS (
    WITH liked_rooms AS (
        SELECT room.id AS room_id
        FROM reservation
            JOIN room ON reservation.room_id = room.id
            JOIN review ON review.reservation_id = reservation.id
        WHERE reservation.user_id = p_user_id
            AND review.rating >= p_rating_threshold
    )
    SELECT user_id AS user_id
    FROM reservation
        JOIN room ON reservation.room_id = room.id
        JOIN hotel ON hotel.id = room.hotel_id
        JOIN liked_rooms ON liked_rooms.room_id = room.id
        JOIN review ON reservation.id = review.reservation_id
    WHERE review.rating >= p_rating_threshold
        AND user_id != p_user_id
)
SELECT hotel_id AS hotel_id
FROM reservation
    JOIN similiar_users ON similiar_users.user_id = reservation.user_id
    JOIN room ON room.id = reservation.room_id
    JOIN review ON review.reservation_id = reservation.id
WHERE review.rating >= p_rating_threshold
    AND room.id NOT IN (
        SELECT room_id
        FROM reservation
        WHERE reservation.user_id = p_user_id
    )
ORDER BY review.rating DESC
LIMIT 5
$$;

SELECT hotel.name AS hotel_name, city.name AS city_name, country.name AS country_name
FROM hotel
     JOIN recommend_hotel(6, 4) AS recommendation ON recommendation.hotel_id = hotel.id
     JOIN city ON city.id = hotel.city_id
     JOIN country ON country.id = city.country_id;
