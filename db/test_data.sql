INSERT INTO crud_operations_stores(store_name, street_address, city, state, zipcode) VALUES('Fred Meyer', '11425 SW Beaverton Hillsdale Hwy', 'Beaverton', 'OR', '97005');
INSERT INTO crud_operations_stores(store_name, street_address, city, state, zipcode) VALUES('Safeway', '2177 NW 185th Ave', 'Hillsboro', 'OR', '97124');
INSERT INTO crud_operations_stores(store_name, street_address, city, state, zipcode) VALUES('Albertsons', '7500 SW Baseline Rd', 'Hillsboro', 'OR', '97123');

INSERT INTO products(product_name, category) VALUES('banana', 'fruit');
INSERT INTO products(product_name, category) VALUES('apple', 'fruit');
INSERT INTO products(product_name, category) VALUES('ice cream', 'dessert');

INSERT INTO badges(badge_name) VALUES('Novice Shopper');
INSERT INTO badges(badge_name) VALUES('Intermediate Shopper');
INSERT INTO badges(badge_name) VALUES('Master Shopper');

INSERT INTO users(last_name, first_name, email_address, pword, num_updates, badge_id) VALUES('Critchfield', 'Nick', 'nicholas.critchfield@gmail.com', '1234', 0, 1);
INSERT INTO users(last_name, first_name, email_address, pword, num_updates, badge_id) VALUES('test1_lname', 'test1_fname', 'test1@gmail.com', '5678', 0, 1);
INSERT INTO users(last_name, first_name, email_address, pword, num_updates, badge_id) VALUES('test2_lname', 'test2_fname', 'test2@gmail.com', 'abcd', 0, 1);

-- product 1
INSERT INTO items(price, is_on_sale, price_update_date, store_id, product_id, latest_update_user_id) VALUES (0.69, false, NOW(), 1, 1, 1);
INSERT INTO items(price, is_on_sale, price_update_date, store_id, product_id, latest_update_user_id) VALUES (0.49, true, NOW(), 2, 1, 2);
INSERT INTO items(price, is_on_sale, price_update_date, store_id, product_id, latest_update_user_id) VALUES (0.89, false, NOW(), 3, 1, 3);
-- product 2
INSERT INTO items(price, is_on_sale, price_update_date, store_id, product_id, latest_update_user_id) VALUES (0.59, false, NOW(), 3, 2, 3);
INSERT INTO items(price, is_on_sale, price_update_date, store_id, product_id, latest_update_user_id) VALUES (0.49, false, NOW(), 3, 2, 2);
INSERT INTO items(price, is_on_sale, price_update_date, store_id, product_id, latest_update_user_id) VALUES (0.39, true, NOW(), 3, 2, 1);
-- product 3
INSERT INTO items(price, is_on_sale, price_update_date, store_id, product_id, latest_update_user_id) VALUES (3.99, true, NOW(), 1, 3, 2);
INSERT INTO items(price, is_on_sale, price_update_date, store_id, product_id, latest_update_user_id) VALUES (4.49, false, NOW(), 2, 3, 3);
INSERT INTO items(price, is_on_sale, price_update_date, store_id, product_id, latest_update_user_id) VALUES (4.49, false, NOW(), 3, 3, 1);

INSERT INTO shopping_lists(user_id, list_name) VALUES(1, 'List 1 - Nick');
INSERT INTO shopping_lists(user_id, list_name) VALUES(2, 'Test List 1');
INSERT INTO shopping_lists(user_id, list_name) VALUES(3, 'Test List 2');

INSERT INTO shopping_list_items(shopping_list_id, product_id) VALUES(1, 1);
INSERT INTO shopping_list_items(shopping_list_id, product_id) VALUES(1, 2);
INSERT INTO shopping_list_items(shopping_list_id, product_id) VALUES(2, 2);
INSERT INTO shopping_list_items(shopping_list_id, product_id) VALUES(2, 3);
INSERT INTO shopping_list_items(shopping_list_id, product_id) VALUES(3, 1);
INSERT INTO shopping_list_items(shopping_list_id, product_id) VALUES(3, 3);