package com.pedidos.services;

import com.pedidos.dtos.ProdutoServicoDTO;
import com.pedidos.entities.ProdutoServico;
import com.pedidos.exceptions.ProdutoServicoAssociadoAPedidoException;
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
public class ProdutoServicoService {

    @Autowired
    private ProdutoServicoRepository produtoServicoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public ProdutoServico buscarPorId(UUID id) {
        return produtoServicoRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException(id));
    }

    public Page<ProdutoServico> listarPaginado(Optional<Integer> page, Optional<Integer> size) {
        return produtoServicoRepository.findAll(PageRequest.of(page.orElse(0), size.orElse(10)));
    }

    public ProdutoServico salvar(ProdutoServicoDTO produtoServicoDTO) {
        return produtoServicoRepository.save(
                ProdutoServico.builder()
                        .nome(produtoServicoDTO.getNome())
                        .ativo(produtoServicoDTO.isAtivo())
                        .tipoProdutoServico(produtoServicoDTO.getTipoProdutoServico())
                        .build()
        );
    }

    public ProdutoServico atualizar(UUID id, ProdutoServicoDTO produtoServicoDTO) {

        ProdutoServico produtoServico = produtoServicoRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException(id));

        produtoServico.setNome(produtoServicoDTO.getNome());
        produtoServico.setAtivo(produtoServicoDTO.isAtivo());
        produtoServico.setTipoProdutoServico(produtoServicoDTO.getTipoProdutoServico());

        return produtoServicoRepository.save(produtoServico);
    }

    public ProdutoServico excluir(UUID id) {

        ProdutoServico produtoServico = buscarPorId(id);

        if(itemPedidoRepository.countAllByProdutoServico(produtoServico) > 0){
            throw new ProdutoServicoAssociadoAPedidoException();
        }

        produtoServicoRepository.delete(produtoServico);

        return produtoServico;

    }

}
