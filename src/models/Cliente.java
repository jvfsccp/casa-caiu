package models;

public class Cliente extends Pessoa {
    private String cpf;

    // Construtor completo
    public Cliente(int id, String nome, String telefone, String email, String cpf) {
        super(id, nome, telefone, email);
        this.cpf = cpf;
    }

    // Construtor sem ID (para novos registros)
    public Cliente(String nome, String telefone, String email, String cpf) {
        super(nome, telefone, email);
        this.cpf = cpf;
    }

    // Getter e Setter
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    // Método toString()
    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + getId() +
                ", nome='" + getNome() + '\'' +
                ", telefone='" + getTelefone() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}
