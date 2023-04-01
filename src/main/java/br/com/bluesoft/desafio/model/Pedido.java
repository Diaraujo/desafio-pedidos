package br.com.bluesoft.desafio.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Fornecedor fornecedor;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itemPedidoList;

    private Date data_pedido;
}
