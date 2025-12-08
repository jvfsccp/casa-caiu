package models;

import java.util.Objects;

public class Imovel {
    private int id;
    private int tipoImovelId;
    private String endereco;
    private int statusImovelId;

    public Imovel(int id, int tipoImovelId, String endereco, int statusImovelId) {
        this.id = id;
        this.tipoImovelId = tipoImovelId;
        this.endereco = endereco;
        this.statusImovelId = statusImovelId;
    }

    public Imovel(int tipoImovelId, String endereco, int statusImovelId) {
        this.tipoImovelId = tipoImovelId;
        this.endereco = endereco;
        this.statusImovelId = statusImovelId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipoImovelId() {
        return tipoImovelId;
    }

    public void setTipoImovelId(int tipoImovelId) {
        this.tipoImovelId = tipoImovelId;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getStatusImovelId() {
        return statusImovelId;
    }

    public void setStatusImovelId(int statusImovelId) {
        this.statusImovelId = statusImovelId;
    }

    @Override
    public String toString() {
        return "Imovel{" +
                "id=" + id +
                ", tipoImovelId=" + tipoImovelId +
                ", endereco='" + endereco + '\'' +
                ", statusImovelId=" + statusImovelId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Imovel imovel = (Imovel) o;
        return id == imovel.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
