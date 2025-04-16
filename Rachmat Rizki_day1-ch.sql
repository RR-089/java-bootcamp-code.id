-- 1.1
CREATE SCHEMA oe;

-- 1.2
-- execute 03-nw_db.sql

-- 1.3
ALTER ROLE postgres SET search_path TO oe;
SET search_path TO oe;

-- 2.1
-- INSERT INTO oe.categories VALUES (9, 'Electronics', 'Gadgets', '\x');
-- DELETE FROM categories WHERE category_id = 9;

-- Jawaban
SELECT c.category_id, category_name, COUNT(1) AS total_product
FROM categories c JOIN products p
ON c.category_id = p.category_id
GROUP BY c.category_id, category_name
ORDER BY c.category_id;

-- Jawaban alternatif 1
SELECT c.category_id, category_name, COUNT(p.category_id) AS total_product
FROM categories c JOIN products p
ON c.category_id = p.category_id
GROUP BY c.category_id, category_name
ORDER BY c.category_id;

-- Jawaban alternatif 2 (menampilkan semua category)
SELECT c.category_id, category_name, COUNT(p.product_id) AS total_product
FROM categories c LEFT JOIN products p 
ON c.category_id = p.category_id
GROUP BY c.category_id, category_name
ORDER BY c.category_id;

-- 2.2
-- INSERT INTO suppliers VALUES (30, 'Sea d''zoyaérables', 'John Doe', 'Accounting Manager', '148 rue Chasseur', 'Ste-Hyacinthe', 'Québec', 'J2S 7S8', 'Canada', '(514) 555-2952', '(514) 555-2922', NULL);
-- DELETE FROM suppliers WHERE supplier_id = 30;


-- Jawaban
SELECT s.supplier_id, company_name, COUNT (1) AS total_product
FROM suppliers s JOIN products p 
ON s.supplier_id = p.supplier_id
GROUP BY s.supplier_id, company_name
ORDER BY total_product DESC


-- Jawaban alternatif 1
SELECT s.supplier_id, company_name, COUNT (p.product_id) AS total_product
FROM suppliers s JOIN products p 
ON s.supplier_id = p.supplier_id
GROUP BY s.supplier_id, company_name
ORDER BY total_product DESC

-- Jawaban alternatif 2
SELECT s.supplier_id, company_name, COUNT (p.product_id) AS total_product
FROM suppliers s LEFT JOIN products p
ON s.supplier_id = p.supplier_id
GROUP BY s.supplier_id, company_name
ORDER BY total_product DESC;

-- 2.3
-- Jawaban
SELECT s.supplier_id, company_name, COUNT (1) AS total_product, 
	TO_CHAR(AVG(unit_price), 'FM999999990.00') AS avg_unit_price
FROM suppliers s JOIN products p
ON s.supplier_id = p.supplier_id
GROUP BY s.supplier_id, company_name
ORDER BY total_product DESC;



-- Jawaban alternatif 1
SELECT s.supplier_id, company_name, COUNT (p.product_id) AS total_product, 
	TO_CHAR(AVG(unit_price), 'FM999999990.00') AS avg_unit_price
FROM suppliers s JOIN products p
ON s.supplier_id = p.supplier_id
GROUP BY s.supplier_id, company_name
ORDER BY total_product DESC;


-- Jawaban alternatif 2
SELECT s.supplier_id, company_name, COUNT (p.product_id) AS total_product, 
	TO_CHAR(AVG(unit_price), 'FM999999990.00') AS avg_unit_price
FROM suppliers s LEFT JOIN products p
ON s.supplier_id = p.supplier_id
GROUP BY s.supplier_id, company_name
ORDER BY total_product DESC;


-- 2.4
-- SELECT product_id, product_name, supplier_id, unit_price, units_in_stock, reorder_level
-- FROM products WHERE units_in_stock <= reorder_level ORDER BY product_name;
-- Jawaban:
SELECT product_id, product_name, s.supplier_id, s.company_name,
	unit_price, units_in_stock, units_on_order, reorder_level
FROM products p JOIN suppliers s 
ON p.supplier_id = s.supplier_id
WHERE p.units_in_stock <= p.reorder_level
ORDER BY p.product_name;

-- 2.5
-- Jawaban
SELECT c.customer_id, company_name, COUNT(o.order_id)
FROM customers c JOIN orders o
ON c.customer_id = o.customer_id
GROUP BY c.customer_id
ORDER BY c.customer_id;

-- 2.6
-- Jawaban
SELECT order_id, customer_id, order_date, required_date, shipped_date,
	shipped_date - order_date AS delivery_time
FROM orders 
WHERE shipped_date > order_date + INTERVAL '7 days'
-- WHERE shipped_date > order_date + INTERVAL '10 days'
-- WHERE shipped_date - order_date > 7
ORDER BY order_id;

-- 2.7
-- Jawaban
SELECT p.product_id, product_name, SUM(od.quantity) AS total_qty 
FROM products p JOIN order_details od
ON p.product_id = od.product_id
JOIN orders o ON od.order_id = o.order_id
WHERE o.shipped_date IS NOT NULL
GROUP BY p.product_id
ORDER BY total_qty DESC;

-- 2.8
-- Jawaban
SELECT c.category_id, category_name, SUM(od.quantity) AS total_qty_ordered
FROM categories c 
JOIN products p ON c.category_id = p.category_id
JOIN order_details od ON p.product_id = od.product_id
JOIN orders o ON od.order_id = o.order_id
WHERE o.shipped_date IS NOT NULL
GROUP BY c.category_id, category_name
ORDER BY total_qty_ordered DESC;



-- 2.9 
WITH category_totals AS (
	SELECT c.category_id, category_name, SUM(od.quantity) AS total_qty_ordered
	FROM categories c 
	JOIN products p ON c.category_id = p.category_id
	JOIN order_details od ON p.product_id = od.product_id
	JOIN orders o ON od.order_id = o.order_id
	WHERE o.shipped_date IS NOT NULL
	GROUP BY c.category_id, category_name
	ORDER BY total_qty_ordered DESC
)

SELECT * FROM category_totals
WHERE total_qty_ordered IN (
	(SELECT MAX(total_qty_ordered) FROM category_totals),
	(SELECT MIN(total_qty_ordered) FROM category_totals)
)

-- 2.10
SELECT shipper_id, company_name, 
	p.product_id, p.product_name, SUM(od.quantity) AS total_qty_ordered
FROM shippers s 
JOIN orders o ON s.shipper_id = o.ship_via
JOIN order_details od ON o.order_id = od.order_id
JOIN products p ON od.product_id = p.product_id
GROUP BY shipper_id, p.product_id, p.product_name
ORDER BY company_name, p.product_name;

-- 2.11
WITH shipper_total_order AS (
	SELECT shipper_id, company_name, 
		p.product_id, p.product_name, SUM(od.quantity) AS total_qty_ordered
	FROM shippers s 
	LEFT JOIN orders o ON s.shipper_id = o.ship_via
	JOIN order_details od ON o.order_id = od.order_id
	JOIN products p ON od.product_id = p.product_id
	GROUP BY shipper_id, p.product_id, p.product_name
	ORDER BY company_name, p.product_name
)								  

SELECT * FROM shipper_total_order 
WHERE total_qty_ordered IN (
	(SELECT MIN (total_qty_ordered)
	FROM shipper_total_order sto
	WHERE sto.company_name = shipper_total_order.company_name),
	(SELECT MAX (total_qty_ordered)
	FROM shipper_total_order sto
	WHERE sto.company_name = shipper_total_order.company_name)
)
ORDER BY shipper_id, total_qty_ordered;

-- 2.12
SET search_path TO hr;

WITH RECURSIVE hierarchy_level AS (
	SELECT employee_id, first_name||' '||last_name AS full_name,
		manager_id, d.department_name, 1 AS level
	FROM employees e
	JOIN departments d ON e.department_id = d.department_id 
	WHERE manager_id IS NULL
	
	UNION ALL
	
	SELECT k.employee_id, first_name||' '||last_name AS full_name,
		k.manager_id, h.department_name, h.level + 1
	FROM employees k
	JOIN hierarchy_level h ON k.manager_id=h.employee_id
)

SELECT * FROM hierarchy_level ORDER BY employee_id;






