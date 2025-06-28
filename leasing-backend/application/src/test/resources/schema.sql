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
