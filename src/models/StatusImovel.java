package models;

import java.util.Objects;

public class StatusImovel {
    private int idStatus;
    private String descricao;

    public StatusImovel(int idStatus, String descricao) {
        this.idStatus = idStatus;
        this.descricao = descricao;
    }

    public StatusImovel(String descricao) {
        this.descricao = descricao;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "StatusImovel{" +
                "idStatus=" + idStatus +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusImovel that = (StatusImovel) o;
        return idStatus == that.idStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStatus);
    }
}
