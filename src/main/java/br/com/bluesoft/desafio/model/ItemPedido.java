package br.com.bluesoft.desafio.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@IdClass(ItemPedidoId.class)
public class ItemPedido {

    @Id
    @ManyToOne
    private Produto produto;

    @Id
    @ManyToOne
    private Pedido pedido;

    private int quantidade;

    private Double preco;

    private Double total;

}
