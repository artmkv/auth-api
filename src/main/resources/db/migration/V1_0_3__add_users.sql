INSERT INTO auth_service.roles (name)
VALUES ('ROLE_READER'),
       ('ROLE_WRITER');

INSERT INTO auth_service.users(username, email, password, country, gender, birthday)
VALUES ('user1', 'user1@mail.com', '$2a$10$pn6rYWViz2K.T5C59LDePuS6aB9dTp/22KFN5kg5Z0XFykL2BuFZu', 'Italy', 'male', '1995-10-01'),
       ('userR', 'userR@mail.com', '$2a$10$Rzv7jRPbNSLYh07k2o2M5.QJxWgD7RUlOG2AlCE.g93qepzcDiupi', 'Poland', 'male', '1999-05-14'),
       ('userWR', 'userWR@mail.com', '$2a$10$JB.I.29Pc3eN/2muCG6qVuFfDm7k9WYzbvATzKtt56cTDfLkK2qBu', 'Germany', 'female', '1988-11-21');

INSERT INTO auth_service.users_roles(user_id, role_name)
VALUES ((SELECT id FROM auth_service.users u WHERE u.username = 'user1'), (SELECT name FROM auth_service.roles r WHERE r.name = 'ROLE_READER')),
       ((SELECT id FROM auth_service.users u WHERE u.username = 'userR'), (SELECT name FROM auth_service.roles r WHERE r.name = 'ROLE_READER')),
       ((SELECT id FROM auth_service.users u WHERE u.username = 'userWR'), (SELECT name FROM auth_service.roles r WHERE r.name = 'ROLE_READER')),
       ((SELECT id FROM auth_service.users u WHERE u.username = 'userWR'), (SELECT name FROM auth_service.roles r WHERE r.name = 'ROLE_WRITER'));
