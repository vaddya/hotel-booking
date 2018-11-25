SELECT COUNT(*), AVG("to" - "from"), AVG(price :: numeric), MAX(price), SUM(price)
FROM reservation