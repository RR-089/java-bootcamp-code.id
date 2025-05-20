
drop table oe.cart_items;

CREATE TABLE IF NOT EXISTS oe.cart_items
(
    cart_id integer NOT NULL,
    product_id integer NOT NULL,
    quantity smallint,
    unit_price real,
    discount decimal(2,2),
	created_date  timestamp default current_timestamp,
    modified_date timestamp,
    CONSTRAINT pk_cart_items PRIMARY KEY (cart_id, product_id),
    CONSTRAINT fk_cart_cart_items FOREIGN KEY (cart_id)
        REFERENCES oe.carts (cart_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)