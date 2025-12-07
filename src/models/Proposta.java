package models;

import java.time.LocalDate;
import java.util.Objects;

public class Proposta {
    private int id; // PADRONIZADO
    private int imovelId; // PADRONIZADO
    private int clienteId; // PADRONIZADO
    private LocalDate dataProposta;

    public Proposta(int id, int imovelId, int clienteId, LocalDate dataProposta) { // PADRONIZADO
        this.id = id;
        this.imovelId = imovelId;
        this.clienteId = clienteId;
        this.dataProposta = dataProposta;
    }

    public Proposta(int imovelId, int clienteId, LocalDate dataProposta) { // PADRONIZADO
        this.imovelId = imovelId;
        this.clienteId = clienteId;
        this.dataProposta = dataProposta;
    }

    public int getId() { // PADRONIZADO
        return id;
    }

    public void setId(int id) { // PADRONIZADO
        this.id = id;
    }

    public int getImovelId() { // PADRONIZADO
        return imovelId;
    }

    public void setImovelId(int imovelId) { // PADRONIZADO
        this.imovelId = imovelId;
    }

    public int getClienteId() { // PADRONIZADO
        return clienteId;
    }

    public void setClienteId(int clienteId) { // PADRONIZADO
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
                "id=" + id + // PADRONIZADO
                ", imovelId=" + imovelId + // PADRONIZADO
                ", clienteId=" + clienteId + // PADRONIZADO
                ", dataProposta=" + dataProposta +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proposta proposta = (Proposta) o;
        return id == proposta.id; // PADRONIZADO
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // PADRONIZADO
    }
}
