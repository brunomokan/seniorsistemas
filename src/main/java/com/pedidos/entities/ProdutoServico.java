package com.pedidos.entities;

import com.pedidos.enums.TipoProdutoServico;
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
@Table(name = "produtos_servicos")
public class ProdutoServico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Column(name = "nome")
    private String nome;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_produto_sevico")
    private TipoProdutoServico tipoProdutoServico;

    @NotNull
    @Column(name = "ativo")
    private boolean ativo;

    @Transient
    @OneToMany(mappedBy = "produtoServico", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ItemPedido> itensPedido;

}
