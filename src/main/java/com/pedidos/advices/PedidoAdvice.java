package com.pedidos.advices;

import com.pedidos.exceptions.DescontoEmPedidoFechadoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PedidoAdvice {

    @ResponseBody
    @ExceptionHandler(DescontoEmPedidoFechadoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String descontoEmPedidoFechadoHandler(DescontoEmPedidoFechadoException ex){
        return ex.getMessage();
    }

}
