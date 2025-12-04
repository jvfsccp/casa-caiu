package models;

import java.util.Objects;

public class Corretor {
    private int idCorretor;
    private String cpf;
    private String nome;
    private String creci;
    private String telefone;
    private String email;

    public Corretor(int idCorretor, String cpf, String nome, String creci, String telefone, String email) {
        this.idCorretor = idCorretor;
        this.cpf = cpf;
        this.nome = nome;
        this.creci = creci;
        this.telefone = telefone;
        this.email = email;
    }

    public Corretor(String cpf, String nome, String creci, String telefone, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.creci = creci;
        this.telefone = telefone;
        this.email = email;
    }

    public int getIdCorretor() {
        return idCorretor;
    }

    public void setIdCorretor(int idCorretor) {
        this.idCorretor = idCorretor;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCreci() {
        return creci;
    }

    public void setCreci(String creci) {
        this.creci = creci;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Corretor{" +
                "idCorretor=" + idCorretor +
                ", cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", creci='" + creci + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Corretor corretor = (Corretor) o;
        return idCorretor == corretor.idCorretor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCorretor);
    }
}
