package models;

import java.time.LocalDate;
import java.util.Objects;

public class Visita {
    private int idVisita;
    private int idImovel;
    private int idCliente;
    private int idCorretor;
    private LocalDate dataVisita;

    public Visita(int idVisita, int idImovel, int idCliente, int idCorretor, LocalDate dataVisita) {
        this.idVisita = idVisita;
        this.idImovel = idImovel;
        this.idCliente = idCliente;
        this.idCorretor = idCorretor;
        this.dataVisita = dataVisita;
    }

    public Visita(int idImovel, int idCliente, int idCorretor, LocalDate dataVisita) {
        this.idImovel = idImovel;
        this.idCliente = idCliente;
        this.idCorretor = idCorretor;
        this.dataVisita = dataVisita;
    }

    public int getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(int idVisita) {
        this.idVisita = idVisita;
    }

    public int getIdImovel() {
        return idImovel;
    }

    public void setIdImovel(int idImovel) {
        this.idImovel = idImovel;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdCorretor() {
        return idCorretor;
    }

    public void setIdCorretor(int idCorretor) {
        this.idCorretor = idCorretor;
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
                "idVisita=" + idVisita +
                ", idImovel=" + idImovel +
                ", idCliente=" + idCliente +
                ", idCorretor=" + idCorretor +
                ", dataVisita=" + dataVisita +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visita visita = (Visita) o;
        return idVisita == visita.idVisita;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVisita);
    }
}
