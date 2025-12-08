package models;

import java.time.LocalDate;
import java.util.Objects;

public class Visita {
    private int id;
    private int imovelId;
    private int clienteId;
    private int corretorId;
    private LocalDate dataVisita;

    public Visita(int id, int imovelId, int clienteId, int corretorId, LocalDate dataVisita) {
        this.id = id;
        this.imovelId = imovelId;
        this.clienteId = clienteId;
        this.corretorId = corretorId;
        this.dataVisita = dataVisita;
    }

    public Visita(int imovelId, int clienteId, int corretorId, LocalDate dataVisita) {
        this.imovelId = imovelId;
        this.clienteId = clienteId;
        this.corretorId = corretorId;
        this.dataVisita = dataVisita;
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

    public int getCorretorId() {
        return corretorId;
    }

    public void setCorretorId(int corretorId) {
        this.corretorId = corretorId;
    }

    public LocalDate getDataVisita() {
        return dataVisita;
    }

    public void setDataVisita(LocalDate dataVisita) {
        this.dataVisita = dataVisita;
    }

    @Override
    public String toString() {
        return "Visita{" +
                "id=" + id +
                ", imovelId=" + imovelId +
                ", clienteId=" + clienteId +
                ", corretorId=" + corretorId +
                ", dataVisita=" + dataVisita +
                '}';
    }

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
