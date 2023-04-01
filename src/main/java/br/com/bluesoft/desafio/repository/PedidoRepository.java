package br.com.bluesoft.desafio.repository;

import br.com.bluesoft.desafio.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
