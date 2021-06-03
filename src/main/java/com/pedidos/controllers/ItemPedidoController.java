package com.pedidos.controllers;

import com.pedidos.dtos.ItemPedidoAtualizarDTO;
import com.pedidos.dtos.ItemPedidoNovoDTO;
import com.pedidos.entities.ItemPedido;
import com.pedidos.services.ItemPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/itenspedido")
public class ItemPedidoController {

    @Autowired
    private ItemPedidoService itemPedidoService;

    @PostMapping("/")
    public ItemPedido create(@RequestBody ItemPedidoNovoDTO itemPedidoNovoDTO) {
        return itemPedidoService.salvar(itemPedidoNovoDTO);
    }

    @GetMapping("/{id}")
    public ItemPedido read(@PathVariable(name = "id") UUID id) {
        return itemPedidoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ItemPedido update(@PathVariable(name = "id") UUID id, @RequestBody ItemPedidoAtualizarDTO itemPedidoAtualizarDTO) {
        return itemPedidoService.atualizar(id, itemPedidoAtualizarDTO);
    }

    @DeleteMapping("/{id}")
    public ItemPedido delete(@PathVariable(name = "id") UUID id) {
        return itemPedidoService.excluir(id);
    }

    @GetMapping("/")
    public Page<ItemPedido> list(
            @RequestParam(name = "page", required = false) Optional<Integer> page,
            @RequestParam(name = "size", required = false) Optional<Integer> size
    ) {
        return itemPedidoService.listarPaginado(page, size);
    }

}
