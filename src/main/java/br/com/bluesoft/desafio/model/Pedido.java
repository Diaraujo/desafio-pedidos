package br.com.bluesoft.desafio.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Setter
@Getter
public class Pedido {

    @Id
    private String id;
    @ManyToOne
    private Fornecedor fornecedor;

    private Date data_pedido;


}
