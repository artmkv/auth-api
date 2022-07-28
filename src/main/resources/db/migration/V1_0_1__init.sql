CREATE TABLE auth_service.users
(
    id       BIGSERIAL PRIMARY KEY,
    username CHARACTER VARYING(128) UNIQUE,
    email    CHARACTER VARYING(128) UNIQUE NOT NULL,
    password CHARACTER VARYING(128)        NOT NULL,
    country  CHARACTER VARYING(128),
    gender   CHARACTER VARYING(24),
    birthday DATE
);

CREATE TABLE roles
(
    id   BIGSERIAL PRIMARY KEY,
    name CHARACTER VARYING(64) NOT NULL
);

CREATE TABLE auth_service.users_roles
(
    user_id BIGINT REFERENCES auth_service.users (id),
    role_id BIGINT REFERENCES auth_service.roles (id),
    PRIMARY KEY (user_id, role_id)
);