select * from sbd1.user;
insert into user (name,nacionalidade,dataNascimento,password,userTipo) values ("Duarte","Madorna",'1966-09-21',"oeiras",1);
insert into user (name,nacionalidade,dataNascimento,password,userTipo) values ("Chucky","SAC",'1998-09-28',"piimps",2);
insert into user (name,nacionalidade,dataNascimento,password,userTipo) values ("Diogo","Almeirim",'1997-01-02',"png",3);
insert into user (name,nacionalidade,dataNascimento,password,userTipo) values ("Jonny","Cacem",'1998-10-12',"xd123",2);
insert into user (name,nacionalidade,dataNascimento,password,userTipo) values ("Bruno","Tavira",'1998-05-17',"benfica132",1);
insert into user (name,nacionalidade,dataNascimento,password,userTipo,reputacao) values ("xd","man",'1998-05-17',"benfica132",1,10);

SELECT * FROM user;



insert into recursoTipo values ("flm"),("msc"),("ftg"),("pma");
select * from sbd1.recursoTipo;

insert into recursoIdadeTipo values ("G"),("PG"),("PG13"),("R"),("NC17");
select * from sbd1.recursoIdadeTipo;


 insert into recurso_multimedia (RecursoTipo,titulo,ilustracao,conteudo,dataCarregamento,resumo,userCriador,idadeTipo)
 values ("flm","Velocidade Furiosa","img","cont",'2019-03-04',"Um oficial da polícia de Los Angeles Brian OConnor vê a
 sua lealdade para com a policia quando entra no mundo de corridas ilegais como infiltrado e se sente fascinado, pondo assim em causa de que lado escolherá ficar.",1,"G");
 
 insert into recurso_multimedia (RecursoTipo,titulo,ilustracao,conteudo,dataCarregamento,resumo,userCriador,idadeTipo)
 values ("msc","Candles","img","cont",'2018-08-21',"",2,"NC17");
 
insert into recurso_multimedia (RecursoTipo,titulo,ilustracao,conteudo,dataCarregamento,resumo,userCriador,idadeTipo)
values ("ftg","benfica","img","cont",'2019-09-01',"",4,"G");
 
 insert into recurso_multimedia (RecursoTipo,titulo,ilustracao,conteudo,dataCarregamento,resumo,userCriador,idadeTipo)
 values ("pma","Amor e fogo que arde sem se ver","img","Amor é fogo que arde sem se ver;
É ferida que dói e não se sente;
É um contentamento descontente;
É dor que desatina sem doer;

É um não querer mais que bem querer;
É solitário andar por entre a gente;
É nunca contentar-se de contente;
É cuidar que se ganha em se perder;

É querer estar preso por vontade;
É servir a quem vence, o vencedor;
É ter com quem nos mata lealdade.

Mas como causar pode seu favor
Nos corações humanos amizade,
Se tão contrário a si é o mesmo Amor?",'2018-04-01',"",1,"PG13");

 insert into recurso_multimedia (RecursoTipo,titulo,ilustracao,conteudo,dataCarregamento,resumo,userCriador,idadeTipo)
 values ("flm","Primer","img","cont",'2019-03-09',"Four friends/fledgling entrepreneurs, knowing that there's something bigger and more innovative than the different error-checking devices they've built, wrestle over their new invention.",2,"NC17");
 
 insert into recurso_multimedia (RecursoTipo,titulo,ilustracao,conteudo,dataCarregamento,resumo,userCriador,idadeTipo)
 values ("flm","American Pie: A Primeira Vez","img","cont",'2019-03-19',"Four teenage boys enter a pact to lose their virginity by prom night.",1,"NC17");
 
 insert into recurso_multimedia (RecursoTipo,titulo,ilustracao,conteudo,dataCarregamento,resumo,userCriador,idadeTipo)
 values ("msc","kyoto","img","cont",'2020-08-21',"",1,"NC17");
 
 insert into recurso_multimedia (RecursoTipo,titulo,ilustracao,conteudo,dataCarregamento,resumo,userCriador,idadeTipo)
 values ("msc","The Heart Part 4","img","cont",'2020-09-28',"",4,"NC17");
 
 insert into recurso_multimedia (RecursoTipo,titulo,ilustracao,conteudo,dataCarregamento,resumo,userCriador,idadeTipo)
 values ("ftg","DB","img","cont",'2019-09-01',"",5,"G");
 
 insert into recurso_multimedia (RecursoTipo,titulo,ilustracao,conteudo,dataCarregamento,resumo,userCriador,idadeTipo)
 values ("ftg","PNG","img","cont",'2019-03-01',"",3,"R");
 
 insert into recurso_multimedia (RecursoTipo,titulo,ilustracao,conteudo,dataCarregamento,resumo,userCriador,idadeTipo)
 values ("pma","Isto","img","Dizem que finjo ou minto
Tudo que escrevo. Não.
Eu simplesmente sinto
Com a imaginação.
Não uso o coração.

Tudo o que sonho ou passo,
O que me falha ou finda,
É como que um terraço
Sobre outra coisa ainda.
Essa coisa é que é linda.

Por isso escrevo em meio 
Do que não está de pé,
Livre do meu enleio,
Sério do que não é.
Sentir? Sinta quem lê! 
",'2018-04-01',"",1,"PG13");

insert into recurso_multimedia (RecursoTipo,titulo,ilustracao,conteudo,dataCarregamento,resumo,userCriador,idadeTipo)
 values ("pma","Já não me importo","img","Já não me importo
Até com o que amo ou creio amar.
Sou um navio que chegou a um porto
E cujo movimento é ali estar.

Nada me resta
Do que quis ou achei.
Cheguei da festa
Como fui para lá ou ainda irei

Indiferente
A quem sou ou suponho que mal sou,

Fito a gente
Que me rodeia e sempre rodeou,

Com um olhar
Que, sem o poder ver,
Sei que é sem ar
De olhar a valer.

E só me não cansa
O que a brisa me traz
De súbita mudança
No que nada me faz.",'2018-04-01',"",1,"PG13");
 
 select * from sbd1.recurso_multimedia;

 
 insert into comentario(UserComentador,RecursoId,comentario,dataComentario)
 values (4,1,"Bom Skyline, tá memo do aço",'2019-03-20');
 
insert into comentario(UserComentador,RecursoId,comentario,dataComentario)
 values (2,3,"Melhor do mundo", '2019-09-02');
 
 insert into comentario(UserComentador,RecursoId,comentario,dataComentario)
 values (2,3,"Melhor xd mundo", '2017-09-02');
 
 insert into comentario(UserComentador,RecursoId,comentario,dataComentario)
 values (5,2,"5 estrelas", '2018-08-23');
 
 select * from sbd1.comentario;
 
 select * from sbd1.classificacao;
 insert into classificacao(UserClassificador,RecursoId,classificacaoVal)
 values (2,2,"5");
 
 insert into classificacao(UserClassificador,RecursoId,classificacaoVal)
 values (1,1,"4");
 
 insert into classificacao(UserClassificador,RecursoId,classificacaoVal)
 values (4,4,"3");
 
 select * from classificacao;
 
 
 select * from sbd1.artista;
 
 #actor
 insert into artista(artista_name) values("Vin Diesel");
 insert into artista(artista_name) values("Paul Walker");
 insert into artista(artista_name) values("Michelle Rodriguez");
 
 #realizador                       Vin Disel
 insert into artista(artista_name) values("Christopher Nolan");
 insert into artista(artista_name) values("David Fincher");
 insert into artista(artista_name) values("Quentin Tarantino");
 
 #musica
 insert into artista(artista_name) values("Juice wrld");
 insert into artista(artista_name) values("Yung Lean");
 insert into artista(artista_name) values("Travis Scott");
 insert into artista(artista_name) values("Eminem");
 insert into artista(artista_name) values("Lil Uzi Vert");
 #letra
 insert into artista(artista_name) values("Freddie Gibbs");
 insert into artista(artista_name) values("Kendrick Lamar");
 insert into artista(artista_name) values("John Lennon");
 
 #fotografo
 insert into artista(artista_name) values("Diogo Bernardão");
 insert into artista(artista_name) values("Nuno Crx");
 insert into artista(artista_name) values("João Monteiradas");
 
 insert into artista(artista_name) values("Luís de Camões");
 insert into artista(artista_name) values("Fernando Pessoa");
 insert into artista(artista_name) values("Florbela Espanca");
 
 insert into artista(artista_name) values("21 Savage");
 select * from sbd1.artista;
 
 insert into ator(idAtor) values(1);
 insert into ator(idAtor) values(2);
 insert into ator(idAtor) values(3);
 select * from sbd1.ator;
 
 insert into realizador(idRealizador) values(1);
 insert into realizador(idRealizador) values(4);
 insert into realizador(idRealizador) values(5);
 insert into realizador(idRealizador) values(6);
 select * from sbd1.realizador;
 
 insert into artistaMusica(idArtistaMusica) values(7);
 insert into artistaMusica(idArtistaMusica) values(8);
 insert into artistaMusica(idArtistaMusica) values(9);
 insert into artistaMusica(idArtistaMusica) values(10);
 insert into artistaMusica(idArtistaMusica) values(11);
 insert into artistaMusica(idArtistaMusica) values(12);
 insert into artistaMusica(idArtistaMusica) values(13);
 insert into artistaMusica(idArtistaMusica) values(14);
 insert into artistaMusica(idArtistaMusica) values(21);
 select * from sbd1.artistaMusica;
 
 insert into artistaLetra(idArtistaLetra) values(7);
 insert into artistaLetra(idArtistaLetra) values(10);
 insert into artistaLetra(idArtistaLetra) values(14);
 select * from sbd1.artistaLetra;
 
 insert into fotografo(idFotografo) values(15);
 insert into fotografo(idFotografo) values(16);
 insert into fotografo(idFotografo) values(17);
 select * from sbd1.fotografo;
 
 insert into poeta(IdPoeta) values(18);
 insert into poeta(IdPoeta) values(19);
 insert into poeta(IdPoeta) values(20);
 select * from sbd1.poeta;
 
  
 insert into filme(anoLancamento,idRealizador,idRecurso)
 values ('2001-05-20',1,1);
 insert into filme(anoLancamento,idRealizador,idRecurso)
 values ('2006-01-19',4,5);
 insert into filme(anoLancamento,idRealizador,idRecurso)
 values ('1999-12-10',5,6);
 select * from sbd1.filme;

 insert into musica(idAutor,idAutorLetra,idRecurso)
 values ( 7,10,2);
 insert into musica(idAutor,idAutorLetra,idRecurso)
 values ( 8,10,7);
 insert into musica(idAutor,idAutorLetra,idRecurso)
 values ( 13,7,8);
 select * from sbd1.musica;
 
 insert into fotografia(idFotografo, idRecurso)
 values (15,3);
 insert into fotografia(idFotografo, idRecurso)
 values (16,9);
 insert into fotografia(idFotografo, idRecurso)
 values (17,10);
 select * from sbd1.fotografia;
 
insert into poema(idRecurso)
values (4);
insert into poema(idRecurso)
values (11);
insert into poema(idRecurso)
values (12);
select * from sbd1.poema;

select * from sbd1.recurso_multimedia;
select * from sbd1.artista;

insert into ator_atua_filme(idAtor,idFilme)
values (1,1);
insert into ator_atua_filme(idAtor,idFilme)
values (2,1);
insert into ator_atua_filme(idAtor,idFilme)
values (3,5);
insert into ator_atua_filme(idAtor,idFilme)
values (3,6);
select * from sbd1.ator_atua_filme;

insert into poeta_atua_poesia(idPoeta,idPoema)
values (18,4);
insert into poeta_atua_poesia(idPoeta,idPoema)
values (19,11);
insert into poeta_atua_poesia(idPoeta,idPoema)
values (19,12);
select * from sbd1.poeta_atua_poesia;

insert into associacao(idRecursoPrincipal,idRecursoSecundario) values ( 3,5);
insert into associacao(idRecursoPrincipal,idRecursoSecundario) values (3,6);
insert into associacao(idRecursoPrincipal,idRecursoSecundario) values (1,7);

select * from sbd1.associacao where idRecursoPrincipal=1 and idRecursoSecundario=11;

select idRecursoSecundario,titulo from associacao
inner join sbd1.recurso_multimedia on  recurso_multimedia.idRecurso = associacao.idRecursoSecundario
where idRecursoPrincipal=1 
;

UPDATE sbd1.user SET blocked = 1 WHERE idUser = 2;

select * from sbd1.user;

SELECT COUNT(idRecurso) AS numberr FROM sbd1.recurso_multimedia where userCriador= 2; 

