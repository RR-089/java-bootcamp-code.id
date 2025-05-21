create sequence oe.orders_order_id_seq
start with 1
increment by 1
cache 1;

alter table oe.orders
alter column order_id set default nextval('oe.orders_order_id_seq');