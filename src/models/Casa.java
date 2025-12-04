package models;

public class Casa extends Imovel {
    private int numeroQuartos;
    private int numeroVagasGaragem;

    // Construtor completo
    public Casa(int id, String endereco, double area, double valorBase, String tipoTransacao, int numeroQuartos, int numeroVagasGaragem) {
        super(id, endereco, area, valorBase, tipoTransacao);
        this.numeroQuartos = numeroQuartos;
        this.numeroVagasGaragem = numeroVagasGaragem;
    }

    // Construtor sem ID (para novos registros)
    public Casa(String endereco, double area, double valorBase, String tipoTransacao, int numeroQuartos, int numeroVagasGaragem) {
        super(endereco, area, valorBase, tipoTransacao);
        this.numeroQuartos = numeroQuartos;
        this.numeroVagasGaragem = numeroVagasGaragem;
    }

    // Getters e Setters
    public int getNumeroQuartos() {
        return numeroQuartos;
    }

    public void setNumeroQuartos(int numeroQuartos) {
        this.numeroQuartos = numeroQuartos;
    }

    public int getNumeroVagasGaragem() {
        return numeroVagasGaragem;
    }

    public void setNumeroVagasGaragem(int numeroVagasGaragem) {
        this.numeroVagasGaragem = numeroVagasGaragem;
    }

    // Implementação do método calcularValor() da interface Calculavel
    @Override
    public double calcularValor() {
        // Exemplo de cálculo: valor base + (número de quartos * 5000) + (número de vagas * 2000)
        return getValorBase() + (numeroQuartos * 5000.0) + (numeroVagasGaragem * 2000.0);
    }

    // Método toString()
    @Override
    public String toString() {
        return "Casa{" +
                "id=" + getId() +
                ", endereco='" + getEndereco() + '\'' +
                ", area=" + getArea() +
                ", valorBase=" + getValorBase() +
                ", tipoTransacao='" + getTipoTransacao() + '\'' +
                ", numeroQuartos=" + numeroQuartos +
                ", numeroVagasGaragem=" + numeroVagasGaragem +
                ", valorCalculado=" + calcularValor() +
                '}';
    }
}
