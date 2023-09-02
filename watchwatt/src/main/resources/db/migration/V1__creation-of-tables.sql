create table tb_address (id bigint not null, city varchar(255), neighborhood varchar(255), number integer, reference varchar(255), state varchar(255), street varchar(255), zip_code varchar(255), user_id bigint, primary key (id));

create table tb_appliance (id bigint not null, model varchar(255), name varchar(255), power integer, address_id bigint, primary key (id));

create table tb_kinship (id bigint not null, degree_kinship varchar(255) check (degree_kinship in ('FATHER','MOTHER','SON','DAUGHTER','SISTER','BROTHER','HUSBAND','WIFE','OTHERS')), name varchar(255), address_id bigint, primary key (id));

create table tb_user (id bigint not null, birthday date, cpf varchar(255), date_created timestamp(6), email varchar(255), gender varchar(255) check (gender in ('MALE','FEMALE','OTHERS')), name varchar(255), password varchar(255), role varchar(255) check (role in ('USER','ADMIN')), update_date timestamp(6), primary key (id));

alter table if exists tb_user drop constraint if exists UK_869sa3rebuf3nm0d4jwxdtouk;
alter table if exists tb_user add constraint UK_869sa3rebuf3nm0d4jwxdtouk unique (cpf);
alter table if exists tb_user drop constraint if exists UK_4vih17mube9j7cqyjlfbcrk4m;
alter table if exists tb_user add constraint UK_4vih17mube9j7cqyjlfbcrk4m unique (email);

create sequence address_sequence start with 11 increment by 50;
create sequence appliance_sequence start with 11 increment by 50;
create sequence kinship_sequence start with 11 increment by 50;
create sequence user_sequence start with 11 increment by 50;

alter table if exists tb_address add constraint FKhx6ab79y261wkrfa11h092ft foreign key (user_id) references tb_user;
alter table if exists tb_appliance add constraint FKralhaghsm6fyf7se1r5bbit2i foreign key (address_id) references tb_address;
alter table if exists tb_kinship add constraint FK38v1x0p7nr9m0yjtga93c2a1v foreign key (address_id) references tb_address;
