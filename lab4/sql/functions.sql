CREATE FUNCTION IncreaseZeroPrice()
    RETURNS void AS
$$
BEGIN
    UPDATE reservation
    SET price = 1 :: money
    WHERE price = 0 :: money;
END;
$$
LANGUAGE plpgsql;

CREATE FUNCTION DeleteMaxPrice()
    RETURNS void AS
$$
BEGIN
    DELETE
    FROM price
    WHERE price.price = (SELECT MAX(price.price)
                         FROM price
                        );
END;
$$
LANGUAGE plpgsql;

CREATE FUNCTION DeleteUsersWithoutReservations()
    RETURNS void AS
$$
BEGIN
    DELETE
    FROM "user" AS usr
    WHERE usr.id NOT IN (SELECT user_id
                         FROM reservation
                         GROUP BY user_id
                        );
END;
$$
LANGUAGE plpgsql;