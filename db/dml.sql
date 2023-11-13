/* CRUD Operations /*

/* Create User */
INSERT INTO users(last_name, first_name, email_address, pword, num_updates, badge_id) VALUES($lname, $fname, $email, $pword, 0, 1);

/* Read User */
SELECT last_name, first_name, email_address, num_updates, badge_id FROM users WHERE user_id = $user_id;

/* Update User Details */
UPDATE users SET last_name = $lname, first_name = $fname, email_address = $email WHERE user_id = $user_id;

/* Update User Password */
UPDATE users SET pword = $pword WHERE user_id = $user_id;

/* Delete User */
DELETE FROM users WHERE user_id = $user_id;

/* Create Store */
INSERT INTO stores(store_name, street_address, city, state, zipcode) VALUES($store_name, $street_address, $city, $state, $zipcode);

/* Read Store */
SELECT store_name, street_address, city, state, zipcode FROM stores WHERE store_id = $store_id;

/* Read Item */
SELECT s.store_name, p.product_name, i.price, i.is_on_sale, i.price_update_date 
FROM items i JOIN stores s ON i.store_id = s.store_id 
JOIN products p ON i.product_id = p.product_id;

/* Create Item */
INSERT INTO items(price, is_on_sale, price_update_date, store_id, product_id, latest_update_user_id) VALUES ($price, $isonsale, NOW(), $store_id, $product_id, $user_id);

/* Update Item Price */
UPDATE items SET price = $price, is_on_sale = $isonsale, price_update_date = NOW(), latest_update_user_id = $user_id WHERE item_id = $item_id;

/* Create Shopping List */
INSERT INTO shopping_lists(user_id, list_name) VALUES($user_id, $list_name);

/* Read Shopping List */
SELECT s.list_name, p.product_name 
FROM shopping_lists s JOIN shopping_list_items i ON s.shopping_list_id = i.shopping_list_id
JOIN products p ON i.product_id = p.product_id
WHERE s.shopping_list_id = $shopping_list_id;

/* Add Item to Shopping List */
INSERT INTO shopping_list_items(shopping_list_id, product_id) VALUES ($shopping_list_id, $product_id);

/* Remove Item from Shopping List */
DELETE FROM shopping_list_items WHERE shopping_list_item_id = $shopping_list_item_id;

/* Delete Shopping List */
DELETE FROM shopping_lists WHERE shopping_list_id = $shopping_list_id;