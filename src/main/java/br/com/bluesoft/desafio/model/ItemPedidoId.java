package br.com.bluesoft.desafio.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ItemPedidoId implements Serializable {
    private String gtin;
    private Long id;
}
