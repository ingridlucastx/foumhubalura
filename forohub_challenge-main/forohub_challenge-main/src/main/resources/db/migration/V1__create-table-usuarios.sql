create table autores(
    id bigint not null auto_increment,
    login varchar(100) not null,
    clave varchar(300) not null,
    primary key(id)
);

insert into autores(id, login, clave) values(1, "german.gomez@email.com", "$2a$12$Wz2Pu4nPDTFaseP0IyMaM.T5c5TxVRRc3bmu4KU3Ck0ZX2PbHHviK");