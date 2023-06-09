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
INSERT INTO users (id, first_name, last_name, role_id, camunda_id)
VALUES (default, 'Александр', 'Македонский', 1, 'makedonsky');

INSERT INTO users (id, first_name, last_name, role_id, camunda_id)
VALUES (default, 'Кир', 'Булычев', 2, 'bulychev');

INSERT INTO users (id, first_name, last_name, role_id, camunda_id)
VALUES (default, 'Василий', 'Тапочкин', 3, 'tapochkin');

INSERT INTO users (id, first_name, last_name, role_id, camunda_id)
VALUES (default, 'Виола', 'Тараканова', 3, 'tarakanova');

INSERT INTO users (id, first_name, last_name, role_id, camunda_id)
VALUES (default, 'Василиса', 'Немудрая', 4, 'nemudraya');

INSERT INTO users (id, first_name, last_name, role_id, camunda_id)
VALUES (default, 'Карен', 'Всеорущая', 4, 'vseorushchaya');

INSERT INTO users (id, first_name, last_name, role_id, camunda_id)
VALUES (default, 'Фанис', 'Фархутдинов', 4, 'farhutdinov');

INSERT INTO users (id, first_name, last_name, role_id, camunda_id)
VALUES (default, 'Алексей', 'Ефимов', 4, 'efimov');