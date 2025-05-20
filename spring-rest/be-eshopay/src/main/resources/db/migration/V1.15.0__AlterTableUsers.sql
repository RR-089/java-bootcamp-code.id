alter table person.users
rename created_on to created_date;

alter table person.users
add column modified_date timestamp;