create table admin(
    id serial primary key,
    login varchar,
    password varchar
);


create table type (id serial primary key,type varchar);

insert into type(type)values('IA S');

create table ContenueIA(
    id serial primary key,
    titre varchar,
    introduction text,
    idtype int references type(id),
    motcle varchar,
    conclure varchar,
    image varchar,
    article text
);

insert into admin (login,password)values ('admin','admin');

alter table ContenueIA add column date_creation date default current_date;


create or replace view V_Contenue as (
select cont.*,tp.type from contenueia cont join 
type tp on cont.idtype = tp.id);