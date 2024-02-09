-- Insertion dans la table "comission"
INSERT INTO comission (taux, datecomission) VALUES 
    (5, default); -- en %

-- Insertion dans la table "utilisateur"
INSERT INTO utilisateur (nom, prenom, genre, date_naissance, email, mdp, roles)
VALUES 
    ('Mr', 'Admin', 1, '1990-01-01', 'admin@gmail.com', 'root', 10),
    ('RANDRIA', 'Malala', 2, '1985-05-15', 'randria@gmail.com', '1234', 1),
    ('RAKOTO', 'Nirina', 1, '1982-09-20', 'rakoto@gmail.com', '1234', 1),
    ('RAHARY', 'Manitra', 2, '1995-03-10', 'rahary@gmail.com', '1234', 1),
    ('RAZAFY', 'Manantsoa', 1, '1988-07-25', 'razafy@gmail.com', '1234', 1);

-- Insertion dans la table "marque"
INSERT INTO marque (marque) VALUES
    ('Toyota'),
    ('Honda'),
    ('Ford'),
    ('Mazda'),
    ('Nissan'),
    ('Audi'),
    ('Porsche'),
    ('Range Rover'),
    ('BMW'),
    ('Subaru');

-- Insertion dans la table "categorie"
INSERT INTO categorie (categorie) VALUES 
    ('Berline'),
    ('SUV'),
    ('Hatchback'),
    ('Camionnette');

-- Insertion dans la table "carburant"
INSERT INTO carburant (carburant) VALUES 
    ('Essence'),
    ('Diesel'),
    ('Hybride'),
    ('Electrique');

-- Insertion dans la table "lieu"
INSERT INTO lieu (lieu) VALUES 
    ('Antananarivo'),
    ('Antsirabe'),
    ('Toamasina'),
    ('Fianarantsoa');

-- Insertion dans la table "model"
INSERT INTO model (marque_id, categorie_id, model) VALUES
    (4, 2, 'Mazda CX3'),
    (6, 2, 'Audi Q8'),
    (7, 2, 'Cayenne Turbo GT'),
    (3, 4, 'Ford Ranger'),
    (4, 4, 'Mazda BT-50'),
    (8, 2, 'Range Rover'),
    (1, 2, 'RAV-4'),
    (9, 1, 'BMW i5'),
    (10, 1, 'Subaru'),
    (1, 2, 'Toyota Fortuner');

-- Insertion dans la table "anneesortie"
INSERT INTO anneesortie (model_id, annee) VALUES 
    (1, 2019),
    (2, 2020),
    (3, 2021),
    (4, 2022),
    (5, 2023);

-- Insertion dans la table "modelcarburant"
INSERT INTO modelcarburant (model_id, carburant_id) VALUES 
    (1, 1),
    (2, 3),
    (3, 2),
    (4, 1),
    (5, 4);

-- Insertion dans la table "voiture"
INSERT INTO voiture (model_id, matricule, kilometrage, modelcarburant_id, anneesortie_id) VALUES 
    (1, 'ABC123', 5000, 1, 1),
    (2, 'DEF456', 7500, 2, 2),
    (3, 'GHI789', 3000, 3, 3),
    (4, 'JKL012', 9000, 4, 4),
    (5, 'MNO345', 6000, 1, 5),
    (1, 'PQR678', 12500, 1, 1),
    (2, 'MAD005', 7000, 2, 2),
    (3, 'VWX234', 1000, 3, 3),
    (4, 'YZA567', 3450, 4, 4),
    (5, 'BCD789', 525, 1, 5);


-- Insertion dans la table "annonce"
-- Donne annonce fictif pour presenter les ventes 2023
INSERT INTO annonce values(0, 1, 1, 2, 0, 3, '2023-01-01 12:00:00', '2023-01-01 12:30:00');

INSERT INTO annonce (voiture_id, lieu_id, vendeur_id, prix_vente, statut, date_annonce, date_confirmation) VALUES 
    (1, 1, 2, 75000000, 2, '2024-01-10 12:30:00', '2024-01-10 12:30:00'),
    (2, 3, 3, 120000000, 2, '2024-01-12 15:45:00', '2024-01-15 10:00:00'),
    (3, 4, 5, 90000000, 2, '2024-01-15 09:15:00', '2024-01-15 09:15:00'),
    (4, 2, 5, 100000000, 2, '2024-01-18 14:20:00', '2024-01-18 14:20:00'),
    (5, 4, 3, 45000000, 2, '2024-01-20 11:00:00', '2024-01-22 08:30:00'),
    (6, 1, 2, 110500000, 2, '2024-01-10 12:30:00', '2024-01-10 12:30:00'),
    (7, 3, 3, 60000000, 2, '2024-01-12 15:45:00', '2024-01-15 10:00:00'),
    (8, 4, 4, 65000000, 1, '2024-01-15 09:15:00', null),
    (9, 1, 4, 58000000, 1, '2024-01-18 14:20:00', null),
    (10, 1, 5, 85000000, 1, '2024-01-20 11:00:00', null);


/***** Hasinjo ***/
INSERT INTO boitevitesse (boitevitesse) VALUES
('Automatic'),
('Manual'),
('CVT'),
('Semi-Automatic');


-- Insertion dans la table "vente"
INSERT INTO vente (annonce_id, acheteur_id, prix_achat, taux_comission, date_achat) VALUES 
    (0, 1, 54000000, 5, '2023-01-01 14:45:00'),
    (0, 2, 139500000, 4, '2023-02-05 09:30:00'),
    (0, 3, 79000000, 3, '2023-03-10 11:15:00'),
    (0, 4, 97500000, 5, '2023-04-15 13:20:00'),
    (0, 5, 181000000, 4, '2023-05-20 16:00:00'),
    (0, 1, 294000000, 5, '2023-06-01 14:45:00'),
    (0, 2, 139500000, 4, '2023-07-05 09:30:00'),
    (0, 3, 359000000, 5, '2023-08-10 11:15:00'),
    (0, 4, 287500000, 2, '2023-09-15 13:20:00'),
    (0, 5, 141000000, 3, '2023-10-20 16:00:00'),
    (0, 5, 210000000, 5, '2023-11-20 16:00:00'),
    (0, 5, 271000000, 4, '2023-12-20 16:00:00');

INSERT INTO vente (annonce_id, acheteur_id, prix_achat, taux_comission, date_achat) VALUES 
    (0, 1, 45000000, 5, '2024-01-01 14:45:00'),
    (0, 2, 67500000, 5, '2024-01-05 09:30:00'),
    (0, 3, 79000000, 5, '2024-01-10 11:15:00');
