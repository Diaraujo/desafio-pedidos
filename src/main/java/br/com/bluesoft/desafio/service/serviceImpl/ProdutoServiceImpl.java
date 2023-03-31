package br.com.bluesoft.desafio.service.serviceImpl;

import br.com.bluesoft.desafio.model.Produto;
import br.com.bluesoft.desafio.repository.ProdutoRepository;
import br.com.bluesoft.desafio.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public Iterable<Produto> findAll() {
        return produtoRepository.findAll();
    }
}
