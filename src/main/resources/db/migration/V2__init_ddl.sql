create table users
(
    id                             bigserial    not null primary key,
    email                          varchar(128) not null,
    role                           varchar(255) not null,
    language_code                  varchar(5)   not null
);