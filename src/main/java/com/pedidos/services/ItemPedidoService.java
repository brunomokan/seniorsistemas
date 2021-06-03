package com.pedidos.services;

import com.pedidos.dtos.ItemPedidoAtualizarDTO;
import com.pedidos.dtos.ItemPedidoNovoDTO;
import com.pedidos.entities.ItemPedido;
import com.pedidos.entities.Pedido;
import com.pedidos.entities.ProdutoServico;
import com.pedidos.exceptions.ProdutoServicoInativoException;
import com.pedidos.exceptions.RegistroNaoEncontradoException;
import com.pedidos.repositories.ItemPedidoRepository;
import com.pedidos.repositories.ProdutoServicoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ProdutoServicoRepository produtoServicoRepository;

    public ItemPedido buscarPorId(UUID id) {
        return itemPedidoRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException(id));
    }

    public Page<ItemPedido> listarPaginado(Optional<Integer> page, Optional<Integer> size) {
        return itemPedidoRepository.findAll(PageRequest.of(page.orElse(0), size.orElse(10)));
    }

    public ItemPedido salvar(ItemPedidoNovoDTO itemPedidoNovoDTO) {

        ProdutoServico produtoServico = produtoServicoRepository.findById(itemPedidoNovoDTO.getProdutoServicoId())
                .orElseThrow(() -> new RegistroNaoEncontradoException(itemPedidoNovoDTO.getProdutoServicoId()));

        if(!produtoServico.isAtivo()){
            throw new ProdutoServicoInativoException();
        }

        return itemPedidoRepository.save(ItemPedido.builder()
                .produtoServico(produtoServico)
                .pedido(Pedido.builder().id(itemPedidoNovoDTO.getPedidoId()).build())
                .quantidade(itemPedidoNovoDTO.getQuantidade())
                .valorUnitario(itemPedidoNovoDTO.getValorUnitario())
                .build());
    }

    public ItemPedido atualizar(UUID id, ItemPedidoAtualizarDTO itemPedidoDTO) {

        ItemPedido itemPedido = buscarPorId(id);

        itemPedido.setQuantidade(itemPedidoDTO.getQuantidade());
        itemPedido.setValorUnitario(itemPedidoDTO.getValorUnitario());

        return itemPedidoRepository.save(itemPedido);

    }

    public ItemPedido excluir(UUID id) {
        ItemPedido itemPedido = buscarPorId(id);
        itemPedidoRepository.delete(itemPedido);
        return itemPedido;
    }

}
