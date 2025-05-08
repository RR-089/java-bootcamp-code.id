create schema if not exists fintech;

drop table if exists fintech.transactions;
drop table if exists fintech.accounts;
drop table if exists fintech.fintechs;

create table fintech.fintechs(
	fint_code varchar(3) constraint pk_fint_code primary key,
	fint_name varchar(125),
	fint_short_name varchar(15),
	fint_type varchar(10) check (fint_type in ('BANK','FINTECH','E-Wallet','P-GateAway')),
	created_date  timestamp default current_timestamp,
    modified_date timestamp
);

create table fintech.accounts(
	account_id bigserial constraint pk_account_code primary key,
	account_no varchar(30) constraint uq_account_no unique,
	balance decimal(15,2),
	currency varchar(5),
	user_id integer constraint fk_accounts_users  references person.users(user_id),
	fin_code varchar(4) constraint fk_accounts_fintech  references fintech.fintechs(fint_code),
	created_date  timestamp default current_timestamp,
    modified_date timestamp
);


create table fintech.transactions(
	trac_id bigserial constraint pk_transactions primary key,
	trac_no varchar(25) constraint uq_transactions unique,
	from_account bigint 
		constraint fk_trac_from_account references fintech.accounts(account_id),
	to_account bigint 
		constraint fk_trac_to_account references fintech.accounts(account_id),
	trac_noref varchar(25) null
		constraint fk_trac_ref references fintech.transactions(trac_no),
	debet decimal(15,2),
	credit decimal(15,2),
	trac_type varchar(15) check (trac_type in ('TRANSFER','DEPOSIT','QRIS')),
	description varchar(55),
	created_date  timestamp default current_timestamp,
    modified_date timestamp,
    status varchar(20) check (status in ('PENDING','SUCCEES','FAILED','CANCELLED'))
);	


