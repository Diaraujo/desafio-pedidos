package br.com.bluesoft.desafio.api;

import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.model.dto.ListaPedidoRequestDto;
import br.com.bluesoft.desafio.model.dto.PedidoDto;
import br.com.bluesoft.desafio.model.dto.PedidoRequestDto;
import br.com.bluesoft.desafio.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @GetMapping
    public Iterable<Pedido> findAll(){
        return pedidoService.findAll();
    }

    @PostMapping("/novo-pedido")
    public Iterable<PedidoDto> novoPedido(@RequestBody ListaPedidoRequestDto listaPedidoRequestDto) throws Exception {
        return pedidoService.novoPedido(listaPedidoRequestDto);
    }

}
