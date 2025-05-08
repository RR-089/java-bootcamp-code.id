DROP TABLE IF EXISTS oe.order_details;
DROP TABLE IF EXISTS oe.orders;
DROP TABLE IF EXISTS oe.products;
DROP TABLE IF EXISTS oe.shippers;
DROP TABLE IF EXISTS oe.suppliers;
DROP TABLE IF EXISTS oe.categories;


--
-- Name: products; Type: TABLE; Schema: public; Owner: -; Tablespace:
--

CREATE TABLE oe.products (
    product_id smallint NOT NULL primary key,
    product_name varchar(40) NOT NULL,
    supplier_id smallint,
    category_id smallint,
    quantity_per_unit varchar(20),
    unit_price real,
    units_in_stock smallint,
    units_on_order smallint,
    reorder_level smallint,
    discontinued integer NOT NULL,
	created_date  timestamp default current_timestamp,
    modified_date timestamp
);



CREATE TABLE oe.shippers (
    shipper_id smallint NOT NULL primary key,
    company_name varchar(40) NOT NULL,
    phone varchar(24),
	created_date  timestamp default current_timestamp,
    modified_date timestamp
);

--
-- Name: suppliers; Type: TABLE; Schema: public; Owner: -; Tablespace:
--

CREATE TABLE oe.suppliers (
    supplier_id smallint NOT NULL primary key,
    company_name varchar(40) NOT NULL,
	created_date  timestamp default current_timestamp,
    modified_date timestamp
);



--
-- Name: categories; Type: TABLE; Schema: public; Owner: -; Tablespace:
--

CREATE TABLE oe.categories (
    category_id smallint NOT NULL primary key,
    category_name varchar(15) NOT NULL,
    description text,
    picture bytea,
	created_date  timestamp default current_timestamp,
    modified_date timestamp
);


--
-- Name: order_details; Type: TABLE; Schema: public; Owner: -; Tablespace:
--

CREATE TABLE oe.order_details (
    order_id smallint NOT NULL primary key,
    product_id smallint NOT NULL,
    unit_price real NOT NULL,
    quantity smallint NOT NULL,
    discount real NOT NULL
);


--
-- Name: orders; Type: TABLE; Schema: public; Owner: -; Tablespace:
--

CREATE TABLE IF NOT EXISTS oe.orders
(
    order_id smallint NOT NULL,
    order_date date,
    required_date date,
    shipped_date date,
    ship_via smallint,
    freight real,
    ship_name varchar(40),
    total_discount decimal(5,2),
    total_amount decimal(8,2),
    payment_type varchar(15),
    transac_no varchar(25),
    transac_date timestamp without time zone,
    location_id integer,
    user_id integer,
    employee_id integer,
	created_date  timestamp default current_timestamp,
    modified_date timestamp,
    CONSTRAINT pk_orders PRIMARY KEY (order_id),
    CONSTRAINT fk_order_location FOREIGN KEY (location_id)
        REFERENCES hr.locations (location_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_orders_hr_employee FOREIGN KEY (employee_id)
        REFERENCES hr.employees (employee_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_orders_shippers FOREIGN KEY (ship_via)
        REFERENCES oe.shippers (shipper_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT orders_payment_type_check CHECK (payment_type::text = ANY (ARRAY['DEBIT'::varchar::text, 'CREDIT'::varchar::text, 'QRIS'::varchar::text, 'TRANSFER'::varchar::text]))
);


