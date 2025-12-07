package models;

import java.util.Objects;

public class StatusImovel {
    private int id; // PADRONIZADO
    private String descricao;

    public StatusImovel(int id, String descricao) { // PADRONIZADO
        this.id = id;
        this.descricao = descricao;
    }

    public StatusImovel(String descricao) {
        this.descricao = descricao;
    }

    public int getId() { // PADRONIZADO
        return id;
    }

    public void setId(int id) { // PADRONIZADO
        this.id = id;
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
                "id=" + id + // PADRONIZADO
                ", descricao='" + descricao + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusImovel that = (StatusImovel) o;
        return id == that.id; // PADRONIZADO
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // PADRONIZADO
    }
}
