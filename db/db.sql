CREATE DATABASE shopping_app;

CREATE TABLE stores (
    store_id serial PRIMARY KEY,
    store_name VARCHAR(50) NOT NULL,
    street_address VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(2) NOT NULL,
    zipcode VARCHAR(5) NOT NULL
);

CREATE TABLE products (
    product_id serial PRIMARY KEY,
    product_name VARCHAR(50) NOT NULL,
    category VARCHAR(50)
);

CREATE TABLE badges (
    badge_id serial PRIMARY KEY,
    badge_name VARCHAR(50) NOT NULL
);

CREATE TABLE users (
    user_id serial PRIMARY KEY,
    last_name VARCHAR(50) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    email_address VARCHAR(50) NOT NULL,
    pword VARCHAR(50) NOT NULL,
    num_updates INT NOT NULL,
    badge_id serial NOT NULL,
    CONSTRAINT fk_user_badge FOREIGN KEY(badge_id) REFERENCES badges(badge_id)
);

CREATE TABLE items (
    item_id serial PRIMARY KEY,
    store_id serial,
    product_id serial,
    latest_update_user_id serial,
    price decimal NOT NULL,
    is_on_sale boolean NOT NULL,
    price_update_date timestamp NOT NULL,
    CONSTRAINT fk_store_id FOREIGN KEY(store_id) REFERENCES stores(store_id),
    CONSTRAINT fk_product_id FOREIGN KEY(product_id) REFERENCES products(product_id),
    CONSTRAINT fk_user_id FOREIGN KEY(latest_update_user_id) REFERENCES users(user_id)
);

CREATE TABLE shopping_lists (
    shopping_list_id serial PRIMARY KEY,
    list_name VARCHAR(50),
    user_id serial,
    CONSTRAINT fk_user_id FOREIGN KEY(user_id) REFERENCES users(user_id)
);

CREATE TABLE shopping_list_items (
    shopping_list_item_id serial PRIMARY KEY,
    shopping_list_id serial,
    product_id serial,
    CONSTRAINT fk_shopping_list_id FOREIGN KEY(shopping_list_id) REFERENCES shopping_lists(shopping_list_id),
    CONSTRAINT fk_product_id FOREIGN KEY(product_id) REFERENCES products(product_id)
);