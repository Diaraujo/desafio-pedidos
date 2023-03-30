package br.com.bluesoft.desafio.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Fornecedor {

    @Id
    private String id;

    private String nome;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
