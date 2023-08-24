alter table if exists tb_appliance add column user_id bigint;
alter table if exists tb_user alter column date_created set data type timestamp(6) with time zone;
alter table if exists tb_user alter column update_date set data type timestamp(6) with time zone;
alter table if exists tb_appliance add constraint FKjrksrqxdhcc2jextgvhy8cg1p foreign key (user_id) references tb_user;