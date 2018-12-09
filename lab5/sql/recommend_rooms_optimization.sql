CREATE OR REPLACE FUNCTION recommend_rooms_optimization(p_hotel_id        INTEGER,
                                                        p_years           INTEGER,
                                                        p_upper_threshold INTEGER,
                                                        p_lower_threshold INTEGER)
    RETURNS TABLE(room_type_id BIGINT, increase BOOLEAN, decrease BOOLEAN)
LANGUAGE SQL
AS $$
WITH reservatrions_per_room_type AS (
    SELECT room_type_id AS room_type_id,
           COUNT(*)     AS reservations_count
    FROM reservation
        JOIN room ON reservation.room_id = room.id
        JOIN room_type ON room.room_type_id = room_type.id
    WHERE date_part('Y', now()) - date_part('Y', reservation.from) <= p_years
        AND hotel_id = p_hotel_id
    GROUP BY room_type_id
),
rooms_per_room_type AS (
    SELECT room_type_id AS room_type_id,
           COUNT(*)     AS room_count
    FROM room
        JOIN room_type ON room.room_type_id = room_type.id
    WHERE hotel_id = p_hotel_id
    GROUP BY room_type_id
)
SELECT summary.room_type_id              AS room_type_id,
       summary.ratio > p_upper_threshold AS increase,
       summary.ratio < p_lower_threshold AS decrease
FROM (
    SELECT rooms_per_room_type.room_type_id AS room_type_id,
           reservations_count / room_count  AS ratio
    FROM reservatrions_per_room_type
        JOIN rooms_per_room_type ON rooms_per_room_type.room_type_id = reservatrions_per_room_type.room_type_id
        JOIN room_type ON room_type.id = rooms_per_room_type.room_type_id
    ORDER BY room_type_id
) AS summary
$$;

SELECT *
FROM recommend_rooms_optimization(5, 2, 30, 15);
