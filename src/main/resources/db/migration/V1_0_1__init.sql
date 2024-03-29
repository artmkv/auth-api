CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

SELECT uuid_generate_v1();

create table users
(
    id       uuid default uuid_generate_v1(),
    username varchar(255) not null,
    email    varchar(255) not null unique,
    password varchar(255) not null,
    birthday date,
    country  varchar(255),
    gender   varchar(255),
    primary key (id)
);

create table roles
(
    name varchar(50) not null primary key
);

create table users_roles
(
    user_id   uuid        not null references users,
    role_name varchar(50) not null references roles,
    primary key (user_id, role_name)
);