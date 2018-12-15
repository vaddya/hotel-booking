SELECT id, name, phone_number, city_id
FROM "user"
WHERE name LIKE 'l%'
  AND phone_number LIKE '1-666-%';
