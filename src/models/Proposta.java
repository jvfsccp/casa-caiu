package models;

import java.time.LocalDate;
import java.util.Objects;

public class Proposta implements Calculavel {
    private int id;
    private Imovel imovel;
    private Cliente cliente;
    private double valorProposto;
    private LocalDate dataProposta;
    private String status; // Ex: Pendente, Aceita, Recusada

    // Construtor completo
    public Proposta(int id, Imovel imovel, Cliente cliente, double valorProposto, LocalDate dataProposta, String status) {
        this.id = id;
        this.imovel = imovel;
        this.cliente = cliente;
        this.valorProposto = valorProposto;
        this.dataProposta = dataProposta;
        this.status = status;
    }

    // Construtor sem ID (para novos registros)
    public Proposta(Imovel imovel, Cliente cliente, double valorProposto, LocalDate dataProposta, String status) {
        this.imovel = imovel;
        this.cliente = cliente;
        this.valorProposto = valorProposto;
        this.dataProposta = dataProposta;
        this.status = status;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getValorProposto() {
        return valorProposto;
    }

    public void setValorProposto(double valorProposto) {
        this.valorProposto = valorProposto;
    }

    public LocalDate getDataProposta() {
        return dataProposta;
    }

    public void setDataProposta(LocalDate dataProposta) {
        this.dataProposta = dataProposta;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Implementação do método calcularValor() da interface Calculavel
    @Override
    public double calcularValor() {
        // O valor calculado da proposta é o valor proposto, mas podemos adicionar uma lógica de bônus/desconto
        // Exemplo: 5% de desconto se o status for "Aceita"
        if ("Aceita".equalsIgnoreCase(status)) {
            return valorProposto * 0.95;
        }
        return valorProposto;
    }

    // Método toString()
    @Override
    public String toString() {
        return "Proposta{" +
                "id=" + id +
                ", imovel=" + imovel.getEndereco() +
                ", cliente=" + cliente.getNome() +
                ", valorProposto=" + valorProposto +
                ", dataProposta=" + dataProposta +
                ", status='" + status + '\'' +
                ", valorCalculado=" + calcularValor() +
                '}';
    }

    // Métodos equals e hashCode (boa prática)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proposta proposta = (Proposta) o;
        return id == proposta.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
