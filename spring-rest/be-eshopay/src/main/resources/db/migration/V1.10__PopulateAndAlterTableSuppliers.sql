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
-- Data for Name: suppliers; Type: TABLE DATA; Schema: oe; Owner: postgres
--

INSERT INTO oe.suppliers (supplier_id, company_name, created_date, modified_date) VALUES (1, 'Exotic Liquids', '2025-05-07 01:33:21.034363', NULL);
INSERT INTO oe.suppliers (supplier_id, company_name, created_date, modified_date) VALUES (2, 'New Orleans Cajun Delights', '2025-05-07 01:33:21.034363', NULL);
INSERT INTO oe.suppliers (supplier_id, company_name, created_date, modified_date) VALUES (3, 'Grandma Kelly''s Homestead', '2025-05-07 01:33:21.034363', NULL);
INSERT INTO oe.suppliers (supplier_id, company_name, created_date, modified_date) VALUES (4, 'Tokyo Traders', '2025-05-07 01:33:21.034363', NULL);
INSERT INTO oe.suppliers (supplier_id, company_name, created_date, modified_date) VALUES (5, 'Cooperativa de Quesos ''Las Cabras''', '2025-05-07 01:33:21.034363', NULL);
INSERT INTO oe.suppliers (supplier_id, company_name, created_date, modified_date) VALUES (6, 'Mayumi''s', '2025-05-07 01:33:21.034363', NULL);
INSERT INTO oe.suppliers (supplier_id, company_name, created_date, modified_date) VALUES (7, 'Pavlova, Ltd.', '2025-05-07 01:33:21.034363', NULL);
INSERT INTO oe.suppliers (supplier_id, company_name, created_date, modified_date) VALUES (8, 'Specialty Biscuits, Ltd.', '2025-05-07 01:33:21.034363', NULL);
INSERT INTO oe.suppliers (supplier_id, company_name, created_date, modified_date) VALUES (9, 'PB Knäckebröd AB', '2025-05-07 01:33:21.034363', NULL);
INSERT INTO oe.suppliers (supplier_id, company_name, created_date, modified_date) VALUES (10, 'Refrescos Americanas LTDA', '2025-05-07 01:33:21.034363', NULL);
INSERT INTO oe.suppliers (supplier_id, company_name, created_date, modified_date) VALUES (11, 'Heli Süßwaren GmbH & Co. KG', '2025-05-07 01:33:21.034363', NULL);
INSERT INTO oe.suppliers (supplier_id, company_name, created_date, modified_date) VALUES (12, 'Plutzer Lebensmittelgroßmärkte AG', '2025-05-07 01:33:21.034363', NULL);
INSERT INTO oe.suppliers (supplier_id, company_name, created_date, modified_date) VALUES (13, 'Nord-Ost-Fisch Handelsgesellschaft mbH', '2025-05-07 01:33:21.034363', NULL);
INSERT INTO oe.suppliers (supplier_id, company_name, created_date, modified_date) VALUES (14, 'Formaggi Fortini s.r.l.', '2025-05-07 01:33:21.034363', NULL);
INSERT INTO oe.suppliers (supplier_id, company_name, created_date, modified_date) VALUES (15, 'Norske Meierier', '2025-05-07 01:33:21.034363', NULL);
INSERT INTO oe.suppliers (supplier_id, company_name, created_date, modified_date) VALUES (16, 'Bigfoot Breweries', '2025-05-07 01:33:21.034363', NULL);
INSERT INTO oe.suppliers (supplier_id, company_name, created_date, modified_date) VALUES (17, 'Svensk Sjöföda AB', '2025-05-07 01:33:21.034363', NULL);
INSERT INTO oe.suppliers (supplier_id, company_name, created_date, modified_date) VALUES (18, 'Aux joyeux ecclésiastiques', '2025-05-07 01:33:21.034363', NULL);
INSERT INTO oe.suppliers (supplier_id, company_name, created_date, modified_date) VALUES (19, 'New England Seafood Cannery', '2025-05-07 01:33:21.034363', NULL);
INSERT INTO oe.suppliers (supplier_id, company_name, created_date, modified_date) VALUES (20, 'Leka Trading', '2025-05-07 01:33:21.034363', NULL);
INSERT INTO oe.suppliers (supplier_id, company_name, created_date, modified_date) VALUES (21, 'Lyngbysild', '2025-05-07 01:33:21.034363', NULL);
INSERT INTO oe.suppliers (supplier_id, company_name, created_date, modified_date) VALUES (22, 'Zaanse Snoepfabriek', '2025-05-07 01:33:21.034363', NULL);
INSERT INTO oe.suppliers (supplier_id, company_name, created_date, modified_date) VALUES (23, 'Karkki Oy', '2025-05-07 01:33:21.034363', NULL);
INSERT INTO oe.suppliers (supplier_id, company_name, created_date, modified_date) VALUES (24, 'G''day, Mate', '2025-05-07 01:33:21.034363', NULL);
INSERT INTO oe.suppliers (supplier_id, company_name, created_date, modified_date) VALUES (25, 'Ma Maison', '2025-05-07 01:33:21.034363', NULL);
INSERT INTO oe.suppliers (supplier_id, company_name, created_date, modified_date) VALUES (26, 'Pasta Buttini s.r.l.', '2025-05-07 01:33:21.034363', NULL);
INSERT INTO oe.suppliers (supplier_id, company_name, created_date, modified_date) VALUES (27, 'Escargots Nouveaux', '2025-05-07 01:33:21.034363', NULL);
INSERT INTO oe.suppliers (supplier_id, company_name, created_date, modified_date) VALUES (28, 'Gai pâturage', '2025-05-07 01:33:21.034363', NULL);
INSERT INTO oe.suppliers (supplier_id, company_name, created_date, modified_date) VALUES (29, 'Forêts d''érables', '2025-05-07 01:33:21.034363', NULL);


--
-- PostgreSQL database dump complete
--

create sequence oe.suppliers_supplier_id_seq
start with 30
increment by 1
cache 1;

alter table oe.suppliers
alter column supplier_id set default nextval('oe.suppliers_supplier_id_seq');

alter sequence oe.suppliers_supplier_id_seq
owned by oe.suppliers.supplier_id;
