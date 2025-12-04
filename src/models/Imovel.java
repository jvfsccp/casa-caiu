package models;

import java.util.Objects;

public abstract class Imovel implements Calculavel {
    private int id;
    private String endereco;
    private double area;
    private double valorBase;
    private String tipoTransacao; // Venda ou Aluguel

    // Construtor completo
    public Imovel(int id, String endereco, double area, double valorBase, String tipoTransacao) {
        this.id = id;
        this.endereco = endereco;
        this.area = area;
        this.valorBase = valorBase;
        this.tipoTransacao = tipoTransacao;
    }

    // Construtor sem ID (para novos registros)
    public Imovel(String endereco, double area, double valorBase, String tipoTransacao) {
        this.endereco = endereco;
        this.area = area;
        this.valorBase = valorBase;
        this.tipoTransacao = tipoTransacao;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getValorBase() {
        return valorBase;
    }

    public void setValorBase(double valorBase) {
        this.valorBase = valorBase;
    }

    public String getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(String tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    // Método toString()
    @Override
    public String toString() {
        return "Imovel{" +
                "id=" + id +
                ", endereco='" + endereco + '\'' +
                ", area=" + area +
                ", valorBase=" + valorBase +
                ", tipoTransacao='" + tipoTransacao + '\'' +
                '}';
    }

    // Métodos equals e hashCode (boa prática)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Imovel imovel = (Imovel) o;
        return id == imovel.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // O método calcularValor() é abstrato na interface, mas será implementado nas subclasses
    // Para fins de polimorfismo, vamos garantir que ele esteja presente.
    // A implementação será específica para cada tipo de imóvel.
}
