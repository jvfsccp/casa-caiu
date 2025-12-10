package models;

import java.time.LocalDate;
import java.util.Objects;

public class Interesse {
    private int id;
    private int clienteId;
    private int imovelId;
    private LocalDate dataInteresse;

    public Interesse(int id, int clienteId, int imovelId, LocalDate dataInteresse) {
        this.id = id;
        this.clienteId = clienteId;
        this.imovelId = imovelId;
        this.dataInteresse = dataInteresse;
    }

    public Interesse(int clienteId, int imovelId, LocalDate dataInteresse) {
        this.clienteId = clienteId;
        this.imovelId = imovelId;
        this.dataInteresse = dataInteresse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getImovelId() {
        return imovelId;
    }

    public void setImovelId(int imovelId) {
        this.imovelId = imovelId;
    }

    public LocalDate getDataInteresse() {
        return dataInteresse;
    }

    public void setDataInteresse(LocalDate dataInteresse) {
        this.dataInteresse = dataInteresse;
    }

    @Override
    public String toString() {
        return "Interesse{" +
                "id=" + id +
                ", clienteId=" + clienteId +
                ", imovelId=" + imovelId +
                ", dataInteresse=" + dataInteresse +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interesse interesse = (Interesse) o;
        return id == interesse.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
