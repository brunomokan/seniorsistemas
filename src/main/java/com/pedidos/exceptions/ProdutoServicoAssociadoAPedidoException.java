package com.pedidos.exceptions;

public class ProdutoServicoAssociadoAPedidoException extends RuntimeException {

    public ProdutoServicoAssociadoAPedidoException() {
        super("Não é possível excluir um produto/serviço que esteja associado a um pedido.");
    }
}

