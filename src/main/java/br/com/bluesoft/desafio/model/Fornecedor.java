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

    @Id
    private String cnpj;

    private String nome;

    @OneToMany(mappedBy = "fornecedor")
    private List<Preco> precos;

}
