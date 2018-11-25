SELECT *
FROM price
WHERE price.price = (SELECT MAX(price.price) FROM price);

DELETE
FROM price
WHERE price.price = (SELECT MAX(price.price) FROM price);

SELECT *
FROM price
WHERE price.price = (SELECT MAX(price.price) FROM price);