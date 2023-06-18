alter table tb_user
    add column update_date timestamp(6) after date_created;
    
alter table tb_user
    add column parentesco varchar;