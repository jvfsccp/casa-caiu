package models;

import java.util.Objects;

public class Imovel {
    private int idImovel;
    private int idTipo;
    private String endereco;
    private int idStatus;

    public Imovel(int idImovel, int idTipo, String endereco, int idStatus) {
        this.idImovel = idImovel;
        this.idTipo = idTipo;
        this.endereco = endereco;
        this.idStatus = idStatus;
    }

    public Imovel(int idTipo, String endereco, int idStatus) {
        this.idTipo = idTipo;
        this.endereco = endereco;
        this.idStatus = idStatus;
    }

    public int getIdImovel() {
        return idImovel;
    }

    public void setIdImovel(int idImovel) {
        this.idImovel = idImovel;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    @Override
    public String toString() {
        return "Imovel{" +
                "idImovel=" + idImovel +
                ", idTipo=" + idTipo +
                ", endereco='" + endereco + '\'' +
                ", idStatus=" + idStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Imovel imovel = (Imovel) o;
        return idImovel == imovel.idImovel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idImovel);
    }
}
