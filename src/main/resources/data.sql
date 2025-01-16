--INSERT INTO CP_PROFILE (name, cpf, phone, gender, birthdate, id_user)
--VALUES
--    ('Alice Silva', '12345678901', '11912345678', 'FEMALE', '1990-05-10', 1),
--    ('Bruno Costa', '98765432109', '21987654321', 'MALE', '1988-12-15', 2),
--    ('Carla Souza', NULL, '31998765432', 'FEMALE', '1995-07-22', 3),
--    ('Diego Lima', '45612378901', NULL, 'MALE', '1992-03-18', 4);
--
--INSERT INTO CP_USER (username, passwordHash, email, userType, idProfile)
--VALUES
--    ('user1', 'passwordHash1', 'user1@example.com', 'COMMON', 1),
--    ('user2', 'passwordHash2', 'user2@example.com', 'COMMON', 2),
--    ('admin1', 'passwordHash3', 'admin1@example.com', 'ADMIN', 3),
--    ('admin2', 'passwordHash4', 'admin2@example.com', 'ADMIN', 4);


INSERT INTO CP_ADDRESS (id, street, city, state, neighborhood, streetNumber, cep)
VALUES
    (1, 'Rua das Flores', 'São Paulo', 'SP', 'Centro', '123', '01000-000'),
    (2, 'Avenida Paulista', 'São Paulo', 'SP', 'Bela Vista', '456', '01310-000'),
    (3, 'Rua da Paz', 'Rio de Janeiro', 'RJ', 'Copacabana', '789', '22040-003'),
    (4, 'Rua do Sol', 'Belo Horizonte', 'MG', 'Savassi', '101', '30120-030');


