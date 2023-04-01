package br.com.bluesoft.desafio.service;

import br.com.bluesoft.desafio.model.Produto;

public interface ProdutoService {

    Iterable<Produto> findAll();

    Produto findProdutoByGtin(String gtin);


}
