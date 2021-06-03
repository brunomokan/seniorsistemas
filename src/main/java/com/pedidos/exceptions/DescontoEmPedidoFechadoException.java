package com.pedidos.exceptions;

public class DescontoEmPedidoFechadoException extends RuntimeException {

    public DescontoEmPedidoFechadoException() {
        super("Não é possível aplicar desconto em um pedido Fechado");
    }
}

