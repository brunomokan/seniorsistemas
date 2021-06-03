package com.pedidos.services;

import com.pedidos.dtos.PedidoDTO;
import com.pedidos.enums.SituacaoPedido;
import com.pedidos.exceptions.DescontoEmPedidoFechadoException;
import com.pedidos.exceptions.RegistroNaoEncontradoException;
import com.pedidos.entities.Pedido;
import com.pedidos.repositories.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido buscarPorId(UUID id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException(id));
    }

    public Page<Pedido> listarPaginado(Optional<Integer> page, Optional<Integer> size) {
        return pedidoRepository.findAll(PageRequest.of(page.orElse(0), size.orElse(10)));
    }

    public Pedido salvar(PedidoDTO pedidoDTO) {
        return pedidoRepository.save(
                Pedido.builder()
                .percentualDesconto(pedidoDTO.getPercentualDesconto())
                .situacaoPedido(pedidoDTO.getSituacaoPedido())
                .build()
        );
    }

    public Pedido atualizar(UUID id, PedidoDTO pedidoDTO){

        Pedido pedido = buscarPorId(id);

        if(pedidoDTO.getPercentualDesconto() != pedido.getPercentualDesconto() && pedido.getSituacaoPedido() != SituacaoPedido.ABERTO){
            throw new DescontoEmPedidoFechadoException();
        }

        pedido.setPercentualDesconto(pedidoDTO.getPercentualDesconto());
        pedido.setSituacaoPedido(pedidoDTO.getSituacaoPedido());

        return pedidoRepository.save(pedido);

    }

    public Pedido excluir(UUID id) {
        Pedido pedido = buscarPorId(id);
        pedidoRepository.delete(pedido);
        return pedido;
    }

}
