package br.com.bluesoft.desafio.service.serviceImpl;

import br.com.bluesoft.desafio.model.*;
import br.com.bluesoft.desafio.model.dto.*;
import br.com.bluesoft.desafio.repository.FornecedorRepository;
import br.com.bluesoft.desafio.repository.ItemPedidoRepository;
import br.com.bluesoft.desafio.repository.PedidoRepository;
import br.com.bluesoft.desafio.repository.PrecoRepository;
import br.com.bluesoft.desafio.service.FornecedorService;
import br.com.bluesoft.desafio.service.PedidoService;
import br.com.bluesoft.desafio.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
@Transactional
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;

    private final FornecedorService fornecedorService;

    private final ProdutoService produtoService;

    private final FornecedorRepository fornecedorRepository;

    private final PrecoRepository precoRepository;

    private final ItemPedidoRepository itemPedidoRepository;

    @Override
    public Iterable<PedidoDto> findAll() {
        return gerarResponseDto(pedidoRepository.findAll());
    }
    private List<PedidoDto> gerarResponseDto(List<Pedido> pedidoList){

        List<PedidoDto> pedidoDtoList = new ArrayList<>();

        for(Pedido pedido : pedidoList) {
            FornecedorDto fornecedorDto = new FornecedorDto();
            fornecedorDto.setNome(pedido.getFornecedor().getNome());
            List<ItemPedidoDto> itemPedidoDtoList = new ArrayList<>();

            for(ItemPedido itemPedido : pedido.getItemPedidoList()){
                ItemPedidoDto itemPedidoDto = new ItemPedidoDto();
                ProdutoDto produtoDto = new ProdutoDto();
                produtoDto.setNome(itemPedido.getProduto().getNome());
                itemPedidoDto.setProduto(produtoDto);
                itemPedidoDto.setPreco(itemPedido.getPreco());
                itemPedidoDto.setQuantidade(itemPedido.getQuantidade());
                itemPedidoDto.setTotal(itemPedido.getTotal());

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


    @Override
    public Iterable<PedidoDto> novoPedido(ListaPedidoRequestDto listaPedidoRequestDto) throws Exception {

        List<Pedido> pedidoList  = gerarPedidoList(listaPedidoRequestDto.getData());

        return gerarResponseDtoESalvarPedidos(pedidoList);
    }

    private void salvarPedido(Pedido pedido){
        fornecedorRepository.save(pedido.getFornecedor());
        precoRepository.saveAll(pedido.getFornecedor().getPrecos());
        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(pedido.getItemPedidoList());
    }

    private List<PedidoDto> gerarResponseDtoESalvarPedidos(List<Pedido> pedidoList){

        List<PedidoDto> pedidoDtoList = new ArrayList<>();

        for(Pedido pedido : pedidoList) {
            salvarPedido(pedido);
            FornecedorDto fornecedorDto = new FornecedorDto();
            fornecedorDto.setNome(pedido.getFornecedor().getNome());
            List<ItemPedidoDto> itemPedidoDtoList = new ArrayList<>();

            for(ItemPedido itemPedido : pedido.getItemPedidoList()){
                ItemPedidoDto itemPedidoDto = new ItemPedidoDto();
                ProdutoDto produtoDto = new ProdutoDto();
                produtoDto.setNome(itemPedido.getProduto().getNome());
                itemPedidoDto.setProduto(produtoDto);
                itemPedidoDto.setPreco(itemPedido.getPreco());
                itemPedidoDto.setQuantidade(itemPedido.getQuantidade());
                itemPedidoDto.setTotal(itemPedido.getTotal());

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

    private List<Pedido> gerarPedidoList(List<PedidoRequestDto> pedidoRequestDtoList) throws Exception {
        List<Pedido> pedidoList = new ArrayList<>();
        HashMap<Fornecedor, List<ItemPedido>> fornecedorItemPedidoMap = gerarItemPedidoMap(pedidoRequestDtoList);

        fornecedorItemPedidoMap.forEach(((fornecedor, itemPedidoList) -> {
            Pedido pedido = new Pedido();
            pedido.setData_pedido(new Date());
            pedido.setFornecedor(fornecedor);
            pedido.setItemPedidoList(itemPedidoList);
            itemPedidoList.forEach(itemPedido -> itemPedido.setPedido(pedido));
            pedidoList.add(pedido);
        }));

        return pedidoList;
    }

    private HashMap<Fornecedor, List<ItemPedido>> gerarItemPedidoMap(List<PedidoRequestDto> pedidoRequestDtoList) throws Exception {
        HashMap<Fornecedor, List<ItemPedido>> fornecedorItemPedidoMap = new HashMap<>();

        for(PedidoRequestDto pedidoRequestDto : pedidoRequestDtoList){
            List<Fornecedor> fornecedorList = fornecedorService.findAllExtFornecedoresByProdutoGtin(pedidoRequestDto.getGtin());
            Preco melhorPreco = findMelhorPreco(fornecedorList, pedidoRequestDto);

            List<ItemPedido> itemPedidoList = new ArrayList<>();

            if (fornecedorItemPedidoMap.containsKey(melhorPreco.getFornecedor())) {
                itemPedidoList = fornecedorItemPedidoMap.get(melhorPreco.getFornecedor());
            } else {
                fornecedorItemPedidoMap.put(melhorPreco.getFornecedor(), itemPedidoList);
            }

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setProduto(melhorPreco.getProduto());
            itemPedido.setPreco(melhorPreco.getPreco());
            itemPedido.setQuantidade(pedidoRequestDto.getQuantidade());
            itemPedido.setTotal(melhorPreco.getPreco() * pedidoRequestDto.getQuantidade());
            itemPedidoList.add(itemPedido);
        }

        return fornecedorItemPedidoMap;
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
