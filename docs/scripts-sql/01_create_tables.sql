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
    matricula VARCHAR(20) NOT NULL UNIQUE,
    data_admissao DATE NOT NULL
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
    uf VARCHAR(2) NOT NULL,
    cep VARCHAR(9),
    tipo_endereco VARCHAR(20) NOT NULL,
    pessoa_id uuid NOT NULL
);

CREATE TABLE telefone (
    id uuid PRIMARY KEY,
    numero VARCHAR(20) NOT NULL,
    tipo_telefone VARCHAR(20) NOT NULL,
    pessoa_id uuid NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE peca (
    id uuid PRIMARY KEY,
    codigo VARCHAR(20) NOT NULL UNIQUE,
    nome VARCHAR(150) NOT NULL,
    descricao VARCHAR(255),
    custo NUMERIC(10,2) NOT NULL,
    valor_venda NUMERIC(10,2) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    marca_id uuid NOT NULL,
    created_by uuid,
    created_at TIMESTAMP,
    updated_by uuid,
    updated_at TIMESTAMP
);

CREATE TABLE aplicacao (
    id uuid PRIMARY KEY,
    ano_inicial INTEGER NOT NULL ,
    ano_final INTEGER NOT NULL ,
    observacoes VARCHAR(255),
    modelo_id uuid NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE servico (
    id uuid PRIMARY KEY,
    codigo VARCHAR(10) NOT NULL UNIQUE,
    nome VARCHAR(150) NOT NULL,
    descricao VARCHAR(255),
    tempo_previsto_minutos INTEGER NOT NULL,
    valor_padrao NUMERIC(10,2) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    created_by uuid,
    created_at TIMESTAMP,
    updated_by uuid,
    updated_at TIMESTAMP
);

CREATE TABLE marca (
    id uuid PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    pais_origem VARCHAR(100) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE modelo (
    id uuid PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    marca_id uuid NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE veiculo (
    id uuid PRIMARY KEY,
    placa VARCHAR(7) NOT NULL UNIQUE,
    chassi VARCHAR(30) NOT NULL UNIQUE,
    renavam VARCHAR(20) NOT NULL UNIQUE,
    ano_fabricacao INTEGER NOT NULL,
    ano_modelo INTEGER NOT NULL,
    quilometragem BIGINT NOT NULL,
    cor VARCHAR(60) NOT NULL,
    combustivel VARCHAR(30) NOT NULL,
    modelo_id uuid NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    created_by uuid,
    created_at TIMESTAMP,
    updated_by uuid,
    updated_at TIMESTAMP
);

CREATE TABLE historico_proprietario (
    id uuid PRIMARY KEY,
    data_inicio DATE NOT NULL,
    data_fim DATE,
    veiculo_id uuid NOT NULL,
    pessoa_id uuid NOT NULL
);

CREATE TABLE item_peca (
    id uuid PRIMARY KEY,
    valor_custo_unitario NUMERIC(10,2) NOT NULL ,
    valor_unitario NUMERIC(10,2) NOT NULL ,
    valor_total NUMERIC(10,2) NOT NULL ,
    desconto NUMERIC(10,2),
    quantidade INTEGER NOT NULL ,
    observacoes VARCHAR(255),
    peca_id uuid NOT NULL,
    ordem_servico_id uuid NOT NULL,
    created_by uuid,
    created_at TIMESTAMP,
    updated_by uuid,
    updated_at TIMESTAMP
);

CREATE TABLE item_servico (
    id uuid PRIMARY KEY,
    valor_unitario NUMERIC(10,2) NOT NULL ,
    valor_total NUMERIC(10,2) NOT NULL ,
    desconto NUMERIC(10,2),
    quantidade INTEGER NOT NULL ,
    observacoes VARCHAR(255),
    servico_id uuid NOT NULL,
    ordem_servico_id uuid NOT NULL,
    created_by uuid,
    created_at TIMESTAMP,
    updated_by uuid,
    updated_at TIMESTAMP
);

CREATE TABLE ordem_servico (
    id uuid PRIMARY KEY,
    numero_os VARCHAR(20) NOT NULL UNIQUE,
    quilometragem_entrada BIGINT NOT NULL,
    data_entrada DATE NOT NULL,
    data_aprovacao DATE,
    data_saida DATE,
    defeito_relatado VARCHAR(255) NOT NULL,
    diagnostico VARCHAR(255),
    observacoes VARCHAR(255),
    sub_total_peca NUMERIC(10,2),
    sub_total_servico NUMERIC(10,2),
    descontos NUMERIC(10,2),
    valor_total NUMERIC(10,2),
    status VARCHAR(20) NOT NULL,
    veiculo_id uuid NOT NULL,
    pessoa_id uuid NOT NULL,
    funcionario_id uuid NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    created_by uuid,
    created_at TIMESTAMP,
    updated_by uuid,
    updated_at TIMESTAMP
);
