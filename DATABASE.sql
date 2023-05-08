create database portefeuille;

create role portefeuille login password portefeuille;
alter database portefeuille owner to portefeuille;

create table categorie(
    id serial primary key,
    categorie varchar,
    type_categorie int
);
tempsdepense ou tempsrecette=====> 1 == jour /20== semaine / 30 == mois / 40 === ans

create table Recette (
    id serial primary key ,
    type int,
    Daty date default current_date,
    description varchar,
    priorite int,
    etat int,
    tempsrecette int,
    idcategorie int references categorie(id)
);


create table depense (
    id serial primary key ,
    type int,
    Daty date default current_date,
    description varchar,
    priorite int,
    etat int,
    tempsdepense int,
    idcategorie int references categorie(id)
);


create table economie(
    id serial primary key,
    montant double precision,
    datyecconomie date
);

create table personne(
    id serial primary key,
    nom varchar,
    prenom varchar,
    contact varchar,
    adresse varchar
);


insert into personne(nom,prenom,contact,adresse)values('Rakoto','Jean','0385905751','ILP');

create table emprunt (
    id serial primary key ,
    dateemprunt date default current_date,
    montant double precision,
    idpersonne int references personne(id),
    dateremboursement date,
    nbrpayement int
);

create table payementemprunt(
    id serial primary key ,
    idemprunt int references emprunt(id),
    montant double precision,
    datepayement date default current_date
);

create table reportcaisse(
    id serial primary key,
    montant double precision,
    datereport timestamp 
);


alter table emprunt add column motif varchar;

CREATE  TABLE "public".tauxemprunt ( 
	id                   serial  NOT NULL ,
	datetaux             date   ,
	pourcentage          double precision   ,
	CONSTRAINT pk_tauxemprunt_id PRIMARY KEY ( id )
 );


insert into tauxemprunt (datetaux,pourcentage)values('2023-02-02',0.4);

ALTER TABLE EMPRUNT ADD COLUMN ETATPAYEMENT INTEGER DEFAULT 1;


create or replace view V_EmpruntPers as (
select 
id,idpersonne,dateremboursement,sum(montant) montant 
from emprunt group by idpersonne,dateremboursement,id);

create or replace view V_EmpruntDetails as (

select v_emprt.*,pers.nom,pers.prenom,pers.adresse,pers.contact from V_EmpruntPers v_emprt 
join personne pers 
on v_emprt.idpersonne=pers.id);

select * from emprunt emprt 
join personne pers 
on emprt.idpersonne=pers.id;

CREATE OR REPLACE VIEW V_MONTANTDETAILS AS (
    select idemprunt,sum(montant) as montant from payementemprunt group by idemprunt 
);


Create or replace view v_detailsMontantEmprunt as (
select v_d.*, (v_d.montant-V_M.montant)   as montant_a_Payer,V_M.montant as montantPayer
 from V_EmpruntDetails v_d LEFT join  V_MONTANTDETAILS V_M
on v_d.id= v_m.idemprunt);
 


