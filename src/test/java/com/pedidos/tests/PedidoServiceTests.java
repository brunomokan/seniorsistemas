package com.pedidos.tests;

import com.pedidos.dtos.PedidoDTO;
import com.pedidos.entities.Pedido;
import com.pedidos.enums.SituacaoPedido;
import com.pedidos.exceptions.DescontoEmPedidoFechadoException;
import com.pedidos.exceptions.RegistroNaoEncontradoException;
import com.pedidos.repositories.PedidoRepository;
import com.pedidos.services.PedidoService;
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
public class PedidoServiceTests {

    @Mock
    private PedidoRepository pedidoRepository;

    PedidoService pedidoService;

    @BeforeAll
    public void setup(){

        MockitoAnnotations.openMocks(this);

        Mockito.when(pedidoRepository.findById(UUID.fromString("c6e7e61a-838e-4b0a-a19a-66e127ad4aa4")))
                .thenReturn(Optional.empty());
        Mockito.when(pedidoRepository.findById(UUID.fromString("c6e7e61a-838e-4b0a-a19a-66e127ad4aa3")))
                .thenReturn(Optional.of(Pedido.builder()
                        .situacaoPedido(SituacaoPedido.FECHADO)
                        .percentualDesconto(10)
                        .build()
                        ));

        pedidoService = new PedidoService(pedidoRepository);

    }

    @Test
    public void naoPodeDarDescontoEmPedidoFechado(){
        Assert.assertThrows(
                DescontoEmPedidoFechadoException.class,
                () -> pedidoService.atualizar(
                        UUID.fromString("c6e7e61a-838e-4b0a-a19a-66e127ad4aa3"),
                        PedidoDTO.builder().percentualDesconto(20).build()
                )
        );
    }

    @Test
    public void deveDispararExceptionAoAlterarPedidoInexistente(){
        Assert.assertThrows(
                RegistroNaoEncontradoException.class,
                () -> pedidoService.atualizar(
                        UUID.fromString("c6e7e61a-838e-4b0a-a19a-66e127ad4aa4"),
                        PedidoDTO.builder().build()
                )
        );
    }

}
