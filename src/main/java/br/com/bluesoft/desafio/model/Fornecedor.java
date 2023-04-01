package br.com.bluesoft.desafio.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Setter
@Getter
public class Fornecedor {

    private String nome;
    @Id
    private String cnpj;

    @ManyToOne
    private Preco preco;

}
