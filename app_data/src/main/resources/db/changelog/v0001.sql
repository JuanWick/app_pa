create table "cart" (
  id integer not null,
  user_id integer not null references user(id),
  primary key (id)
);

create table "category" (
  id integer not null,
  name varchar (255) not null,
  primary key (id)
);

create table "product" (
  id integer not null,
  name varchar (255) not null,
  info varchar (255) not null,
  barre_code varchar (255) not null,
  primary key (id)
);

create table "role" (
  id integer not null,
  name varchar (255) not null,
  primary key (id)
);

create table "store" (
  id integer not null,
  address varchar (255) not null,
  zipcode varchar (255) not null,
  city varchar (255) not null,
  country varchar (255) not null,
  user_id integer not null references user(id),
  primary key (id)
);

create table "user" (
  id integer not null,
  name varchar (255) not null,
  first_name varchar (255) not null,
  primary key (id)
);

create table "cart_products" (
  cart_id integer not null references cart(id),
  product_id integer not null references product(id),
);

create table "loyalty_card" (
  user_id integer not null references user(id),
  store_id integer not null references store(id),
);

create table "product_category" (
  product_id integer not null references product(id),
  category_id integer not null references category(id),
);

create table "shared_cart" (
  user_id integer not null references user(id),
  cart_id integer not null references cart(id),
);

create table "store_product" (
  store_id integer not null references store(id),
  product_id integer not null references product(id),
);

create table "user_role" (
  user_id integer not null references user(id),
  role_id integer not null references role(id),
);