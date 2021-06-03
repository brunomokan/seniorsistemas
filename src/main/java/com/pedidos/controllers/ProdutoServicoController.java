package com.pedidos.controllers;

import com.pedidos.dtos.ProdutoServicoDTO;
import com.pedidos.entities.ProdutoServico;
import com.pedidos.services.ProdutoServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/produtoservico")
public class ProdutoServicoController {

    @Autowired
    private ProdutoServicoService produtoServicoService;

    @PostMapping("/")
    public ProdutoServico create(@RequestBody ProdutoServicoDTO produtoServicoDTO){
        return produtoServicoService.salvar(produtoServicoDTO);
    }

    @GetMapping("/{id}")
    public ProdutoServico read(@PathVariable(name = "id") UUID id){
        return produtoServicoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ProdutoServico update(@PathVariable(name = "id") UUID id, @RequestBody ProdutoServicoDTO produtoServicoDTO){
        return produtoServicoService.atualizar(id, produtoServicoDTO);
    }

    @DeleteMapping("/{id}")
    public ProdutoServico delete(@PathVariable(name = "id") UUID id){
        return produtoServicoService.excluir(id);
    }

    @GetMapping("/")
    public Page<ProdutoServico> list(
            @RequestParam(name = "page", required = false) Optional<Integer> page,
            @RequestParam(name = "size", required = false) Optional<Integer> size
    ){
        return produtoServicoService.listarPaginado(page, size);
    }

}
