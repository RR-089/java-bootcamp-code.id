create sequence if not exists oe.carts_cart_id_seq
start with 1
increment by 1
cache 1;

CREATE TABLE IF NOT EXISTS oe.carts
(
    cart_id integer NOT NULL DEFAULT nextval('oe.carts_cart_id_seq'::regclass),
    created_on timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    user_id integer,
    CONSTRAINT pk_carts PRIMARY KEY (cart_id),
    CONSTRAINT uq_carts_user UNIQUE (user_id),
    created_date  timestamp default current_timestamp,
    modified_date timestamp
);


CREATE TABLE IF NOT EXISTS oe.cart_items
(
    cart_item_id serial not null,
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