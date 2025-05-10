-- create sequence
create sequence oe.products_product_id_seq
start with 1
increment by 1
cache 1;

alter table oe.products
alter column product_id set default nextval('oe.products_product_id_seq');

alter sequence oe.products_product_id_seq
owned by oe.products.product_id;


-- add thumbnail picture field
alter table oe.products
add column thumbnail_picture varchar(255);

-- add fks
alter table oe.products
add constraint fk_category 
foreign key (category_id) references oe.categories(category_id)
on delete cascade;

alter table oe.suppliers
add constraint fk_supplier 
foreign key (supplier_id) references oe.suppliers(supplier_id)
on delete cascade;

