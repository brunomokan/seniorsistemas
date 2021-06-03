package com.pedidos.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "itens_pedidos")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private ProdutoServico produtoServico;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Pedido pedido;

    @NotNull
    @Column(name = "quantidade")
    private double quantidade;

    @NotNull
    @Column(name = "valor_unitario")
    private double valorUnitario;

}
