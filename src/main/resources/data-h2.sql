--Наполнение таблицы ролей
INSERT INTO roles(id, code, name, description)
VALUES (default, 'DEPARTMENT_HEAD', 'Руководитель отдела сопровождения', null);

INSERT INTO roles(id, code, name, description)
VALUES (default, 'TEAM_LEADER', 'Руководитель группы сопровождения', null);

INSERT INTO roles(id, code, name, description)
VALUES (default, 'EXPERT', 'Специалист сопровождения', null);

INSERT INTO roles(id, code, name, description)
VALUES (default, 'COMMON_USER', 'Пользователь системы', null);

--Наполнение таблицы пользователей
INSERT INTO users (id, first_name, last_name, role_id)
VALUES (default, 'Александр', 'Македонский', 1);

INSERT INTO users (id, first_name, last_name, role_id)
VALUES (default, 'Кир', 'Булычев', 2);

INSERT INTO users (id, first_name, last_name, role_id)
VALUES (default, 'Василий', 'Тапочкин', 3);

INSERT INTO users (id, first_name, last_name, role_id)
VALUES (default, 'Виола', 'Тараканова', 3);

INSERT INTO users (id, first_name, last_name, role_id)
VALUES (default, 'Василиса', 'Немудрая', 4);

INSERT INTO users (id, first_name, last_name, role_id)
VALUES (default, 'Карен', 'Всеорущая', 4);

INSERT INTO users (id, first_name, last_name, role_id)
VALUES (default, 'Фанис', 'Фархутдинов', 4);

INSERT INTO users (id, first_name, last_name, role_id)
VALUES (default, 'Алексей', 'Ефимов', 4);