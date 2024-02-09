\c postgres;
drop database vehicule;
create database vehicule2;
\c vehicule2;


create table token(
    id_token serial primary key,
    token text not null,
    cle text not null,
    date_creation date not null,
    date_expiration date not null
);

create table tokenmobile(
    id_tokenmobile serial primary key,
    tokenmobile text not null,
    utilisateur_id int references utilisateur(id_utilisateur)
);

create table comission(
    id_comission serial primary key,
    taux double precision not null,
    datecomission date default now()
);

create table utilisateur(
    id_utilisateur serial primary key,
    nom varchar(50) not null,
    prenom varchar(50) not null,
    genre int,
    date_naissance date,
    email varchar(150) not null,
    mdp varchar(80) not null,
    roles int not null
);

create table marque(
    id_marque serial primary key,
    marque varchar(70) not null
);


create table categorie(
    id_categorie serial primary key,
    categorie varchar(70) not null
);

create table carburant(
    id_carburant serial primary key,
    carburant varchar(70) not null
);

create table model(
    id_model serial primary key,
    marque_id int references marque(id_marque),
    categorie_id int references categorie(id_categorie),
    model varchar(70) not null
);



create table anneesortie(
    id_anneesortie serial primary key,
    model_id int references model(id_model),
    annee int not null
);

create table modelcarburant(
    id_modelcarburant serial primary key,
    model_id int references model(id_model),
    carburant_id int references carburant(id_carburant)
);


create table lieu(
    id_lieu serial primary key,
    lieu varchar(120) not null
);

create table voiture(
    id_voiture serial primary key,
    model_id int references model(id_model),
    matricule varchar(70) not null,
    kilometrage double precision not null,
    modelcarburant_id int references modelcarburant(id_modelcarburant),
    anneesortie_id int references anneesortie(id_anneesortie)
);

create table annonce(
    id_annonce serial primary key,
    voiture_id int references voiture(id_voiture),
    lieu_id int references lieu(id_lieu),
    vendeur_id int references utilisateur(id_utilisateur),
    prix_vente double precision,
    statut int,
    date_annonce Date not null,
    date_confirmation Date null
);

create table vente(
    id_vente serial primary key,
    annonce_id int references annonce(id_annonce),
    acheteur_id int references utilisateur(id_utilisateur),
    prix_achat double precision,
    taux_comission double precision,
    date_achat date default current_date
);


/***** Hasinjo **/
create table boitevitesse (
    id_boitevitesse serial primary key,
    boitevitesse varchar(70) not null
);