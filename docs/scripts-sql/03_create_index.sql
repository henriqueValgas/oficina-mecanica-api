/**
- indice para acelerar a consulta de enderecos de pessoa

  uma pessoa pode possuir varios endereços e as
  consultas serão realizadas utilizando pessoa_id
*/
CREATE INDEX idx_endereco_pessoa
ON endereco (pessoa_id);

/**
- indice para acelerar a consulta de telefone de pessoa

  uma pessoa pode pussuir varios telefones e as
  consultas serão realizadas utilizando pessoa_id
*/
CREATE INDEX idx_telefone_pessoa
ON telefone (pessoa_id);

/**
- indice para acelerar a consulta de veiculo petencentes a um modelo

  permiti consultar rapidamente veiculos pertencentes a determinado modelo
  através do modelo_id
*/
CREATE INDEX idx_veiculo_modelo
ON veiculo (modelo_id);

/**
- indice para acelerar a consulta do historico  do proprietario
  de um determinado veiculo

  o historico esta relacionado com veiculo atraves do id, permitindo
  localizar rapidamente seus registros historicos
*/
CREATE INDEX idx_historico_proprietario_veiculo
ON historico_proprietario (veiculo_id);

/**
- indice para acelerar a busca de peca em ordem de serviço

  uma ordem de serviço pode possuir varios itens de peça,
  sendo necessário localizar esses itens atraves da ordem_servico_id
*/
CREATE INDEX idx_item_peca_ordem_servico
ON item_peca (ordem_servico_id);

/**
- indice para acelar a busca de servico em ordem de serviços

  uma ordem de serviço pode possuir varios tipos item de serviços
  sendo necessário localizar esses itens atraves da ordem_servico_id
*/
CREATE INDEX idx_item_servico_ordem_servico
ON item_servico (ordem_servico_id);

/**
- indice para acelerar a consulta das ordens de serviço
  associado a um determinado veiculo

  permiti consultar rapidamente o historico de servico realizados
  a um determinado veiculo atraves de veiculo_id
*/
CREATE INDEX idx_ordem_servico_veiculo
ON ordem_servico (veiculo_id);

/**
- indice para acelar a consulta das ordens de serviço associado
  a um determinado cliente

  permiti consultar rapidamente o historico de servico realizados
  para determinado cliente atraves de pessoa_id
*/
CREATE INDEX idx_ordem_servico_pessoa
ON ordem_servico (pessoa_id);

/**
- Índice para acelerar a consulta das ordens de serviço
  associadas a um determinado funcionário.

  permite localizar rapidamente as ordens de serviço
  atribuídas ao funcionário através de funcionario_id.
 */
CREATE INDEX idx_ordem_servico_funcionario
ON ordem_servico (funcionario_id);
