/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  User_HP
 * Created: 28 févr. 2023
 */

CREATE DATABASE film;
CREATE ROLE film LOGIN PASSWORD 'film';
ALTER DATABASE film OWNER TO film;
\c film film;

CREATE TABLE plateau(
    id serial PRIMARY KEY,
    plateau VARCHAR(50)
);
insert into plateau (plateau) values ('maison'),('eglise'),('rue');
CREATE TABLE typeScene(
    id serial PRIMARY KEY,
    typeScene VARCHAR(50)
);

insert into typeScene (typeScene) values ('dialogue'),('voix off');

CREATE TABLE film(
    id serial PRIMARY KEY,
    visuelle   VARCHAR(50),
    titre VARCHAR(50),
    auteur VARCHAR(50)
);

insert into film (visuelle,titre,auteur)
values
('Malokila 15','Malokila 15','Rajao'),
('Baraingo','Baraingo','Pasitera');

CREATE TABLE scene(
    id serial PRIMARY KEY,
    idFilm int references film(id),
    idType int references typeScene(id),
    idPlateau int references plateau(id),
    duree TIME
);
insert into scene (idFilm,idType,idPlateau,duree) values(1,1,1,'1:30:30');
insert into scene (idFilm,idType,idPlateau,duree) values(1,2,1,'0:20:30');
insert into scene (idFilm,idType,idPlateau,duree) values(1,2,2,'0:15:30');
insert into scene (idFilm,idType,idPlateau,duree) values(2,1,2,'0:15:30');

insert into scene (idFilm,idType,idPlateau,duree) values(1,1,1,'0:05:30');
insert into scene (idFilm,idType,idPlateau,duree) values(1,2,1,'0:25:30');
insert into scene (idFilm,idType,idPlateau,duree) values(1,2,2,'0:15:30');
insert into scene (idFilm,idType,idPlateau,duree) values(1,1,1,'0:30:30');
insert into scene (idFilm,idType,idPlateau,duree) values(1,1,1,'0:20:30');
insert into scene (idFilm,idType,idPlateau,duree) values(1,2,2,'0:15:30');
CREATE TABLE acteur(
    id serial PRIMARY KEY,
    nom VARCHAR(50),
    role VARCHAR(50)
);

CREATE TABLE typeEmotion(
    id serial PRIMARY KEY,
    type VARCHAR(50),
    dureEstimer TIME
);

CREATE TABLE personnageAction(
    idScene int references scene(id),
    idActeur int references acteur(id),
    idEmotion int references typeEmotion(id),
    phrase TEXT,
    duree TIME
);

CREATE TABLE estimation(
    idScene int references scene(id),
    idPlateau int references plateau(id),
    idFilm int references film(id),
    duree double precision,
    etat int default 0
 );

alter table estimation add column idfilm int references film(id);

insert into estimation(idscene,idplateau,duree)
values

(1,1,'3:30:00'),
(2,1,'2:00:00'),
(3,2,'1:30:00'),
(4,1,'00:30:00'),
(5,1,'01:00:00');
(6,2,'00:30:00'),
(7,1,'01:30:00'),
(8,1,'02:00:00'),
(9,2,'03:20:00');



select sum(est.duree) as heure as duree from estimation 
est join scene sc on est.idscene=sc.id where idfilm=1;

create or replace view V_Estimation as (
 select film.*,pl.plateau,est.* from estimation est 
 join film on est.idfilm=film.id
 join plateau pl on est.idplateau=pl.id
);

alter table plateau add column x double precision;
alter table plateau add column y double precision;
alter table scene add column heureEfficase time;

create table indisponibiliterPlateau(
    id serial primary key,
    idPlateau int references plateau(id),
    date date,
    observation text
);

create table indisponibiliterActeur(
    id serial primary key,
    idScene int references scene(id),
    date date,
    observation text
);

create or replace view tempsEstimer as select sum((( (extract(hour from personnageaction.duree))  )+( extract(minute from personnageaction.duree) / 60)+( extract(second from personnageaction.duree) / 3600) ) * (typeemotion.dureestimer)) as duree , personnageaction.idscene from personnageaction join typeEmotion on personnageaction.idEmotion = typeEmotion.id group by personnageaction.idscene;


 id | idacteur | date | observation


insert into indisponibiliteracteur(idacteur,date,observation)
values
(1,'2023-03-24','En travail'),
(2,'2023-03-23','Fait un tournage'),
(2,'2023-03-22','Repos');
insert into indisponibiliterplateau(idplateau,date,observation)
values
(1,'2023-03-22','utiliser pour tournage'),
(2,'2023-03-23','fabrication'),
(3,'2023-03-24','reserver');
insert into  


update estimation set duree = 2 where idscene=32 and duree<10;
update estimation set duree =3.8 where idscene=32 and duree=10;


create or replace view V_PersonnageAction as (
select pers.*,em.type,em.dureestimer,act.nom,act.role from personnageaction pers join acteur act 
on pers.idacteur=act.id 
join typeemotion em
on pers.idemotion=em.id);

create table jourferier(
    id serial primary key,
    cause varchar,
    daty date
);

insert into 
jourferier(cause,daty)
values
('Nouvelle An','2023-01-01'),
('Tolona','2023-03-29'),
('Fete Travail ','2023-05-01'),
('Lundi de paque','2023-04-10'),
('lundi Pantequote','2023-05-29'),
('fete Indepandence','2023-06-25'),
('fete Indepandence','2023-06-26');