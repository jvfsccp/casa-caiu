# Casa Caiu 🏠

<p align="center">
  <img src="https://skillicons.dev/icons?i=java,mysql" alt="Tecnologias utilizadas" />
</p>

## 1. Visão Geral

O **Casa Caiu** é um sistema de gestão imobiliária desenvolvido em Java com JDBC para interação com um banco de dados MySQL. Ele permite o gerenciamento completo de imóveis, clientes, corretores, propostas e visitas, oferecendo uma solução robusta para imobiliárias.

## 2. Funcionalidades

O sistema oferece um menu interativo para gerenciar diferentes aspectos do negócio imobiliário:

- **Gestão de Clientes:**
  - Cadastrar, listar, atualizar e excluir clientes.
- **Gestão de Corretores:**
  - Cadastrar, listar, atualizar e excluir corretores.
- **Gestão de Imóveis:**
  - Cadastrar, listar, atualizar e excluir imóveis.
  - Gerenciar tipos e status dos imóveis.
- **Gestão de Visitas:**
  - Agendar, listar, atualizar e cancelar visitas a imóveis.
- **Gestão de Propostas:**
  - Criar, listar, aceitar e recusar propostas de compra ou aluguel.
- **Registro de Interesses:**
  - Registrar o interesse de clientes por tipos específicos de imóveis.
- **Relatórios:**
  - Gerar relatórios para análise de dados do sistema.

## 3. Estrutura de Pastas

O projeto está organizado da seguinte forma para separar o código-fonte, as dependências e os arquivos compilados:

```
casa-caiu/
├── src/
│   ├── App.java
│   ├── db/
│   │   ├── ClienteDAO.java
│   │   ├── ConnectionFactory.java
│   │   ├── CorretorDAO.java
│   │   ├── ImovelDAO.java
│   │   ├── InteresseDAO.java
│   │   ├── PopularDados.java
│   │   ├── PropostaDAO.java
│   │   ├── StatusImovelDAO.java
│   │   ├── TipoImovelDAO.java
│   │   └── VisitaDAO.java
│   ├── models/
│   │   ├── Cliente.java
│   │   ├── Corretor.java
│   │   ├── Imovel.java
│   │   ├── Interesse.java
│   │   ├── Proposta.java
│   │   ├── StatusImovel.java
│   │   ├── TipoImovel.java
│   │   └── Visita.java
│   └── views/
│       ├── MenuClientes.java
│       ├── MenuCorretores.java
│       ├── MenuImoveis.java
│       ├── MenuInteresses.java
│       ├── MenuPrincipal.java
│       ├── MenuPropostas.java
│       ├── MenuRelatorios.java
│       └── MenuVisitas.java
├── lib/
│   └── (Dependências, como o conector MySQL)
├── bin/
│   └── (Arquivos .class compilados)
└── README.md
```

- `src`: Contém todo o código-fonte Java, dividido em pacotes `models`, `views` e `db` (DAO).
- `lib`: Armazena as bibliotecas e dependências externas, como o driver JDBC do MySQL.
- `bin`: Diretório onde os arquivos `.class` compilados são gerados.
