ALTER TABLE tb_user
    ADD role varchar(255) check (role in ('ADMIN', 'USER'));
