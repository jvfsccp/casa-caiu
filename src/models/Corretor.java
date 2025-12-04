package models;

public class Corretor extends Pessoa {
    private String creci;

    // Construtor completo
    public Corretor(int id, String nome, String telefone, String email, String creci) {
        super(id, nome, telefone, email);
        this.creci = creci;
    }

    // Construtor sem ID (para novos registros)
    public Corretor(String nome, String telefone, String email, String creci) {
        super(nome, telefone, email);
        this.creci = creci;
    }

    // Getter e Setter
    public String getCreci() {
        return creci;
    }

    public void setCreci(String creci) {
        this.creci = creci;
    }

    // Método toString()
    @Override
    public String toString() {
        return "Corretor{" +
                "id=" + getId() +
                ", nome='" + getNome() + '\'' +
                ", telefone='" + getTelefone() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", creci='" + creci + '\'' +
                '}';
    }
}
