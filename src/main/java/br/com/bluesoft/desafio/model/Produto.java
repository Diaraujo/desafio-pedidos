package br.com.bluesoft.desafio.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class Produto {

    @Id
    private String gtin;

    private String nome;
}
