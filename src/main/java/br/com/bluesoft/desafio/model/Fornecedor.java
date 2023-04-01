package br.com.bluesoft.desafio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


@Entity
@Setter
@Getter
public class Fornecedor {

    private String nome;
    @Id
    private String cnpj;

    @OneToMany
    private List<Preco> precos;

}
