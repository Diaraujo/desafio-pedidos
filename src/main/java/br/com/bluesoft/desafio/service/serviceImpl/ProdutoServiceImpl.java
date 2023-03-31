package br.com.bluesoft.desafio.service.serviceImpl;

import br.com.bluesoft.desafio.model.Produto;
import br.com.bluesoft.desafio.service.ProdutoService;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServiceImpl implements ProdutoService {


    @Override
    public Iterable<Produto> findAll() {
        return null;
    }
}
