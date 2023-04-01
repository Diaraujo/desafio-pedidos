package br.com.bluesoft.desafio.service;

import org.springframework.data.repository.CrudRepository;

import br.com.bluesoft.desafio.model.Fornecedor;

import java.util.List;

public interface FornecedorService  {

    Iterable<Fornecedor> findAll();

    List<Fornecedor> findAllExtFornecedoresByProdutoGtin(String gtin);

}
