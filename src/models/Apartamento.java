package models;

public class Apartamento extends Imovel {
    private int andar;
    private double valorCondominio;

    // Construtor completo
    public Apartamento(int id, String endereco, double area, double valorBase, String tipoTransacao, int andar, double valorCondominio) {
        super(id, endereco, area, valorBase, tipoTransacao);
        this.andar = andar;
        this.valorCondominio = valorCondominio;
    }

    // Construtor sem ID (para novos registros)
    public Apartamento(String endereco, double area, double valorBase, String tipoTransacao, int andar, double valorCondominio) {
        super(endereco, area, valorBase, tipoTransacao);
        this.andar = andar;
        this.valorCondominio = valorCondominio;
    }

    // Getters e Setters
    public int getAndar() {
        return andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }

    public double getValorCondominio() {
        return valorCondominio;
    }

    public void setValorCondominio(double valorCondominio) {
        this.valorCondominio = valorCondominio;
    }

    // Implementação do método calcularValor() da interface Calculavel
    @Override
    public double calcularValor() {
        // Exemplo de cálculo: valor base + (andar * 1000) + (valor do condomínio * 12)
        return getValorBase() + (andar * 1000.0) + (valorCondominio * 12.0);
    }

    // Método toString()
    @Override
    public String toString() {
        return "Apartamento{" +
                "id=" + getId() +
                ", endereco='" + getEndereco() + '\'' +
                ", area=" + getArea() +
                ", valorBase=" + getValorBase() +
                ", tipoTransacao='" + getTipoTransacao() + '\'' +
                ", andar=" + andar +
                ", valorCondominio=" + valorCondominio +
                ", valorCalculado=" + calcularValor() +
                '}';
    }
}
