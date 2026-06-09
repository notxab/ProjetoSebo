# Sistema de Gestão de Sebo

## Descrição
Aplicação desktop desenvolvida para o controle de acervo e operações de um sebo. O sistema permite realizar o cadastro, edição, exclusão e listagem de livros, além de gerenciar a associação com prateleiras físicas, garantindo a integridade dos dados e organização do inventário.

---

## Arquitetura
O projeto foi estruturado seguindo o padrão **MVC (Model-View-Controller)**, assegurando a separação de responsabilidades entre a lógica de negócio, a camada de persistência e a interface gráfica.

* **Model**: Representação das entidades e regras de negócio.
* **View**: Interface gráfica construída com Java Swing.
* **Controller**: Intermediação entre a view e os objetos de acesso a dados.

---

## Tecnologias Utilizadas
* **Linguagem**: Java (JDK 11 ou superior)
* **Banco de Dados**: MySQL
* **Persistência**: JDBC (Java Database Connectivity)
* **Interface**: Java Swing

---

## Funcionalidades
* **CRUD Completo**: Cadastro, leitura, atualização e remoção de livros.
* **Integridade Referencial**: Gerenciamento de prateleiras via chaves estrangeiras (Foreign Keys), garantindo que livros sejam alocados apenas em locais existentes.
* **Atualização em Tempo Real**: Listagem dinâmica de dados na tabela.
* **Automação**: Procedimentos armazenados (Stored Procedures) para otimização de consultas e operações de atualização.

---

## Como Executar

### Pré-requisitos
* Java Development Kit (JDK) configurado.
* MySQL Server instalado e em execução.
* IDE de sua preferência (Eclipse, IntelliJ ou NetBeans).

### Configuração
1. Clone este repositório:
   ```bash
   git clone <URL_DO_SEU_REPOSITORIO>
Importe o projeto em sua IDE como um projeto Java padrão.

Configuração do Banco:

Acesse a pasta /database deste projeto.

Importe o arquivo banco_sebo.sql no seu servidor MySQL (via Workbench ou linha de comando).

Caso necessário, ajuste as credenciais de acesso ao banco no arquivo de persistência da aplicação.

Estrutura do Repositório
/src: Código-fonte da aplicação (Pacotes: Pck_View, Pck_Control, Pck_Model, Pck_Persistencia).

/database: Script SQL para criação da estrutura do banco de dados.

Contribuição
Este projeto faz parte de um portfólio acadêmico/profissional. Sinta-se à vontade para abrir issues caso encontre erros ou sugestões de melhoria.


---
