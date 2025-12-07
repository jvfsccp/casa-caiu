package models;

import java.util.Objects;

public class Imovel {
    private int id; // PADRONIZADO
    private int tipoImovelId; // PADRONIZADO
    private String endereco;
    private int statusImovelId; // PADRONIZADO

    public Imovel(int id, int tipoImovelId, String endereco, int statusImovelId) { // PADRONIZADO
        this.id = id;
        this.tipoImovelId = tipoImovelId;
        this.endereco = endereco;
        this.statusImovelId = statusImovelId;
    }

    public Imovel(int tipoImovelId, String endereco, int statusImovelId) { // PADRONIZADO
        this.tipoImovelId = tipoImovelId;
        this.endereco = endereco;
        this.statusImovelId = statusImovelId;
    }

    public int getId() { // PADRONIZADO
        return id;
    }

    public void setId(int id) { // PADRONIZADO
        this.id = id;
    }

    public int getTipoImovelId() { // PADRONIZADO
        return tipoImovelId;
    }

    public void setTipoImovelId(int tipoImovelId) { // PADRONIZADO
        this.tipoImovelId = tipoImovelId;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getStatusImovelId() { // PADRONIZADO
        return statusImovelId;
    }

    public void setStatusImovelId(int statusImovelId) { // PADRONIZADO
        this.statusImovelId = statusImovelId;
    }

    @Override
    public String toString() {
        return "Imovel{" +
                "id=" + id + // PADRONIZADO
                ", tipoImovelId=" + tipoImovelId + // PADRONIZADO
                ", endereco='" + endereco + '\'' +
                ", statusImovelId=" + statusImovelId + // PADRONIZADO
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Imovel imovel = (Imovel) o;
        return id == imovel.id; // PADRONIZADO
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // PADRONIZADO
    }
}
