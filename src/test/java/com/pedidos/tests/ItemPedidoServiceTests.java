package com.pedidos.tests;

import com.pedidos.dtos.ItemPedidoNovoDTO;
import com.pedidos.entities.ProdutoServico;
import com.pedidos.exceptions.ProdutoServicoInativoException;
import com.pedidos.exceptions.RegistroNaoEncontradoException;
import com.pedidos.repositories.ItemPedidoRepository;
import com.pedidos.repositories.ProdutoServicoRepository;
import com.pedidos.services.ItemPedidoService;
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
public class ItemPedidoServiceTests {

    @Mock
    private ItemPedidoRepository itemPedidoRepository;

    @Mock
    private ProdutoServicoRepository produtoServicoRepository;

    private ItemPedidoService itemPedidoService;

    @BeforeAll
    public void setup(){

        MockitoAnnotations.openMocks(this);

        Mockito.when(produtoServicoRepository.findById(UUID.fromString("c6e7e61a-838e-4b0a-a19a-66e127ad4aa3")))
                .thenReturn(Optional.empty());

        Mockito.when(produtoServicoRepository.findById(UUID.fromString("c6e7e61a-838e-4b0a-a19a-66e127ad4aa4")))
                .thenReturn(Optional.of(
                        ProdutoServico.builder().ativo(false).build()
                ));

        itemPedidoService = new ItemPedidoService(itemPedidoRepository, produtoServicoRepository);

    }

    @Test
    public void naoDeveAdicionarProdutoInexistenteAoPedido(){

        Assert.assertThrows(
                RegistroNaoEncontradoException.class,
                () -> itemPedidoService.salvar(
                        ItemPedidoNovoDTO.builder()
                                .pedidoId(UUID.fromString("c6e7e61a-838e-4b0a-a19a-66e127ad4aa0"))
                                .produtoServicoId(UUID.fromString("c6e7e61a-838e-4b0a-a19a-66e127ad4aa3"))
                                .build()
                )
        );
    }

    @Test
    public void naoDeveAdicionarProdutoInativoAoPedido(){

        Assert.assertThrows(
                ProdutoServicoInativoException.class,
                () -> itemPedidoService.salvar(
                        ItemPedidoNovoDTO.builder()
                                .pedidoId(UUID.fromString("c6e7e61a-838e-4b0a-a19a-66e127ad4aa0"))
                                .produtoServicoId(UUID.fromString("c6e7e61a-838e-4b0a-a19a-66e127ad4aa4"))
                                .build()
                )
        );

    }


}
