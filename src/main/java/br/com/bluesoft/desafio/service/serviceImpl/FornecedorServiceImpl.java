package br.com.bluesoft.desafio.service.serviceImpl;

import br.com.bluesoft.desafio.model.Fornecedor;
import br.com.bluesoft.desafio.service.FornecedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FornecedorServiceImpl implements FornecedorService {

    @Value("${ext.url.fornecedor}")
    private String extUrl;

    private final RestTemplate restTemplate;

    @Override
    public Iterable<Fornecedor> findAll() {
        return null;
    }

    public List<Fornecedor> findAllExtFornecedoresByProdutoGtin(String gtin) {

        List<Fornecedor> fornecedorList = Arrays.asList(restTemplate.getForObject(extUrl.concat(gtin), Fornecedor[].class));

        return fornecedorList;
    }


}
