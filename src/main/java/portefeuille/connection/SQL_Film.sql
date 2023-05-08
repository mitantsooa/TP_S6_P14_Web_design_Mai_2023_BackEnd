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

CREATE TABLE typeScene(
    id serial PRIMARY KEY,
    typeScene VARCHAR(50)
);

CREATE TABLE film(
    id serial PRIMARY KEY,
    film VARCHAR(50),
    titre VARCHAR(50),
    auteur VARCHAR(50)
);

CREATE TABLE scene(
    id serial PRIMARY KEY,
    idFilm int references film(id),
    idType int references typeScene(id),
    idPlateau int references plateau(id),
    duree TIME
);

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
    duree TIME
);

create table pagination(id serial primary key,pagination int);