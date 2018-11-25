SELECT usr.name, city.name AS city, country.name AS country
FROM "user" AS usr
     JOIN city ON usr.city_id = city.id
     JOIN country ON city.country_id = country.id
LIMIT 10;
