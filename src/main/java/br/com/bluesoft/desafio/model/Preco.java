package br.com.bluesoft.desafio.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Preco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private Double preco;

    private int quantidade_minima;

    @ManyToOne(fetch = FetchType.LAZY)
    private Fornecedor fornecedor;

    @ManyToOne
    private Produto produto;

}
