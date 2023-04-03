create table requests
(
    id           number auto_increment,
    request_text nvarchar2(255)                 not null,
    executor     nvarchar2(127),
    status       nvarchar2(10) default 'NEW'    not null,
    priority     nvarchar2(7)  default 'NORMAL' not null,
    constraint REQUESTS_PK
        primary key (id)
);

comment on table requests is 'Таблица принимаемых и обрабатываемых запросов';

create unique index REQUESTS_ID_UINDEX
    on requests (id);