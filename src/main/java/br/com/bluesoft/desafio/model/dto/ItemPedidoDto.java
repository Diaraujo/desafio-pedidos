package br.com.bluesoft.desafio.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemPedidoDto {
    private ProdutoDto produto;
    private int quantidade;
    private Double preco;
    private Double total;
}
