create table tb_address
(
    id           bigint not null,
    city         varchar(255),
    neighborhood varchar(255),
    number       integer,
    reference    varchar(255),
    state        varchar(255),
    street       varchar(255),
    zip_code     varchar(255),
    primary key (id)
);
