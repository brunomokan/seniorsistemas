package com.pedidos.repositories;

import com.pedidos.entities.ItemPedido;
import com.pedidos.entities.ProdutoServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, UUID> {
    public int countAllByProdutoServico(ProdutoServico produtoServico);
}
