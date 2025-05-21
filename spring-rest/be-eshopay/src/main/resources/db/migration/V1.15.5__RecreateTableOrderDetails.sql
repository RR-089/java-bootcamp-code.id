drop table if exists oe.order_details;

CREATE TABLE oe.order_details (
    order_id smallint NOT NULL,
    product_id smallint NOT NULL,
    unit_price real NOT NULL,
    quantity smallint NOT NULL,
    discount real NOT NULL,
    CONSTRAINT pk_order_details PRIMARY KEY (order_id, product_id),
    CONSTRAINT fk_order_order_details FOREIGN KEY (order_id)
        REFERENCES oe.orders (order_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_product_order_details FOREIGN KEY (product_id)
        REFERENCES oe.products (product_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
    
);