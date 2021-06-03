package com.pedidos.exceptions;

public class ProdutoServicoInativoException extends RuntimeException {

    public ProdutoServicoInativoException() {
        super("Não é possível adicionar um produto/serviço desativado em um pedido");
    }
}

