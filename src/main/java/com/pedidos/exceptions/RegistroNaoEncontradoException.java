package com.pedidos.exceptions;

import java.util.UUID;

public class RegistroNaoEncontradoException extends RuntimeException {

    public RegistroNaoEncontradoException(UUID id) {
        super("Não foi possível encontrar o registro de id \"" + id + "\"");
    }
}

