SELECT usr.id, usr.name
FROM "user" usr
WHERE usr.id NOT IN (SELECT user_id
                     FROM reservation
                     GROUP BY user_id
                    );

DELETE
FROM "user" AS usr
WHERE usr.id NOT IN (SELECT user_id
                     FROM reservation
                     GROUP BY user_id
                    );

SELECT usr.id, usr.name
FROM "user" usr
WHERE usr.id NOT IN (SELECT user_id
                     FROM reservation
                     GROUP BY user_id
                    );