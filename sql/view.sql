create or replace view v_marque_model as 
select 
    mo.id_model, mo.marque_id, m.marque, an.id_anneesortie, an.annee,
    ca.id_categorie, ca.categorie, mo.model
from marque m
join model mo on mo.marque_id = m.id_marque
join anneesortie an  on an.model_id = mo.id_model
join categorie ca on ca.id_categorie =  mo.categorie_id;

CREATE VIEW v_model_details AS
SELECT
    ma.id_marque,
    ma.marque,
    m.id_model,
    m.model,
    c.id_categorie,
    c.categorie
FROM
    model m
    JOIN marque ma ON m.marque_id = ma.id_marque
    JOIN categorie c ON m.categorie_id = c.id_categorie
    order by ma.id_marque, m.id_model, c.id_categorie;

create view v_model_annee as 
select 
    m.id_marque,
    m.marque,
    m.id_model,
    m.model,
    m.id_categorie,
    m.categorie,
    an.id_anneesortie,
    an.annee 
from 
    v_model_details m
    join anneesortie an on an.model_id = m.id_model 
    order by m.id_marque, m.id_model, m.id_categorie, an.id_anneesortie; 


create or replace view v_categorie_marque as
select 
    id_marque, marque, id_categorie, categorie 
from v_model_details 
    group by id_marque, marque, id_categorie, categorie 
    order by id_marque, id_categorie;

create view v_detail_annonce as 
select 
    annonce.id_annonce, 
    concat(utilisateur.nom, ' ', utilisateur.prenom) as auteur,
    lieu.lieu,
    annonce.prix_vente,
    annonce.date_annonce,
    model.model,
    voiture.matricule,
    voiture.kilometrage,
    marque.marque,
    categorie.categorie,
    annee,
    carburant
from 
    annonce
    join utilisateur on utilisateur.id_utilisateur = annonce.vendeur_id
    join lieu on lieu.id_lieu = annonce.lieu_id
    join voiture on annonce.voiture_id = voiture.id_voiture
    join model on voiture.model_id = model.id_model
    join marque on model.marque_id = marque.id_marque
    join categorie on model.categorie_id = categorie.id_categorie
    join anneesortie on voiture.anneesortie_id = anneesortie.id_anneesortie
    join modelcarburant on voiture.modelcarburant_id = modelcarburant.id_modelcarburant
    join carburant on carburant.id_carburant = modelcarburant.carburant_id;


create view v_effectifstat as
SELECT
    ROW_NUMBER() OVER () AS id,
    (SELECT COUNT(*) FROM utilisateur WHERE roles < 10) AS total_utilisateur,
    (SELECT SUM(prix_achat * (taux_comission/100)) FROM vente) AS total_commission,
    (SELECT COUNT(id_annonce) FROM annonce WHERE statut = 2) AS total_annonce,
    (SELECT COUNT(id_vente) FROM vente) AS total_vente;

/*create view v_statannoncemois as
SELECT
    ROW_NUMBER() OVER () AS id,
    EXTRACT(YEAR FROM date_annonce) AS annee,
    EXTRACT(MONTH FROM date_annonce) AS mois,
    statut,
    COUNT(id_annonce) AS nombre_annonces
FROM
    annonce
GROUP BY
    annee, mois, statut;
*/

--------------- modif 
create view v_statannoncemois as
SELECT
    ROW_NUMBER() OVER () AS id,
    COALESCE(EXTRACT(YEAR FROM date_confirmation), EXTRACT(YEAR FROM date_annonce), 0) AS annee,
    COALESCE(EXTRACT(MONTH FROM date_confirmation), EXTRACT(MONTH FROM date_annonce), 0) AS mois,
    COALESCE(statut, 1) AS statut,
    COUNT(id_annonce) AS nombre_annonces
FROM

    annonce
GROUP BY
    annee, mois, statut;





create view v_statventemois as
SELECT
    ROW_NUMBER() OVER () AS id,
    EXTRACT(YEAR FROM date_achat) AS annee,
    EXTRACT(MONTH FROM date_achat) AS mois,
    SUM(prix_achat * (taux_comission/100)) as total_comission
FROM 
    vente
GROUP BY
    annee, mois
ORDER BY
    annee, mois;


create view v_vente as
select
    vente.*,
    vendeur.prenom as vendeur,
    acheteur.prenom as acheteur,
    EXTRACT(YEAR FROM date_achat) AS annee,
    EXTRACT(MONTH FROM date_achat) AS mois,
    (prix_achat * (taux_comission/100)) as total_comission
from vente
    join annonce on annonce.id_annonce = vente.annonce_id
    join utilisateur vendeur on annonce.vendeur_id = vendeur.id_utilisateur
    join utilisateur acheteur on vente.acheteur_id = acheteur.id_utilisateur;
    

create view v_utilisateur as
select
    utilisateur.*,
    coalesce(vente.nb_achat, 0) as nb_achat,
    coalesce(annonce.nb_annonce, 0) as nb_annonce
from utilisateur
    join (
    select acheteur_id, count(*) as nb_achat from vente group by acheteur_id
    ) vente on vente.acheteur_id = utilisateur.id_utilisateur
    join ( 
    select vendeur_id, count(*) as nb_annonce from annonce group by vendeur_id
    ) annonce on annonce.vendeur_id = utilisateur.id_utilisateur;