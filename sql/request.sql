select * from v_vente where annee = 2024 and mois = 1 order by total_comission desc;

select acheteur_id, count(*) from vente group by acheteur_id;
select vendeur_id, count(*) from annonce group by vendeur_id;