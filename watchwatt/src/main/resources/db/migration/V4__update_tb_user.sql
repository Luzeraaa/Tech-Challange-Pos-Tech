alter table tb_user
    add column update_date timestamp(6) after date_created;