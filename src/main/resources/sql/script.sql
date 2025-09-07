-- Utils
SELECT * FROM ARTISTA;
SELECT * FROM ALBUM;
SELECT * from SINGLE;
SELECT * from MUSICA;

DELETE from ARTISTA where id = 3;

-- Data Load Inicial
-- (INSERT DE CADA DAO)
INSERT INTO artista (ID, NOME, ARQ_BANNER) VALUES (1, 'The Beatles', 'thebeatles.jpg');
INSERT INTO ALBUM (ID, NOME, DT_LANCAMENTO, ARQ_CAPA, COD_ARTISTA) VALUES (1,'Revolver', '05-08-1966', 'thebeatles_revolver.jpg', 1);
INSERT INTO single (ID, NOME, DURACAO, DT_LANCAMENTO, ARQ_AUDIO, ARQ_CAPA, COD_ARTISTA) VALUES (1,'Now And Then', '0:4:08', '02-11-2023', 'thebeatles_nowandthen.mp3', 'thebeatles_nowandthen.jpg', 1);
INSERT INTO MUSICA (ID, NOME, DURACAO, ARQ_AUDIO, COD_ALBUM) VALUES (1,'Here There and Everywhere', '0:2:24', 'revolver_herethereandeverywhere.mp3', 1);

-- sql do DAO (valores já pré-setados)
-- select
SELECT al.id, al.nome as "album", al.dt_lancamento, al.arq_capa, ar.nome as "artista"
FROM ALBUM al
JOIN ARTISTA ar ON al.COD_ARTISTA = ar.ID;

SELECT id, nome, arq_banner
FROM ARTISTA;

SELECT m.id, m.nome as "musica", m.duracao, m.arq_audio, al.nome as "album", ar.nome as "artista"
FROM musica m
JOIN ALBUM al on al.ID = m.COD_ALBUm
JOIN ARTISTA ar ON ar.id = al.cod_artista;

SELECT s.id, s.nome as "single", s.duracao, s.dt_lancamento, s.arq_audio, s.arq_capa, a.nome as "artista"
FROM single s
JOIN artista a on a.id = s.cod_artista;

-- update
UPDATE ALBUM
SET nome = 'Sgt. Peppers Lonely Hearts Club Band'
WHERE ID = 1;

-- delete
DELETE FROM ARTISTA
WHERE ID = 1;