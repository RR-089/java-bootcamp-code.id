drop table oe.product_images;

create table oe.product_images (
  product_image_id serial primary key,
  file_name varchar(255) not null,
  file_size decimal(10, 2) not null,
  file_type varchar(100) not null,
  file_uri varchar(255) not null,
  index integer,
  product_id integer not null references oe.products (product_id) on delete cascade
);
