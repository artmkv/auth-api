CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

SELECT favorites.uuid_generate_v1();

create table auth_service.users
(
    id       uuid default favorites.uuid_generate_v1(),
    username varchar(255) not null,
    email    varchar(255) not null unique,
    password varchar(255) not null,
    birthday date,
    country  varchar(255),
    gender   varchar(255),
    primary key (id)
);

create table auth_service.roles
(
    name varchar(50) not null primary key
);

create table auth_service.users_roles
(
    user_id   uuid        not null references auth_service.users,
    role_name varchar(50) not null references auth_service.roles,
    primary key (user_id, role_name)
);