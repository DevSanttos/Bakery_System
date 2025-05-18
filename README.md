# 🥖 Sistema de Padaria em Java

Projeto acadêmico desenvolvido por quatro estudantes com o objetivo de aplicar os conceitos de Programação Orientada a Objetos, banco de dados e interfaces gráficas utilizando Java com Swing.

## 📋 Descrição

Este sistema simula a rotina de uma padaria, permitindo o gerenciamento de produtos, funcionários, vendas e estoque. A aplicação foi construída com Java, possui uma interface gráfica criada com Swing e faz integração com um banco de dados relacional (PostgreSQL), permitindo a persistência dos dados.

## 💡 Funcionalidades

- Cadastro, edição e exclusão de produtos (pães, doces, bolos etc.)
- Controle de estoque
- Cadastro de funcionários
- Registro de vendas
- Relatórios de movimentação
- Interface gráfica amigável (Java Swing)
- Persistência de dados com banco de dados PostgreSQL

## 🛠️ Tecnologias Utilizadas

- **Java 17+**
- **Java Swing**
- **PostgreSQL**
- **JDBC (Java Database Connectivity)**
- IDE utilizada: NetBeans / IntelliJ 

## 🧑‍💻 Integrantes

- **Jonathan Rezende dos Santos** – [@DevSanttos](https://github.com/DevSanttos)  
- **José Otávio Ávila Alves Pires** – [@Saigo0](https://github.com/Saigo0)  
- **Nataniel de Oliveira Da Silva** – [@natan1iel](https://github.com/natan1iel)  
- **Guilherme Costa** – [@TioPinas](https://github.com/TioPinas)  

## 📁 Organização do Projeto

/src

├── model → Classes de domínio e conexão com banco

├── view → Interface gráfica com Swing

├── service → Centraliza a lógica de negócio da aplicação

└── controller → Lógica de negócio

/database

└── script.sql → Script de criação do banco de dados


## 🧪 Como executar o projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/DevSanttos/sistema-padaria-java.git
 
2. Importe o projeto na sua IDE Java.

3. Execute o script script.sql no seu servidor PostgreSQL para criar o banco de dados.

4. Ajuste as configurações de conexão com o banco de dados no código (usuário, senha e URL).

5. Execute o projeto.

💡 Obs.: Certifique-se de que o driver JDBC está adicionado às dependências do seu projeto.

📝 Licença
Este projeto é de uso educacional e está sob a licença MIT. Sinta-se à vontade para estudar, modificar e contribuir!
