package com.pedidos.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class ItemPedidoNovoDTO {
    private UUID pedidoId;
    private UUID produtoServicoId;
    private double quantidade;
    private double valorUnitario;
}
