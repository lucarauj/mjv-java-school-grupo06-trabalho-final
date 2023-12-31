CREATE TABLE public.cidade (
	id SERIAL,
	estado varchar(255) NOT NULL,
	nome varchar(255) NOT NULL,
	sigla varchar(255) NOT NULL,
	CONSTRAINT cidade_pkey PRIMARY KEY (id)
);

CREATE TABLE public.empresa (
	id SERIAL,
	nome varchar(255) NOT NULL,
	CONSTRAINT empresa_pkey PRIMARY KEY (id)
);

CREATE TABLE public.cadastro (
	id SERIAL,
	contato int8 NOT NULL,
	whatsapp bool NOT NULL,
	cpf varchar(255) NOT NULL,
	data_nascimento timestamp(6) NOT NULL,
	email varchar(255) NOT NULL,
	bairro varchar(255) NULL,
	cep int8 NULL,
	complemento varchar(255) NULL,
	logradouro varchar(255) NULL,
	numero varchar(255) NULL,
	habilidades varchar[] NOT NULL,
	nome varchar(255) NOT NULL,
	sexo_enum varchar(255) NULL,
	telefone int8 NOT NULL,
	cidade_id int4 NULL,
	valor_minimo float8 NOT NULL,
	valor_maximo float8 NOT NULL,
	profissao_id int4 NOT NULL,
	CONSTRAINT cadastro_pkey PRIMARY KEY (id),
	CONSTRAINT cadastro_sexo_enum_check CHECK (((sexo_enum)::text = ANY ((ARRAY['MASCULINO'::character varying, 'FEMININO'::character varying])::text[]))),
	CONSTRAINT fkjx8jpsixkalofngbn4psigw4r FOREIGN KEY (cidade_id) REFERENCES public.cidade(id)
);

CREATE TABLE public.cadastro_experiencia (
	id SERIAL,
	data_contratacao timestamp(6) NOT NULL,
	desligamento timestamp(6) NOT NULL,
	emprego_atual bool NOT NULL,
	regime_contratacao_enum varchar(255) NULL,
	salario float8 NOT NULL,
	empresa_id int4 NULL,
	cadastro_id int4 NULL,
	CONSTRAINT cadastro_experiencia_pkey PRIMARY KEY (id),
	CONSTRAINT cadastro_experiencia_regime_contratacao_enum_check CHECK (((regime_contratacao_enum)::text = ANY ((ARRAY['CLT'::character varying, 'PJ'::character varying, 'COP'::character varying, 'AUT'::character varying])::text[]))),
	CONSTRAINT fk38j9ujoyacx7qa3ybmt8jg6cl FOREIGN KEY (empresa_id) REFERENCES public.empresa(id),
	CONSTRAINT fkf9ds42brydhto0g05riup2mgo FOREIGN KEY (cadastro_id) REFERENCES public.cadastro(id)
);

CREATE TABLE public.profissao (
	id SERIAL,
	nome varchar(255) NOT NULL,
	cadastro_id int4 NULL,
	CONSTRAINT profissao_pkey PRIMARY KEY (id),
	CONSTRAINT fklqdnrp9sci6y6ej7ve0l7j10n FOREIGN KEY (cadastro_id) REFERENCES public.cadastro(id)
);