--
-- PostgreSQL database dump
--

-- Dumped from database version 17.4
-- Dumped by pg_dump version 17.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Data for Name: shippers; Type: TABLE DATA; Schema: oe; Owner: postgres
--

INSERT INTO oe.shippers (shipper_id, company_name, phone, created_date, modified_date) VALUES (1, 'Speedy Express', '(503) 555-9831', '2025-05-07 01:33:36.022865', NULL);
INSERT INTO oe.shippers (shipper_id, company_name, phone, created_date, modified_date) VALUES (2, 'United Package', '(503) 555-3199', '2025-05-07 01:33:36.022865', NULL);
INSERT INTO oe.shippers (shipper_id, company_name, phone, created_date, modified_date) VALUES (3, 'Federal Shipping', '(503) 555-9931', '2025-05-07 01:33:36.022865', NULL);
INSERT INTO oe.shippers (shipper_id, company_name, phone, created_date, modified_date) VALUES (4, 'Alliance Shippers', '1-800-222-0451', '2025-05-07 01:33:36.022865', NULL);
INSERT INTO oe.shippers (shipper_id, company_name, phone, created_date, modified_date) VALUES (5, 'UPS', '1-800-782-7892', '2025-05-07 01:33:36.022865', NULL);
INSERT INTO oe.shippers (shipper_id, company_name, phone, created_date, modified_date) VALUES (6, 'DHL', '1-800-225-5345', '2025-05-07 01:33:36.022865', NULL);


--
-- PostgreSQL database dump complete
--


create sequence oe.shippers_shipper_id_seq
start with 7
increment by 1
cache 1;

alter table oe.shippers
alter column shipper_id set default nextval('oe.shippers_shipper_id_seq');

alter sequence oe.shippers_shipper_id_seq
owned by oe.shippers.shipper_id;
