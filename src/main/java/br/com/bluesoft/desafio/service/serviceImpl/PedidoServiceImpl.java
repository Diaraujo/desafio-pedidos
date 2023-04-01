package br.com.bluesoft.desafio.service.serviceImpl;

import br.com.bluesoft.desafio.model.*;
import br.com.bluesoft.desafio.model.dto.*;
import br.com.bluesoft.desafio.repository.PedidoRepository;
import br.com.bluesoft.desafio.service.FornecedorService;
import br.com.bluesoft.desafio.service.PedidoService;
import br.com.bluesoft.desafio.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;

    private final FornecedorService fornecedorService;

    private final ProdutoService produtoService;

    @Override
    public Iterable<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    @Override
    public Iterable<PedidoDto> novoPedido(ListaPedidoRequestDto listaPedidoRequestDto) throws Exception {

        List<Preco> precoList  = gerarItemPedidoList(listaPedidoRequestDto.getData());

        List<List<Preco>> grupoPrecoListByFornecedor = new ArrayList<>(precoList.stream().collect(Collectors.groupingBy(preco -> preco.getFornecedor())).values());

        List<Pedido> pedidoList = gerarPedidoList(grupoPrecoListByFornecedor);

        return salvarPedidos(pedidoList);
    }

    private List<PedidoDto> salvarPedidos(List<Pedido> pedidoList){

        List<PedidoDto> pedidoDtoList = new ArrayList<>();

        for(Pedido pedido : pedidoList) {
            FornecedorDto fornecedorDto = new FornecedorDto();
            fornecedorDto.setNome(pedido.getPrecoList().get(0).getFornecedor().getNome());
            List<ItemPedidoDto> itemPedidoDtoList = new ArrayList<>();

            for(Preco preco : pedido.getPrecoList()){
                ItemPedidoDto itemPedidoDto = new ItemPedidoDto();
                ProdutoDto produtoDto = new ProdutoDto();
                produtoDto.setNome(preco.getProduto().getNome());
                itemPedidoDto.setProduto(produtoDto);
                itemPedidoDto.setPreco(preco.getPreco());
                itemPedidoDto.setQuantidade(5);
                itemPedidoDto.setTotal(5*preco.getPreco());

                itemPedidoDtoList.add(itemPedidoDto);
            }

            PedidoDto pedidoDto = new PedidoDto();

            pedidoDto.setId(pedido.getId());
            pedidoDto.setFornecedor(fornecedorDto);
            pedidoDto.setItens(itemPedidoDtoList);

            pedidoDtoList.add(pedidoDto);
        }


        return pedidoDtoList;
    }

    private List<Pedido> gerarPedidoList(List<List<Preco>> grupoPrecoListByFornecedor) {
        List<Pedido> pedidoList = new ArrayList<>();

        for(List<Preco> precoListByFornecedor : grupoPrecoListByFornecedor){
            Pedido pedido = new Pedido();
            pedido.setData_pedido(new Date());
            pedido.setPrecoList(precoListByFornecedor);
            pedidoList.add(pedido);
        }

        return pedidoList;
    }

    private List<Pedido> gerarPedidoList(List<PedidoRequestDto> pedidoRequestDtoList) throws Exception {
        List<Pedido> pedidoList = new ArrayList<>();

        for(PedidoRequestDto pedidoRequestDto : pedidoRequestDtoList){
            List<Fornecedor> fornecedorList = fornecedorService.findAllExtFornecedoresByProdutoGtin(pedidoRequestDto.getGtin());
            Preco melhorPreco = findMelhorPreco(fornecedorList, pedidoRequestDto);

            Pedido pedido = ??
            List<ItemPedido> itemPedidoList = new ArrayList<>();
            if (??){
                pedido = new Pedido();
                pedido.setFornecedor(melhorPreco.getFornecedor());
            } else {
                ??
                itemPedidoList = ??
            }

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setProduto(melhorPreco.getProduto());
            itemPedido.setPreco(melhorPreco.getPreco());
            itemPedido.setQuantidade(pedidoRequestDto.getQuantidade());
            itemPedido.setTotal(melhorPreco.getPreco() * pedidoRequestDto.getQuantidade());
            itemPedidoList.add(itemPedido);
        }

        return pedidoList;
    }

    private Preco findMelhorPreco(List<Fornecedor> fornecedorList, PedidoRequestDto pedidoRequestDto) throws Exception {

        Preco melhorPreco = new Preco();
        melhorPreco.setPreco(Double.MAX_VALUE);
        Produto produto = produtoService.findProdutoByGtin(pedidoRequestDto.getGtin());

        for(Fornecedor fornecedor : fornecedorList){
            Preco precoAtual = fornecedor.getPrecos().stream()
                    .filter(preco -> preco.getQuantidade_minima() <= pedidoRequestDto.getQuantidade())
                    .sorted(Comparator.comparing(Preco::getPreco))
                    .findFirst()
                    .orElse(null);

            if(!isNull(precoAtual) &&
                    precoAtual.getPreco() < melhorPreco.getPreco()){
                precoAtual.setProduto(produto);
                precoAtual.setFornecedor(fornecedor);
                melhorPreco = precoAtual;
            }
        }

        if (melhorPreco.getPreco() == Double.MAX_VALUE) {
            throw new Exception("Nenhum fornecedor encontrado para a quantidade solicitada do produto " + produto.getNome());
        }

        return melhorPreco;
    }
}
