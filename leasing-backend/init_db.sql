-- Création de la base de données si elle n'existe pas
CREATE DATABASE IF NOT EXISTS leasing_db;

-- Création de l'utilisateur kata_user s'il n'existe pas
CREATE USER IF NOT EXISTS 'kata_user'@'%' IDENTIFIED WITH mysql_native_password BY 'kata_password';

-- Affectation des droits à l'utilisateur sur leasing_db

GRANT ALL PRIVILEGES ON leasing_db.* TO 'kata_user'@'%';
FLUSH PRIVILEGES;


-- Utilisation de la base de données
USE leasing_db;
CREATE TABLE IF NOT EXISTS customer (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    blacklisted BOOLEAN NOT NULL DEFAULT false
);

CREATE TABLE IF NOT EXISTS car (
    id SERIAL PRIMARY KEY,
    brand VARCHAR(100),
    model VARCHAR(100),
    type VARCHAR(50),
    location VARCHAR(255),
    base_price_per_day NUMERIC,
    leased BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS lease (
    id SERIAL PRIMARY KEY,
    customer_id BIGINT NOT NULL REFERENCES customer(id),
    car_id BIGINT NOT NULL REFERENCES car(id),
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP,
    actual_return_date TIMESTAMP
);

INSERT INTO customer (name, blacklisted) VALUES ('Alice', false);
INSERT INTO customer (name, blacklisted) VALUES ('Bob', false);

INSERT INTO car (brand, model, type, location, base_price_per_day, leased) VALUES
('Toyota', 'Corolla', 'SEDAN', 'Paris', 30, false),
('BMW', 'X5', 'SUV', 'Lyon', 50, false);


-- Insertion de contrats de location (leases)
INSERT INTO lease (customer_id, car_id, start_date, end_date, actual_return_date)
VALUES
    (1, 1, '2024-06-01 10:00:00', '2024-06-05 10:00:00', '2024-06-05 09:00:00'), -- Alice loue la Toyota
    (2, 2, '2024-06-10 14:00:00', '2024-06-15 10:00:00', NULL); -- Bob loue la BMW (en cours)

-- Mettre à jour le statut des voitures comme louées si nécessaire
UPDATE car SET leased = true WHERE id IN (2); -- La BMW est toujours louée
