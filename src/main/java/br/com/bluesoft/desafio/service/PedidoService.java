package br.com.bluesoft.desafio.service;

import org.springframework.data.repository.CrudRepository;

import br.com.bluesoft.desafio.model.Pedido;

public interface PedidoService  {

    public Iterable<Pedido> findAll();

}
