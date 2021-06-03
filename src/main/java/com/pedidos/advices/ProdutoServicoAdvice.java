package com.pedidos.advices;

import com.pedidos.exceptions.ProdutoServicoAssociadoAPedidoException;
import com.pedidos.exceptions.ProdutoServicoInativoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ProdutoServicoAdvice {

    @ResponseBody
    @ExceptionHandler(ProdutoServicoInativoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String produtoServicoInativoHandler(ProdutoServicoInativoException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ProdutoServicoAssociadoAPedidoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String produtoServicoAssociadoAPedidoExceptionHandler(ProdutoServicoAssociadoAPedidoException ex){
        return ex.getMessage();
    }

}
