CREATE TABLE pessoa (
    id uuid PRIMARY KEY,
    email VARCHAR(255) UNIQUE,
    ativo BOOLEAN NOT NULL,
    created_by uuid,
    created_at TIMESTAMP,
    updated_by uuid,
    updated_at TIMESTAMP
);

CREATE TABLE pessoa_fisica (
    id uuid PRIMARY KEY,
    nome VARCHAR(150) NOT NULL ,
    cpf VARCHAR(14) NOT NULL UNIQUE
);

CREATE TABLE pessoa_juridica (
    id uuid PRIMARY KEY,
    cnpj VARCHAR(18) NOT NULL UNIQUE,
    razao_social VARCHAR(150) NOT NULL ,
    nome_fantasia VARCHAR(150) NOT NULL ,
    inscricao_estadual VARCHAR(20) NOT NULL ,
    observacoes VARCHAR(500)
);

CREATE TABLE cliente_pf (
    id uuid PRIMARY KEY
);

CREATE TABLE cliente_pj (
    id uuid PRIMARY KEY
);

CREATE TABLE funcionario (
    id uuid PRIMARY KEY,
    cargo VARCHAR(20) NOT NULL,
    matricula VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE fornecedor (
    id uuid PRIMARY KEY
);

CREATE TABLE endereco (
    id uuid PRIMARY KEY,
    rua VARCHAR(150) NOT NULL,
    numero VARCHAR(20) NOT NULL,
    complemento VARCHAR(100),
    bairro VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    uf CHAR(2) NOT NULL,
    cep VARCHAR(8),
    tipo_endereco VARCHAR(20) NOT NULL
);

CREATE TABLE telefone (
    id uuid PRIMARY KEY,
    numero VARCHAR(20) NOT NULL,
    tipo_telefone VARCHAR(20) NOT NULL
);

CREATE TABLE peca (
    id uuid PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    descricao VARCHAR(255),
    custo NUMERIC(10,2) NOT NULL,
    valor_venda NUMERIC(10,2) NOT NULL,
    ativo BOOLEAN NOT NULL,
    created_by uuid,
    created_at TIMESTAMP,
    updated_by uuid,
    updated_at TIMESTAMP
);

CREATE TABLE aplicacao (
    id uuid PRIMARY KEY,
    ano_inicial INTEGER NOT NULL ,
    ano_final INTEGER NOT NULL ,
    observacoes VARCHAR(255)
);

CREATE TABLE servico (
    id uuid PRIMARY KEY,
    codigo VARCHAR(10) NOT NULL,
    nome VARCHAR(150) NOT NULL,
    descricao VARCHAR(255),
    tempo_previsto_minutos INTEGER NOT NULL,
    valor_padrao NUMERIC(10,2) NOT NULL,
    ativo BOOLEAN NOT NULL,
    created_by uuid,
    created_at TIMESTAMP,
    updated_by uuid,
    updated_at TIMESTAMP
);
