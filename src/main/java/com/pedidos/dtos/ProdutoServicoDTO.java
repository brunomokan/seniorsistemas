package com.pedidos.dtos;

import com.pedidos.enums.TipoProdutoServico;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProdutoServicoDTO {
    private String nome;
    private TipoProdutoServico tipoProdutoServico;
    private boolean ativo;
}
