-- Insert categories
insert into category (id, name, description) values
(nextval('category_seq'), 'Groceries', 'Daily essentials and food items'),
(nextval('category_seq'), 'Electronics', 'Smartphones, laptops, and accessories'),
(nextval('category_seq'), 'Apparel', 'Clothing and fashion items'),
(nextval('category_seq'), 'Home Appliances', 'Kitchen and home electrical items'),
(nextval('category_seq'), 'Beauty & Personal Care', 'Cosmetics and skincare products');

-- Insert products
insert into product (id, name, description, available_quantity, price, category_id) values
(nextval('product_seq'), 'Fortune Sunflower Oil', '5-liter sunflower oil for cooking', 100, 800.00,
    (select id from category where name = 'Groceries')),
(nextval('product_seq'), 'Aashirvaad Atta', 'Whole wheat flour - 10kg', 50, 450.00,
    (select id from category where name = 'Groceries')),
(nextval('product_seq'), 'Apple iPhone 15', '128GB, Midnight Black', 30, 79999.00,
    (select id from category where name = 'Electronics')),
(nextval('product_seq'), 'Samsung 55-inch Smart TV', 'Ultra HD 4K LED TV', 20, 55000.00,
    (select id from category where name = 'Electronics')),
(nextval('product_seq'), 'Levi’s Men Jeans', 'Slim Fit, Indigo Blue', 200, 2499.00,
    (select id from category where name = 'Apparel')),
(nextval('product_seq'), 'Bata Women Sandals', 'Casual everyday wear', 150, 999.00,
    (select id from category where name = 'Apparel')),
(nextval('product_seq'), 'Prestige Gas Stove', '4-burner glass top stove', 80, 7499.00,
    (select id from category where name = 'Home Appliances')),
(nextval('product_seq'), 'Philips Mixer Grinder', '750W with 3 jars', 60, 3299.00,
    (select id from category where name = 'Home Appliances')),
(nextval('product_seq'), 'Lakme Compact Powder', 'Radiance natural finish', 500, 299.00,
    (select id from category where name = 'Beauty & Personal Care')),
(nextval('product_seq'), 'Nivea Body Lotion', 'Deep nourishing formula - 400ml', 400, 399.00,
    (select id from category where name = 'Beauty & Personal Care'));

-- Commit the transaction (if needed)
commit;
