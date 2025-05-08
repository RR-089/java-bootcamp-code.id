--1.insert all data fintech
insert into fintech.fintechs (fint_code,fint_name,fint_short_name,fint_type)
values
('002',	'Bank Rakyat Indonesia', 'BRI', 'BANK'),
('008',	'Bank Mandiri',	'MANDIRI', 'BANK'),
('009',	'Bank Negara Indonesia', 'BNI'	, 'BANK'),
('014',	'Bank Central Asia', 'BCA', 'BANK'),
('022',	'Bank CIMB Niaga',	'CIMB', 'BANK'),
('028',	'Bank OCBC NISP',	'OCBC', 'BANK'),
('426',	'Bank Mega',	'MEGA', 'BANK'),
('013',	'Bank Permata',	'PERMATA', 'BANK'),
('011',	'Bank Danamon',	'DANAMON', 'BANK'),
('016',	'Bank Maybank Indonesia',	'MAYBANK', 'BANK'),
('019',	'Bank Panin',	'PANIN', 'BANK'),
('023',	'Bank UOB Indonesia',	'UOB', 'BANK'),
('200',	'Bank Tabungan Negara', 'BTN', 'BANK'),
('045',	'Bank BTPN/Jago',	'BTPN/JAGO', 'BANK'),
('213',	'Bank BJB',	'BJB', 'BANK'),
('490',	'Bank Neo Commerce','NEO', 'BANK'),
('501',	'Bank Digital BCA (blu)','BCA DIGITAL', 'BANK'),
('503',	'Bank Jago',	'JAGO', 'BANK'),
('506',	'Bank Seabank',	'BANK','BANK'),
('890',	'Dana',	'DANA','E-Wallet'),
('991',	'OVO',	'OVO','E-Wallet'),
('992',	'LinkAja','LINKAJA',	'E-Wallet'),
('993',	'GoPay','GOPAY',	'E-Wallet'),
('994',	'ShopeePay','SHOPEEPAY',	'E-Wallet'),
('995',	'Flip',	'FLIP','P-GateAway'),
('998',	'Jenius (BTPN)	Digital Banking','JENIUS','E-Wallet');

--2. set for bank & fintech sequence range 100
alter sequence fintech.accounts_account_id_seq
restart with 100 increment by 1;

--3. bank account
insert into fintech.accounts (account_no,balance,currency,user_id,fin_code)
values
('77712345678',20000000,'IDR',147,'014'),
('88898765432',10000000,'IDR',148,'008'),
('54298765432 ',5000000,'IDR',149,'993'),
('88912345678 ',3000000,'IDR',150,'994');

--4. set for bank & fintech sequence range 300
alter sequence fintech.accounts_account_id_seq
restart with 300 increment by 1;

--5. user account
insert into fintech.accounts (account_no,balance,currency,user_id,fin_code)
values
('008-133465789',1000000,'IDR',66,'008'),
('014-567898765',2000000,'IDR',66,'014'),
('993-456789876',0,'IDR',66,'993'),
('008-321123456',1000000,'IDR',68,'008'),
('014-876567890',2000000,'IDR',68,'008'),
('994-456789876',20000,'IDR',68,'994');


