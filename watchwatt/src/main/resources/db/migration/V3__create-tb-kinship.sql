create table tb_kinship
(
    id             bigint not null,
    degree_kinship varchar(255) check (degree_kinship in
                                       ('FATHER', 'MOTHER', 'SON', 'DAUGHTER', 'SISTER', 'BROTHER', 'HUSBAND', 'WIFE',
                                        'OTHERS')),
    name           varchar(255),
    user_id        bigint,
    primary key (id)
);
