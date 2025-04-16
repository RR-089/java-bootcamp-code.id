-- no 0
set search_path to hr, oe;

-- no 1.1
-- Buat table baru
create table oe.bank (
    bank_code varchar(4) primary key,
    bank_name varchar(55)
);

create table oe.carts (
    cart_id smallint primary key,
    created_on timestamp default current_timestamp,
    user_id int unique -- TODO: fk add later
);

create table oe.cart_items (
    cart_item_id smallint primary key,
    product_id int unique,
    quantity smallint,
    created_on timestamp default current_timestamp,
    cart_id int -- TODO: fk add later
);

-- Penambahan field baru pada tabel yang sudah ada
create type payment_type_enum as enum ('DEBIT', 'CREDIT', 'QRIS', 'TRANSFER');

alter table oe.orders
add column order_no varchar(15),
add column total_discount decimal(5,2),
add column total_amount decimal(8,2),
add column payment_type payment_type_enum, 
add column card_no varchar(25),
add column transac_no varchar(25),
add column transac_date timestamp,
add column ref_no varchar(25),
add column location_id int,
add column user_id int,
add column bank_code varchar(4);

-- no 2.1
alter table oe.orders
add constraint fk_location_id foreign key (location_id) references hr.locations(location_id);

update oe.orders o
set location_id = l.location_id
from hr.locations l
where o.location_id is null
  and (
      (o.ship_postal_code is not null and o.ship_postal_code = l.postal_code)
      or (o.ship_postal_code is null) -- karena ada beberapa postal code yang null
  )
  and (
      (o.ship_city is not null and o.ship_city = l.city)
      or (o.ship_city is null) -- antisipasi apabila field lain juga null seperti postal code
  )
  and (
      (o.ship_address is not null and o.ship_address = l.street_address)
      or (o.ship_address is null)
  )
  and (
      (o.ship_region is not null and o.ship_region = l.state_province)
      or (o.ship_region is null)
  )
  or (
      (o.ship_country is not null and o.ship_country = l.country_id)
      or (o.ship_country is null)
  );


-- hapus unused fields
alter table oe.orders
drop column ship_address,
drop column ship_postal_code,
drop column ship_city,
drop column ship_region,
drop column ship_country;

-- no 3
-- no 3.1
insert into hr.employees (employee_id, first_name, last_name, hire_date, job_id, salary, email)
select employee_id, first_name, last_name, hire_date,
	case 
        when title = 'oe Representative' then 16
        when title = 'Vice President, oe' then 5
        when title = 'oe Manager' then 15
        when title = 'Inside oe Coordinator' then 3
        else null
    end as job_id,
	case 
        when title = 'oe Representative' then 6000
        when title = 'Vice President, oe' then 15000
        when title = 'oe Manager' then 10000
        when title = 'Inside oe Coordinator' then 3000
        else null -- for titles that don't match
    end as salary,
	lower(first_name)||'.'||lower(last_name)||'@sqltutorial.org'
from oe.employees oee
where employee_id not in (select employee_id from hr.employees)
	and not exists (
		select 1
		from hr.employees e
		where e.first_name = oee.first_name
			and e.last_name = oee.last_name
			and e.hire_date = oee.hire_date
	);
-- delete from hr.employees
-- where employee_id < 20;


-- no 3.2
-- drop table oe.employees;

alter table employee_territories
drop constraint fk_employee_territories_employees;

alter table orders
drop constraint fk_orders_employees;

alter table oe.employee_territories
add constraint fk_employee_territories_employees foreign key (employee_id) references hr.employees(employee_id);

alter table oe.orders
add constraint fk_orders_employees foreign key (employee_id) references hr.employees(employee_id);

-- drop table oe.employees;

-- select order_id, o.employee_id, e.first_name from oe.orders o
-- join hr.employees e on o.employee_id= e.employee_id


-- no 4
-- no 4.1, 4.2 & 4.4
CREATE EXTENSION IF NOT EXISTS pgcrypto;

create table oe.users as (
    select 
		row_number() over (order by customer_id) as user_id,
        customer_id, 
        contact_name as user_name,
		crypt(customer_id::text, gen_salt('bf', 12)) as user_password,
        phone as user_handphone,
        lower(replace(contact_name, ' ', '.')) || '@sqltutorial.org' as user_email, -- place holder karena tidak ada field email
        now() as created_on 
    from oe.customers
);
-- select * from oe.users;

update oe.orders o
set user_id = u.user_id
from oe.users u
where o.customer_id = u.customer_id;

-- select * from oe.orders order by user_id;

alter table oe.users add constraint pk_users_user_id primary key (user_id);

alter table oe.users add constraint unique_users_user_id unique (user_id);

alter table oe.users add constraint unique_users_email unique (user_email);

-- drop table users;

-- no 4.3
-- fk_orders_customers (customer_id) -> (customer_id)
alter table oe.orders
drop constraint fk_orders_customers;

alter table oe.orders
drop column customer_id;

alter table oe.users
drop column customer_id

alter table oe.orders
add constraint fk_orders_users foreign key (user_id) references oe.users(user_id);

-- select user_name from orders o
-- join users u on o.customer_id = u.user_id

-- sebagai tambahan lanjutan penambahan constraints
alter table oe.carts
add constraint fk_carts_users foreign key (user_id) references oe.users(user_id) 

alter table oe.cart_items
add constraint fk_cart_items_carts foreign key (cart_id) references oe.carts(cart_id)

alter table oe.orders
add constraint fk_orders_bank_code foreign key (bank_code) references oe.bank(bank_code)

-- select * from oe.orders