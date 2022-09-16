drop schema if exists contactsdb;

create schema contactsdb;de

use contactsdb;

create table contact (
    name varchar(32) not null,
    mobile varchar(16) not null,
    email varchar(32) not null,

    primary key (email)

);



















