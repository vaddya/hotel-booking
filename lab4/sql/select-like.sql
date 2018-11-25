SELECT id, name, phone_number, city_id
FROM "user"
WHERE name LIKE 'вадим%'
  AND phone_number LIKE '1-%';