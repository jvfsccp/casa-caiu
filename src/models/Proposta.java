package models;

import java.time.LocalDate;
import java.util.Objects;

public class Proposta {
    private int id;
    private int imovelId;
    private int clienteId;
    private LocalDate dataProposta;

    public Proposta(int id, int imovelId, int clienteId, LocalDate dataProposta) {
        this.id = id;
        this.imovelId = imovelId;
        this.clienteId = clienteId;
        this.dataProposta = dataProposta;
    }

    public Proposta(int imovelId, int clienteId, LocalDate dataProposta) {
        this.imovelId = imovelId;
        this.clienteId = clienteId;
        this.dataProposta = dataProposta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImovelId() {
        return imovelId;
    }

    public void setImovelId(int imovelId) {
        this.imovelId = imovelId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public LocalDate getDataProposta() {
        return dataProposta;
    }

    public void setDataProposta(LocalDate dataProposta) {
        this.dataProposta = dataProposta;
    }

    @Override
    public String toString() {
        return "Proposta{" +
                "id=" + id +
                ", imovelId=" + imovelId +
                ", clienteId=" + clienteId +
                ", dataProposta=" + dataProposta +
                '}';
    }

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
