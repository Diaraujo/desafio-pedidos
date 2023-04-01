package br.com.bluesoft.desafio.service;

import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.model.dto.ListaPedidoRequestDto;
import br.com.bluesoft.desafio.model.dto.PedidoDto;


public interface PedidoService  {

    Iterable<Pedido> findAll();

    Iterable<PedidoDto> novoPedido(ListaPedidoRequestDto listaPedidoRequestDto) throws Exception;

}
