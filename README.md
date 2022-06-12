# Projeto_Final_POO
Cod para gerar a tabela no PG


create table itens(
	id bigserial primary key,
	titulo varchar(50),
	local varchar(40),
	observacao varchar(200),
	status varchar(10),
	data date
);
