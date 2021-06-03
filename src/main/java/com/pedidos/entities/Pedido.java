package com.pedidos.entities;

import com.pedidos.enums.SituacaoPedido;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Transient
    @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ItemPedido> itensPedido;

    @Column(name = "percentual_desconto")
    private float percentualDesconto;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "situacao_pedido")
    private SituacaoPedido situacaoPedido;

}
