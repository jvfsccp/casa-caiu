package models;

public class SalaComercial extends Imovel {
    private boolean temEstacionamento;

    // Construtor completo
    public SalaComercial(int id, String endereco, double area, double valorBase, String tipoTransacao, boolean temEstacionamento) {
        super(id, endereco, area, valorBase, tipoTransacao);
        this.temEstacionamento = temEstacionamento;
    }

    // Construtor sem ID (para novos registros)
    public SalaComercial(String endereco, double area, double valorBase, String tipoTransacao, boolean temEstacionamento) {
        super(endereco, area, valorBase, tipoTransacao);
        this.temEstacionamento = temEstacionamento;
    }

    // Getter e Setter
    public boolean isTemEstacionamento() {
        return temEstacionamento;
    }

    public void setTemEstacionamento(boolean temEstacionamento) {
        this.temEstacionamento = temEstacionamento;
    }

    // Implementação do método calcularValor() da interface Calculavel
    @Override
    public double calcularValor() {
        // Exemplo de cálculo: valor base + (área * 100) + bônus por estacionamento (5000)
        double valor = getValorBase() + (getArea() * 100.0);
        if (temEstacionamento) {
            valor += 5000.0;
        }
        return valor;
    }

    // Método toString()
    @Override
    public String toString() {
        return "SalaComercial{" +
                "id=" + getId() +
                ", endereco='" + getEndereco() + '\'' +
                ", area=" + getArea() +
                ", valorBase=" + getValorBase() +
                ", tipoTransacao='" + getTipoTransacao() + '\'' +
                ", temEstacionamento=" + temEstacionamento +
                ", valorCalculado=" + calcularValor() +
                '}';
    }
}
