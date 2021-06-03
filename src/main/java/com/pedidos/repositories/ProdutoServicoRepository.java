package com.pedidos.repositories;

import com.pedidos.entities.ProdutoServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProdutoServicoRepository extends JpaRepository<ProdutoServico, UUID> {

    public abstract Optional<ProdutoServico> findAllByAtivo(boolean ativo);
    public abstract Optional<ProdutoServico> findByIdAndAtivo(UUID id, boolean ativo);

}
