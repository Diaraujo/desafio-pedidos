package br.com.bluesoft.desafio.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PedidoDto {
    private Long id;

    private FornecedorDto fornecedor;

    private List<ItemPedidoDto> itens;
}
