CREATE TABLE artista (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(200),
    arq_banner BYTEA
);

CREATE TABLE album (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(200),
    dt_lancamento DATE,
    arq_capa BYTEA,
    cod_artista INT REFERENCES ARTISTA(id)
);

CREATE TABLE single (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(200),
    duracao double precision,
    dt_lancamento DATE,
    arq_audio BYTEA,
    arq_capa BYTEA,
    cod_artista INT REFERENCES ARTISTA(id)
);

CREATE TABLE musica (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(200),
    duracao double precision,
    arq_audio BYTEA,
    cod_album INT REFERENCES ALBUM(id)
);