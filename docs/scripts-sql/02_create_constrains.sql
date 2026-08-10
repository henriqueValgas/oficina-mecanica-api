ALTER TABLE pessoa_fisica
ADD CONSTRAINT fk_pessoa_fisica_pessoa
FOREIGN KEY (id)
REFERENCES pessoa(id);

ALTER TABLE pessoa_juridica
ADD CONSTRAINT fk_pessoa_juridica_pessoa
FOREIGN KEY (id)
REFERENCES pessoa(id);

ALTER TABLE cliente_pf
ADD CONSTRAINT fk_pessoa_pf_pessoa_fisica
FOREIGN KEY (id)
REFERENCES pessoa_fisica(id);

ALTER TABLE cliente_pj
ADD CONSTRAINT fk_cliente_pj_pessoa_juridica
FOREIGN KEY (id)
REFERENCES pessoa_juridica(id);

ALTER TABLE funcionario
ADD CONSTRAINT fk_funcionario_pessoa_fisica
FOREIGN KEY (id)
REFERENCES pessoa_fisica(id);

ALTER TABLE fornecedor
ADD CONSTRAINT fk_fornecedor_pessoa_juridica
FOREIGN KEY (id)
REFERENCES pessoa_juridica(id);

ALTER TABLE endereco
ADD CONSTRAINT fk_endereco_pessoa
FOREIGN KEY (pessoa_id)
REFERENCES pessoa(id);

ALTER TABLE telefone
ADD CONSTRAINT fk_telefone_pessoa
FOREIGN KEY (pessoa_id)
REFERENCES pessoa(id);

ALTER TABLE peca
ADD CONSTRAINT fk_peca_marca
FOREIGN KEY (marca_id)
REFERENCES marca(id);

ALTER TABLE aplicacao
ADD CONSTRAINT fk_aplicacao_modelo
FOREIGN KEY (modelo_id)
REFERENCES modelo(id);

ALTER TABLE modelo
ADD CONSTRAINT fk_modelo_marca
FOREIGN KEY (marca_id)
REFERENCES marca(id);

ALTER TABLE veiculo
ADD CONSTRAINT fk_veiculo_modelo
FOREIGN KEY (modelo_id)
REFERENCES modelo(id);

ALTER TABLE historico_proprietario
ADD CONSTRAINT fk_historico_proprietario_pessoa
FOREIGN KEY (pessoa_id)
REFERENCES pessoa(id),
ADD CONSTRAINT fk_historico_proprietario_veiculo
FOREIGN KEY (veiculo_id)
REFERENCES veiculo(id);

ALTER TABLE item_peca
ADD CONSTRAINT fk_item_peca_peca
FOREIGN KEY (peca_id)
REFERENCES peca(id),
ADD CONSTRAINT fk_item_peca_ordem_servico
FOREIGN KEY (ordem_servico_id)
REFERENCES ordem_servico(id);

ALTER TABLE item_servico
ADD CONSTRAINT fk_item_servico_servico
FOREIGN KEY (servico_id)
REFERENCES servico(id),
ADD CONSTRAINT fk_item_servico_ordem_servico
FOREIGN KEY (ordem_servico_id)
REFERENCES ordem_servico(id);

ALTER TABLE ordem_servico
ADD CONSTRAINT fk_ordem_servico_pessoa
FOREIGN KEY (pessoa_id)
REFERENCES pessoa(id),
ADD CONSTRAINT fk_ordem_servico_veiculo
FOREIGN KEY (veiculo_id)
REFERENCES veiculo(id),
ADD CONSTRAINT fk_ordem_servico_funcionario
FOREIGN KEY (funcionario_id)
REFERENCES funcionario(id);

/**
 adicionando unique constraint
*/

/**
- unique em marca para garantir que so exista uma marca com cada nome
*/
ALTER TABLE marca
ADD CONSTRAINT uk_marca_nome
UNIQUE (nome);

/**
- para garantir nome de modelo unico na mesma marca, mas que nome possa
  se repetir em outras marcas foi adicionado unique composto de marca_id e nome
*/
ALTER TABLE modelo
ADD CONSTRAINT uk_modelo_marca_nome
UNIQUE (marca_id, nome);

/**
- Garante que o mesmo período de aplicação não seja
  cadastrado mais de uma vez para o mesmo modelo.
  Períodos diferentes para o mesmo modelo continuam permitidos.
*/
ALTER TABLE aplicacao
ADD CONSTRAINT uk_aplicacao_modelo_periodo
UNIQUE (modelo_id, ano_inicial, ano_final);

/**
  Adicionando CHECK constraint para validação das regras de integridade de dados
*/

ALTER TABLE item_peca
ADD CONSTRAINT ck_item_peca_quantidade
CHECK (quantidade > 0);

ALTER TABLE item_servico
ADD CONSTRAINT ck_item_servico_quantidade
CHECK (quantidade > 0);

ALTER TABLE aplicacao
ADD CONSTRAINT ck_aplicacao_ano_inicial_ano_final
CHECK (ano_final >= ano_inicial);

ALTER TABLE peca
ADD CONSTRAINT ck_peca_custo
CHECK (custo >= 0),
ADD CONSTRAINT ck_peca_valor_venda
CHECK (valor_venda >= 0);

ALTER TABLE servico
ADD CONSTRAINT ck_servico_tempo_previsto_minutos
CHECK (tempo_previsto_minutos >= 0),
ADD CONSTRAINT ck_servico_valor_padrao
CHECK (valor_padrao >= 0);
