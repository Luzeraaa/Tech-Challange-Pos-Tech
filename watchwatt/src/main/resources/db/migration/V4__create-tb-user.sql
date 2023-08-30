create table tb_user
(
    id           bigint not null,
    birthday     date,
    cpf          varchar(255),
    date_created timestamp(6) with time zone,
    email        varchar(255),
    gender       varchar(255) check (gender in ('MALE', 'FEMALE', 'OTHERS')),
--     role         varchar(255) check (gender in ('ADMIN', 'USER')),
    name         varchar(255),
    password     varchar(255),
    update_date  timestamp(6) with time zone,
    primary key (id)
);
