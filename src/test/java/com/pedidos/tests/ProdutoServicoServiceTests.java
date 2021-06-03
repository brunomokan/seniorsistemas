package com.pedidos.tests;

import com.pedidos.dtos.ProdutoServicoDTO;
import com.pedidos.entities.ProdutoServico;
import com.pedidos.exceptions.ProdutoServicoAssociadoAPedidoException;
import com.pedidos.exceptions.RegistroNaoEncontradoException;
import com.pedidos.repositories.ItemPedidoRepository;
import com.pedidos.repositories.ProdutoServicoRepository;
import com.pedidos.services.ProdutoServicoService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProdutoServicoServiceTests {

    @Mock
    ProdutoServicoRepository produtoServicoRepository;
    @Mock
    ItemPedidoRepository itemPedidoRepository;

    ProdutoServicoService produtoServicoService;

    @BeforeAll
    public void setup(){

        MockitoAnnotations.openMocks(this);

        Mockito.when(produtoServicoRepository.findById(UUID.fromString("c6e7e61a-838e-4b0a-a19a-66e127ad4aa3")))
                .thenReturn(Optional.empty());
        Mockito.when(produtoServicoRepository.findById(UUID.fromString("c6e7e61a-838e-4b0a-a19a-66e127ad4aa4")))
                .thenReturn(Optional.of(new ProdutoServico()));
        Mockito.when(itemPedidoRepository.countAllByProdutoServico(Mockito.any(ProdutoServico.class)))
                .thenReturn(1);

        produtoServicoService = new ProdutoServicoService(produtoServicoRepository, itemPedidoRepository);
    }

    @Test
    public void naoDeveAlterarProdutoServicoInexistente() {
        ProdutoServicoDTO build = ProdutoServicoDTO.builder().build();
        Assert.assertThrows(
                RegistroNaoEncontradoException.class,
                () -> produtoServicoService.atualizar(UUID.fromString("c6e7e61a-838e-4b0a-a19a-66e127ad4aa3"), build)
        );
    }

    @Test
    public void naoDeveExcluirProdutoNaoEncontrado() {
        Assert.assertThrows(
                RegistroNaoEncontradoException.class,
                () -> produtoServicoService.excluir(UUID.fromString("c6e7e61a-838e-4b0a-a19a-66e127ad4aa3"))
        );
    }

    @Test
    public void naoDeveExcluirProdutoAssociadoAPedido() {
        Assert.assertThrows(
                ProdutoServicoAssociadoAPedidoException.class,
                () -> produtoServicoService.excluir(UUID.fromString("c6e7e61a-838e-4b0a-a19a-66e127ad4aa4"))
        );
    }

}
