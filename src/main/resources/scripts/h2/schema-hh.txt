create table requests
(
    id           number,
    request_text nvarchar2(255)                 not null,
    executor_id  number,
    status       nvarchar2(10) default 'NEW'    not null,
    priority     nvarchar2(7)  default 'NORMAL' not null,
    constraint REQUESTS_PK
        primary key (id)
);

comment on table requests is 'Таблица принимаемых и обрабатываемых запросов';

create unique index REQUESTS_ID_UINDEX
    on requests (id);

--
create table users
(
    id         number,
    first_name nvarchar2(64) not null,
    last_name  nvarchar2(64) not null,
    role_id    number        not null,
    constraint USERS_PK
        primary key (id)
);

comment on table users is 'Таблица пользователей';

create unique index USERS_ID_UINDEX
    on users (id);

--
create table roles
(
    id               number auto_increment,
    code        nvarchar2(32) not null,
    name        nvarchar2(64) not null,
    description nvarchar2(128),
    constraint ROLES_PK
        primary key (id)
);

comment on table roles is 'Таблица ролей';

create unique index ROLES_ID_UINDEX
    on roles (id);

create unique index ROLES_ROLE_CODE_UINDEX
    on roles (code);

create unique index ROLES_ROLE_NAME_UINDEX
    on roles (name);

--Наполнение таблицы пользователей
INSERT INTO users (id, first_name, last_name, role_id)
VALUES (1, 'Александр', 'Македонский', 1);

INSERT INTO users (id, first_name, last_name, role_id)
VALUES (2, 'Кир', 'Булычев', 2);

INSERT INTO users (id, first_name, last_name, role_id)
VALUES (3, 'Василий', 'Тапочкин', 3);

INSERT INTO users (id, first_name, last_name, role_id)
VALUES (4, 'Виола', 'Тараканова', 3);

INSERT INTO users (id, first_name, last_name, role_id)
VALUES (5, 'Василиса', 'Немудрая', 4);

INSERT INTO users (id, first_name, last_name, role_id)
VALUES (6, 'Карен', 'Всеорущая', 4);

INSERT INTO users (id, first_name, last_name, role_id)
VALUES (7, 'Фанис', 'Фархутдинов', 4);

--Наполнение таблицы ролей
INSERT INTO roles(id, code, name, description)
VALUES (1, 'DEPARTMENT_HEAD', 'Руководитель отдела сопровождения', null);

INSERT INTO roles(id, code, name, description)
VALUES (2, 'TEAM_LEADER', 'Руководитель группы сопровождения', null);

INSERT INTO roles(id, code, name, description)
VALUES (3, 'EXPERT', 'Специалист сопровождения', null);

INSERT INTO roles(id, code, name, description)
VALUES (4, 'COMMON_USER', 'Пользователь системы', null);

/*INSERT INTO requests(id, request_text, executor_id, status, priority)
VALUES (1, 'Request #0', 3, 'OPEN', 'NORMAL');

INSERT INTO requests(id, request_text, executor_id, status, priority)
VALUES (2, 'Request #1', 1, 'OPEN', 'NORMAL');

INSERT INTO requests(id, request_text, executor_id, status, priority)
VALUES (3, 'Request #2', 3, 'OPEN', 'HIGH');

INSERT INTO requests(id, request_text, executor_id, status, priority)
VALUES (4, 'Request #3', 4, 'OPEN', 'NORMAL');*/
