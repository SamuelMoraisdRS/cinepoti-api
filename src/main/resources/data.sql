INSERT INTO CP_ADDRESS (street, city, state, neighborhood, street_number, cep)
VALUES ('Main Street', 'Springfield', 'Illinois', 'Downtown', '123', '62704-123');

INSERT INTO cp_MOVIE (title, duration, release_date, rating, synopsis)
VALUES ('Inception', 148, '2010-07-16', 8.8, 'A skilled thief is given a chance at redemption if he can successfully perform inception.');

INSERT INTO CP_CINEMA_ROOM (name, address_id)
VALUES
( 'Cinema Room 1', 1);

INSERT INTO CP_SEAT (id, seat_row, seat_number, cinema_room_id, price)
VALUES
(1, 'A', '1', 1, 15.50);
