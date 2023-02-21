create database sbd1;
use sbd1;

CREATE TABLE user(
idUser int NOT NULL auto_increment ,
blocked boolean default(0) not null,
name varchar(100) NOT NULL unique,
nacionalidade varchar(100) NOT NULL,
dataNascimento date NOT NULL,
reputacao int default(0),
userTipo int default(1) ,
password varchar(100) not null,
constraint pk_user primary key(idUser),
constraint reputacao CHECK(reputacao >= 0),
constraint userTipo CHECK(userTipo >=1 and userTipo <=3)
);

/*
CREATE TABLE admin(
idUser int not null, #foregin key para user,
constraint pk_admin primary key(idUser)

);
ALTER TABLE admin ADD CONSTRAINT fk_admin FOREIGN KEY (idUser) REFERENCES user(idUser) ON DELETE CASCADE;
*/
create table recursoTipo(
	recurso_tipo_codigo char(3) not null,
    primary key  (recurso_tipo_codigo)
);

create table recursoIdadeTipo(
	recurso_idade_codigo char(4) not null,
    primary key  (recurso_idade_codigo)
);


CREATE TABLE recurso_multimedia(
idRecurso int NOT NULL auto_increment,#fica
RecursoTipo char(3) not null ,
ilustracao longblob not null,
conteudo longblob not null,
titulo varchar(100) NOT NULL,
dataCarregamento date NOT NULL,
resumo varchar(5000),
estadoRecurso bool default(true),
userCriador int NOT NULL,
idadeTipo char(4) not null,
constraint pk_recurso primary key(idRecurso)
);

#foreign key recurso userCriador
alter table recurso_multimedia add constraint fk_recurso_multimedia_user foreign key (userCriador) references user(idUser)  ON DELETE CASCADE ;
alter table recurso_multimedia add constraint fk_recurso_multimedia_recurso_tipo foreign key (RecursoTipo) references recursoTipo(recurso_tipo_codigo)  ON DELETE CASCADE ;
alter table recurso_multimedia add constraint fk_recurso_multimedia_recursoIdadeTipo foreign key (idadeTipo) references recursoIdadeTipo(recurso_idade_codigo)  ON DELETE CASCADE ;


CREATE TABLE comentario(
comentarioId int not null auto_increment,
UserComentador int not NULL,
RecursoId int not null,
comentario varchar(5000) NOT NULL,
#classificacao int ,
dataComentario date not null , 
constraint pk_comentario primary key(comentarioId)
#constraint classificacao CHECK(classificacao >= 0 and classificacao <=5)
);

#FOREIGN KEYS COMENTARIO
alter table comentario add constraint fk_comentario_comentador foreign key (UserComentador) references user(idUser) ON DELETE CASCADE;
alter table comentario add constraint fk_comentario_recurso foreign key (RecursoId) references recurso_multimedia(idRecurso) ON DELETE CASCADE;


CREATE TABLE classificacao(
classificacaoId int not null auto_increment,
UserClassificador int not NULL,
RecursoId int not null,
classificacaoVal int not null ,
constraint pk_classificacao primary key(classificacaoId),
constraint classificacaoVal CHECK(classificacaoVal >= 0 and classificacaoVal <=5)
);


alter table classificacao add constraint fk_classificacao_classificador foreign key (UserClassificador) references user(idUser) ON DELETE CASCADE;
alter table classificacao add constraint fk_classificacao_recurso foreign key (RecursoId) references recurso_multimedia(idRecurso) ON DELETE CASCADE;


CREATE TABLE artista(

idArtista int NOT NULL auto_increment,
artista_name varchar(100) NOT NULL,
constraint pk_artista primary key(idArtista)
);

create table ator(
	idAtor int not null,
    tag char (3) default "ATR",
    constraint fk_ator foreign key (idAtor) references artista(idArtista),
    constraint pk_ator primary key(idAtor)
);


create table realizador(
	idRealizador  int not null,
	tag char (3) default "RLZ",
    constraint fk_realizador foreign key (idRealizador) references artista(idArtista),
    constraint pk_realizador primary key(idRealizador)
);

create table artistaLetra(
	idArtistaLetra int not null,
    tag char (3) default "ATL",
    constraint fk_artistaLetra foreign key (idArtistaLetra) references artista(idArtista),
    constraint pk_artistaLetra primary key(idArtistaLetra)
);

create table artistaMusica(
	idArtistaMusica int not null,
    tag char (3) default "ATM",
    constraint fk_artistaMusica foreign key (idArtistaMusica) references artista(idArtista),
    constraint pk_artistaMusica primary key(idArtistaMusica)
);

create table fotografo(
	idFotografo int not null,
    tag char (3) default "FTG",
    constraint fk_fotografo foreign key (idFotografo) references artista(idArtista),
    constraint pk_fotografo primary key(idFotografo)
);
create table poeta(
	IdPoeta int not null,
    tag char (3) default "PTA",
    constraint fk_poeta foreign key (IdPoeta) references artista(idArtista),
    constraint pk_poeta primary key(IdPoeta)
);


CREATE TABLE filme(

anoLancamento date NOT NULL,
idRealizador int NOT NULL,
idRecurso int not NULL,
constraint pk_filme primary key(idRecurso)

);

#FOREIGN KEYS FILMES
alter table filme add constraint fk_filme_realizador foreign key (idRealizador) references realizador(idRealizador) ON DELETE CASCADE;
alter table filme add constraint fk_filme_recurso foreign key (idRecurso) references recurso_multimedia(idRecurso)  ON DELETE CASCADE;


CREATE TABLE musica(

idAutor int NOT NULL,
idAutorLetra int NOT NULL,
idRecurso int not NULL,
constraint pk_musica primary key(idRecurso));


#FOREIGN KEYS MUSICA
alter table musica add constraint fk_musica_autor foreign key (idAutor) references artistaMusica(idArtistaMusica) ON DELETE CASCADE;
alter table musica add constraint fk_musica_autor_letra foreign key (idAutorLetra) references artistaLetra(idArtistaLetra) ON DELETE CASCADE;
alter table musica add constraint fk_musica_recurso foreign key (idRecurso) references recurso_multimedia(idRecurso) ON DELETE CASCADE;


CREATE TABLE fotografia(

idFotografo int NOT NULL,
idRecurso int not null,
constraint pk_fotografia primary key(idRecurso)
);
#FOREIGN KEYS FOTOGRAFIA
alter table fotografia add constraint fk_fotografia_fotografo foreign key (idFotografo) references fotografo(idFotografo)  ON DELETE CASCADE;
alter table fotografia add constraint fk_fotografia_recurso foreign key (idRecurso) references recurso_multimedia(idRecurso) ON DELETE CASCADE;


CREATE TABLE poema(

idRecurso int not null,
constraint pk_poema primary key(idRecurso)
);

#FOREIGN KEYS POEMA
alter table poema add constraint fk_poema_recurso foreign key (idRecurso) references recurso_multimedia(idRecurso) ON DELETE CASCADE;

CREATE TABLE ator_atua_filme(
idAtor int not null,
idFilme int not null,
constraint pk_atua primary key (idAtor,idFilme),
constraint fk_atua_ator foreign key (idAtor) references ator(idAtor),
constraint fk_atua_filme foreign key (idFilme) references filme(idRecurso)
);

CREATE TABLE poeta_atua_poesia(

idPoeta int not null,
idPoema int not null,
constraint pk_atua primary key (idPoeta,idPoema),
constraint fk_atua_poeta foreign key (idPoeta) references poeta(IdPoeta),
constraint fk_atua_poema foreign key (idPoema) references poema(idRecurso)
);

create table associacao(
idAssociacao int NOT NULL auto_increment ,
idRecursoPrincipal  int not null,
idRecursoSecundario  int not null,
constraint pk_associacao primary key(idAssociacao),
constraint fk_recurso_principal foreign key (idRecursoPrincipal) references recurso_multimedia(idRecurso),
constraint fk_recurso_secundario foreign key (idRecursoSecundario) references recurso_multimedia(idRecurso)

)

