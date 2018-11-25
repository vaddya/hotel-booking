CREATE VIEW select_in AS
    SELECT *
    FROM reservation
    WHERE user_id IN (1, 2, 3)
      AND "from" < to_date('2017-01-31', 'YYYY-MM-DD');

CREATE VIEW select_between AS
    SELECT *
    FROM reservation
    WHERE price < 100 :: money
      AND "from" BETWEEN to_date('2017-01-01', 'YYYY-MM-DD')
              AND to_date('2017-01-31', 'YYYY-MM-DD');

CREATE VIEW select_like AS
    SELECT id, name, phone_number, city_id
    FROM "user"
    WHERE name LIKE 'вадим%'
      AND phone_number LIKE '1-%';

CREATE VIEW select_eval AS
    SELECT id, "to" - "from"                AS duration,
           price / NULLIF("to" - "from", 0) AS per_night
    FROM reservation
    LIMIT 10;

CREATE VIEW select_order AS
    SELECT *
    FROM reservation
    ORDER BY is_paid DESC, price DESC
    LIMIT 10;

CREATE VIEW select_summary AS
    SELECT COUNT(*)                       AS count,
           AVG("to" - "from")             AS avg_duration,
           AVG(price :: numeric) :: money AS avg_price,
           MAX(price)                     AS max_price,
           SUM(price)                     AS sum_price
    FROM reservation;

CREATE VIEW select_join_1 AS
    SELECT "from", "to", type, capacity
    FROM reservation
         JOIN room ON reservation.room_id = room.id
         JOIN room_type ON room.room_type_id = room_type.id
    LIMIT 10;

CREATE VIEW select_join_2 AS
    SELECT usr.name, city.name AS city, country.name AS country
    FROM "user" AS usr
         JOIN city ON usr.city_id = city.id
         JOIN country ON city.country_id = country.id
    LIMIT 10;

CREATE VIEW select_group AS
    SELECT user_id
    FROM reservation
    GROUP BY user_id
    HAVING COUNT(1) > 30;

CREATE VIEW select_subquery AS
    SELECT usr.id, usr.name, q.cnt
    FROM (SELECT user_id, COUNT(1) AS cnt
          FROM reservation
          GROUP BY user_id
          HAVING COUNT(1) > 30
         ) q
         JOIN "user" AS usr ON q.user_id = usr.id;

CREATE VIEW "quarter" AS
    WITH summary AS (
        WITH quarter_summary AS (
            SELECT city.id                      AS city_id,
                   EXTRACT(QUARTER FROM "from") AS quarter,
                   COUNT(*)                     AS count
            FROM reservation
                JOIN room ON room.id = reservation.room_id
                JOIN room_type ON room_type.id = room.room_type_id
                JOIN hotel ON hotel.id = room_type.hotel_id
                JOIN city ON hotel.city_id = city.id
            GROUP BY city.id, quarter
        )
        SELECT ROW_NUMBER() OVER (PARTITION BY quarter ORDER BY SUM(count) DESC) AS row_num,
               city_id                                                           AS city_id,
               quarter                                                           AS quarter,
               SUM(count)                                                        AS reservations
        FROM quarter_summary
        GROUP BY city_id, quarter
    )
    SELECT summary.quarter      AS quarter,
           city.name            AS city_name,
           country.name         AS country_name,
           summary.reservations AS reservations
    FROM summary
             JOIN city ON summary.city_id = city.id
             JOIN country ON city.country_id = country.id
    WHERE summary.row_num < 2
    ORDER BY summary.quarter, summary.reservations DESC;

CREATE VIEW travelling AS
    WITH summary AS (
        WITH increase_summary AS (
            SELECT ROW_NUMBER() OVER (PARTITION BY user_id ORDER BY "from") AS row_num,
                   price                                                    AS price,
                   user_id                                                  AS user_id
            FROM reservation
            WHERE is_paid AND user_id IN (
                SELECT user_id
                FROM reservation
                WHERE is_paid
                GROUP BY user_id
                HAVING COUNT(*) > 10
            )
        )
        SELECT curr.user_id                                       AS user_id,
               AVG((curr.price - prev.price) :: numeric) :: money AS avg_diff
        FROM increase_summary curr
             JOIN increase_summary prev
             ON curr.user_id = prev.user_id AND curr.row_num = prev.row_num + 1
        GROUP BY curr.user_id
        ORDER BY avg_diff DESC
        LIMIT 5
    )
    SELECT usr.id, usr.name, reservations, total, avg_diff
    FROM summary
         JOIN "user" AS usr ON user_id = usr.id
         JOIN (
             SELECT user_id    AS user_id,
                    COUNT(*)   AS reservations,
                    SUM(price) AS total
             FROM reservation
             GROUP BY user_id
         ) AS total ON total.user_id = usr.id;
