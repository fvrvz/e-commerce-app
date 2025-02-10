-- Create the category sequence first
create sequence if not exists category_seq increment by 50 start with 1;

-- Create the product sequence
create sequence if not exists product_seq increment by 50 start with 1;

-- Create the category table
create table if not exists category (
    id integer not null primary key,
    description varchar(255),
    name varchar(255)
);

-- Create the product table
create table if not exists product (
    id integer not null primary key,
    description varchar(255),
    name varchar(255),
    available_quantity double precision not null,
    price numeric(38, 2),
    category_id integer,
    constraint fk_product_category foreign key (category_id) references category (id)
);
