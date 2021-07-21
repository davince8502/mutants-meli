-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
--@uthor: davince0285@gmail.com - Miguel Leonardo Hernandez
--Date: 18-07-2021
--Description: Creación tabla mutants_analytics.dna_vericated
-------------------------------------------------------------------------------

SET search_path TO mutants_analytics;

DROP TABLE IF EXISTS mutants_analytics.dna_verified;


CREATE TABLE mutants_analytics.dna_verified
(
    id                               bigint not null,
    dna	                             text not null,
    is_mutant                        boolean null,
    date_create 			         timestamp without time zone DEFAULT now(),
    date_modification 		         timestamp null
);

ALTER TABLE mutants_analytics.dna_verified ADD CONSTRAINT pk_dna_verified PRIMARY KEY (id);

