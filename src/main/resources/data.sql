INSERT INTO CP_USER (username, password_hash, email, user_type,registration_date)
VALUES
   ('user1', 'passwordHash1', 'user1@example.com', 'COMMON','2005-12-31 23:59:59'),
   ('user2', 'passwordHash2', 'user2@example.com', 'COMMON','2005-12-31 23:59:59'),
   ('admin1', 'passwordHash3', 'admin1@example.com', 'ADMIN','2005-12-31 23:59:59'),
   ('admin2', 'passwordHash4', 'admin2@example.com', 'ADMIN','2005-12-31 23:59:59');

INSERT INTO CP_PROFILE (name, cpf, phone, gender, birthdate, id_user)
VALUES
   ('Alice Silva', '12345678901', '11912345678', 'FEMALE', '1990-05-10', 1),
   ('Bruno Costa', '98765432109', '21987654321', 'MALE', '1988-12-15', 2),
   ('Carla Souza', NULL, '31998765432', 'FEMALE', '1995-07-22', 3),
   ('Diego Lima', '45612378901', NULL, 'MALE', '1992-03-18', 4);

INSERT INTO CP_ADDRESS (street, city, state, neighborhood, street_number, cep)
VALUES
    ('Rua das Flores', 'São Paulo', 'SP', 'Centro', '123', '01000-000'),
    ('Avenida Paulista', 'São Paulo', 'SP', 'Bela Vista', '456', '01310-000'),
    ('Rua da Paz', 'Rio de Janeiro', 'RJ', 'Copacabana', '789', '22040-003'),
    ('Rua do Sol', 'Belo Horizonte', 'MG', 'Savassi', '101', '30120-030');
