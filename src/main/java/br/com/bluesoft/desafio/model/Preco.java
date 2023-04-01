package br.com.bluesoft.desafio.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Setter
@Getter
public class Preco {

    @Id
    private String id;

    private Double preco;

    private int quantidade_minima;

    @ManyToOne
    private Fornecedor fornecedor;

    @ManyToOne
    private Produto produto;

}
