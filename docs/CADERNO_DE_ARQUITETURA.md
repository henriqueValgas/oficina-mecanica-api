# 1 Visão geral do sistema

Projeto: Oficina Mecânica API

versão: 1.0

A Oficina-mecanica-api é um sistema desenvolvido em spring boot para genrenciamento de processos de oficina mecanica

Com o objetivo de acompanhhar todo o ciclo de atendimento controlando
cliente, veiculos, ordem de serviço, peças, tipos de serviços, estoque e pagamento
desde a entrada até a saida do veiculo.

# 2 Objetivos

- Aprender arquitetura de software
- Aplicar SOLID
- Aplicar DDD (Domain-Driver Design)
- utilizar Spring Boot
- Utilizar JPA/Hibernate
- Construir um api proxima do real

# 3 Dominio do Negócio

- O dominio principal do negócio e genrencia um oficina mecânica
gerando ordem de serviço que representa o atendimento ao veiculo que pertence a um cliente

# 4 Modulos do sistema

## Cadastro
- Clientes
- Funcionarios
- Fornecedores
- Marcas
- Modelos

## Veiculos
- Veiculos
- Historico

## Ordem de Serviço
- Ordem de serviço
- Itens de serviço
- Itens de peças

## Estoque
- Peças
- Movimentações

## Financeiros
- Pagementos

# 5 Nucleo do Sistema

- Cliente
-     ↓
- Veiculo
-     ↓
- Ordem de serviço
-     ↓
- Itens
-     ↓
- Peças/Serviços


# 6 Decisões arquiteturais

- As principais decisões arquiteturais se encontram documentadas na pasta
docs/adrs

Entre elas:

- ADR-001 - Modelagem de pessoas utilizando herança
- ADR-002 - Soft delete
- ADR-003 - Entidade Marca
- ADR-004 - Item de serviço
- ADR-005 - Item de peça

# 7 Arquitetura da aplicação

- Cliente
-     ↓
- Controller
-     ↓
- DTOs
-     ↓
- Mappers
-     ↓
- Service
-     ↓
- Repository
-     ↓
- Banco de dados
-     ↓
- Exceptions
-     ↓
- Configurações

Esta APi utiliza arquiteturas em camadas para separar as
responsabilidades, evitando o acoplamento, 
tornando a manutenção simples e facilitando possiveis refatorações

# 8 Tecnologias 
- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySql
- Maven
- Lombok
- MapStruct
- Bean Validation
- Swagger/OpenAPI
- Git
- GitHub

# 9 Evolução do projeto
## Concluido
- cadastro de pessoas
- Pessoa fisica
- Pessoa juridica
- Fornecedor
## Em desenvolvimento
- Marca
- Modelo
- Veículo
## Planejado
- Ordem de serviço
- Estoque
- Financeiro
- Relátorios




