create sequence oe.categories_category_id_seq
start with 9
increment by 1
cache 1;

alter table oe.categories
alter column category_id set default nextval('oe.categories_category_id_seq');

alter sequence oe.categories_category_id_seq
owned by oe.categories.category_id;