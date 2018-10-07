SELECT name, checkin_from, checkout_until
FROM hotel
       JOIN house_rules ON hotel.id = house_rules.id;

SELECT name AS hotel_name, type, room_type.description
FROM room_type
       JOIN hotel ON room_type.hotel_id = hotel.id;

SELECT hotel.name AS hotel_name, room.name, type, room_type.description
FROM room
       JOIN room_type ON room.room_type_id = room_type.id
       JOIN hotel ON room_type.hotel_id = hotel.id;

SELECT "from", "to", is_paid, room.name
FROM reservation
       JOIN room ON reservation.room_id = room.id
       JOIN room_type ON room.room_type_id = room_type.id
       JOIN hotel ON room_type.hotel_id = hotel.id;

SELECT guest.name, "from", "to", price
FROM guest
       JOIN reservation ON guest.reservation_id = reservation.id;

SELECT price, rating
FROM review
       JOIN reservation ON review.reservation_id = reservation.id;

SELECT room_type.type, "from", "to", price
FROM price
       JOIN room_type ON price.room_type_id = room_type.id;

SELECT hotel.name            AS hotel,
       room_type.type        AS room_type,
       room_type.description AS room_description,
       facility.name         AS facility
FROM room_type
       JOIN room_facility on room_facility.room_type_id = room_type.id
       JOIN facility on room_facility.facility_id = facility.id
       JOIN hotel on room_type.hotel_id = hotel.id;