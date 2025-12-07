package models;

import java.time.LocalDate;
import java.util.Objects;

public class Visita {
    private int id; // PADRONIZADO
    private int imovelId; // PADRONIZADO
    private int clienteId; // PADRONIZADO
    private int corretorId; // PADRONIZADO
    private LocalDate dataVisita;

    public Visita(int id, int imovelId, int clienteId, int corretorId, LocalDate dataVisita) { // PADRONIZADO
        this.id = id;
        this.imovelId = imovelId;
        this.clienteId = clienteId;
        this.corretorId = corretorId;
        this.dataVisita = dataVisita;
    }

    public Visita(int imovelId, int clienteId, int corretorId, LocalDate dataVisita) { // PADRONIZADO
        this.imovelId = imovelId;
        this.clienteId = clienteId;
        this.corretorId = corretorId;
        this.dataVisita = dataVisita;
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

    public int getCorretorId() { // PADRONIZADO
        return corretorId;
    }

    public void setCorretorId(int corretorId) { // PADRONIZADO
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
                "id=" + id + // PADRONIZADO
                ", imovelId=" + imovelId + // PADRONIZADO
                ", clienteId=" + clienteId + // PADRONIZADO
                ", corretorId=" + corretorId + // PADRONIZADO
                ", dataVisita=" + dataVisita +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visita visita = (Visita) o;
        return id == visita.id; // PADRONIZADO
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // PADRONIZADO
    }
}
