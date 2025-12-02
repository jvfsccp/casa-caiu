# 🧩 Divisão de Tarefas – Sistema Imobiliário (POO + JDBC + MySQL)

---

## 📘 Template Estruturado do Projeto

### **📌 Nome do Projeto:** Sistema Imobiliário – Casa Caiu  
### **📌 Linguagem:** Java  
### **📌 Banco de Dados:** MySQL  
### **📌 Integração:** JDBC  
### **📌 Pilares:** POO, Herança, Polimorfismo, Encapsulamento, Interface  

---

# 📂 Estrutura Recomendada do Projeto

```
src/
 ├─ App.java
 ├─ model/
 │   ├─ Pessoa.java
 │   ├─ Cliente.java
 │   ├─ Corretor.java
 │   ├─ Imovel.java
 │   ├─ Casa.java
 │   ├─ Apartamento.java
 │   ├─ SalaComercial.java
 │   ├─ Visita.java
 │   └─ Proposta.java
 ├─ db/
 │   ├─ ConnectionFactory.java
 │   ├─ ImovelDAO.java
 │   ├─ ClienteDAO.java
 │   ├─ CorretorDAO.java
 │   ├─ VisitaDAO.java
 │   └─ PropostaDAO.java
 ├─ service/
 │   └─ SistemaImobiliarioService.java
 └─ view/
     ├─ MenuPrincipal.java
     ├─ MenuImovel.java
     ├─ MenuCliente.java
     ├─ MenuCorretor.java
     ├─ MenuVisita.java
     └─ MenuProposta.java
```

---

# 👥 Divisão de Tarefas

## 👤 **Pessoa 1 – Modelagem + Banco de Dados**

### **Responsabilidades**
- Criar o **DER** (Entidade-Relacionamento) contendo:
  - `imovel`, `cliente`, `corretor`, `visita`, `proposta`
- Produzir versão **normalizada** das tabelas
- Criar script SQL contendo:
  - `CREATE DATABASE casa_caiu;`
  - `CREATE TABLE` com chaves primárias e estrangeiras
  - `INSERT` de dados de teste
- Garantir:
  - integridade referencial
  - consistência dos tipos
  - documentação do esquema

---

## 👤 **Pessoa 2 – Modelos Java (POO)**

### **Classes a Implementar**
- `Pessoa` (classe base)
- `Cliente`, `Corretor`
- `Imovel` (classe abstrata)
- `Casa`, `Apartamento`, `SalaComercial`
- `Visita`, `Proposta`
- Interface `Calculavel`

### **Requisitos**
- Construtores bem definidos
- Getters e Setters
- Métodos `toString()`
- Aplicação de polimorfismo:
  - Ex.: `List<Imovel>` contendo objetos de subclasses diferentes

---

## 👤 **Pessoa 3 – DAO + JDBC + Tratamento de Exceções**

### **Responsabilidades**
- Criar classe `ConnectionFactory`
- Implementar DAOs:
  - `ImovelDAO`
  - `ClienteDAO`
  - `CorretorDAO`
  - `VisitaDAO`
  - `PropostaDAO`
- Utilizar:
  - `PreparedStatement`
  - `ResultSet`
  - `try/catch` para tratar `SQLException`
- Implementar CRUD completo e consultas especiais
- Retornar listas usando `ArrayList<>`

---

## 👤 **Pessoa 4 – View (Menus) + Regras de Negócio (Service)**

### **Responsabilidades**
- Criar menus no console:
  - Imóveis
  - Clientes
  - Corretores
  - Visitas
  - Propostas
  - Relatórios
- Criar submenus com:
  - Inserir
  - Listar
  - Buscar
  - Atualizar
  - Deletar
- Usar a classe `SistemaImobiliarioService` para:
  - `agendarVisita()`
  - `registrarProposta()`
  - `aceitarProposta()`
- Implementar relatórios como:
  - Imóveis disponíveis
  - Visitas por corretor
  - Propostas pendentes
- Garantir:
  - Fluxo intuitivo
  - Mensagens claras
  - Tratamento de erros do usuário

---

# 📌 Observações Gerais

- O projeto deve compilar e rodar via terminal.
- Todos os métodos devem estar documentados.
- É obrigatório demonstrar uso de:
  - Herança
  - Polimorfismo
  - Encapsulamento
  - Interface
  - JDBC
  - ArrayList
  - Try/Catch
- O professor provavelmente pedirá apresentação — mantenham tudo organizado.

---

# ✅ Fim do Documento
