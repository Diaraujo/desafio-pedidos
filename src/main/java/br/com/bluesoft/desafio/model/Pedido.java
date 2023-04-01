package br.com.bluesoft.desafio.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
public class Pedido {

    @Id
    private Long id;

    @ManyToOne
    private Fornecedor fornecedor;

    private Date data_pedido;
}
