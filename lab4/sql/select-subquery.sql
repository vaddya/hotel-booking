SELECT usr.id, usr.name, q.cnt
FROM (SELECT user_id, COUNT(1) AS cnt
      FROM reservation
      GROUP BY user_id
      HAVING COUNT(1) > 30
     ) q
     JOIN "user" AS usr ON q.user_id = usr.id;
