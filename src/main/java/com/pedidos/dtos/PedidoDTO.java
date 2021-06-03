package com.pedidos.dtos;

import com.pedidos.enums.SituacaoPedido;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PedidoDTO {

    private float percentualDesconto;
    private SituacaoPedido situacaoPedido;

}
