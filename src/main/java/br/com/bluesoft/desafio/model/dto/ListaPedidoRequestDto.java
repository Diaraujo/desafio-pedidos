package br.com.bluesoft.desafio.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ListaPedidoRequestDto {
    List<PedidoRequestDto> data;
}
