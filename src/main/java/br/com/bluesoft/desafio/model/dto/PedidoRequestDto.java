package br.com.bluesoft.desafio.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PedidoRequestDto {
    private String gtin;
    private int quantidade;
}
