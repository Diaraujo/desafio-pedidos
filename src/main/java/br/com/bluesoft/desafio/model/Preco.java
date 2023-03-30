package br.com.bluesoft.desafio.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Preco {

    @Id
    private String id;

    private Double preco;

    private int quantidade_min;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public int getQuantidade_min() {
        return quantidade_min;
    }

    public void setQuantidade_min(int quantidade_min) {
        this.quantidade_min = quantidade_min;
    }
}
