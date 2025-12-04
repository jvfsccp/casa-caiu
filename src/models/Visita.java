package models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Visita {
    private int id;
    private Imovel imovel;
    private Cliente cliente;
    private Corretor corretor;
    private LocalDateTime dataHora;

    // Construtor completo
    public Visita(int id, Imovel imovel, Cliente cliente, Corretor corretor, LocalDateTime dataHora) {
        this.id = id;
        this.imovel = imovel;
        this.cliente = cliente;
        this.corretor = corretor;
        this.dataHora = dataHora;
    }

    // Construtor sem ID (para novos registros)
    public Visita(Imovel imovel, Cliente cliente, Corretor corretor, LocalDateTime dataHora) {
        this.imovel = imovel;
        this.cliente = cliente;
        this.corretor = corretor;
        this.dataHora = dataHora;
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

    public Corretor getCorretor() {
        return corretor;
    }

    public void setCorretor(Corretor corretor) {
        this.corretor = corretor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    // Método toString()
    @Override
    public String toString() {
        return "Visita{" +
                "id=" + id +
                ", imovel=" + imovel.getEndereco() +
                ", cliente=" + cliente.getNome() +
                ", corretor=" + corretor.getNome() +
                ", dataHora=" + dataHora +
                '}';
    }

    // Métodos equals e hashCode (boa prática)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visita visita = (Visita) o;
        return id == visita.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
