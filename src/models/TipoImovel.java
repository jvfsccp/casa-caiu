package models;

import java.util.Objects;

public class TipoImovel {
    private int idTipo;
    private String descricao;

    public TipoImovel(int idTipo, String descricao) {
        this.idTipo = idTipo;
        this.descricao = descricao;
    }

    public TipoImovel(String descricao) {
        this.descricao = descricao;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "TipoImovel{" +
                "idTipo=" + idTipo +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoImovel that = (TipoImovel) o;
        return idTipo == that.idTipo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTipo);
    }
}
