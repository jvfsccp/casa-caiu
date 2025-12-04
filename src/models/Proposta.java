package models;

import java.time.LocalDate;
import java.util.Objects;

public class Proposta {
    private int idProposta;
    private int idImovel;
    private int idCliente;
    private LocalDate dataProposta;

    public Proposta(int idProposta, int idImovel, int idCliente, LocalDate dataProposta) {
        this.idProposta = idProposta;
        this.idImovel = idImovel;
        this.idCliente = idCliente;
        this.dataProposta = dataProposta;
    }

    public Proposta(int idImovel, int idCliente, LocalDate dataProposta) {
        this.idImovel = idImovel;
        this.idCliente = idCliente;
        this.dataProposta = dataProposta;
    }

    public int getIdProposta() {
        return idProposta;
    }

    public void setIdProposta(int idProposta) {
        this.idProposta = idProposta;
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

    public LocalDate getDataProposta() {
        return dataProposta;
    }

    public void setDataProposta(LocalDate dataProposta) {
        this.dataProposta = dataProposta;
    }

    @Override
    public String toString() {
        return "Proposta{" +
                "idProposta=" + idProposta +
                ", idImovel=" + idImovel +
                ", idCliente=" + idCliente +
                ", dataProposta=" + dataProposta +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proposta proposta = (Proposta) o;
        return idProposta == proposta.idProposta;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProposta);
    }
}
