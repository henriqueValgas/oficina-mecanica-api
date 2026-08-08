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
