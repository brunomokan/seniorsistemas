package com.pedidos.controllers;

import com.pedidos.dtos.PedidoDTO;
import com.pedidos.entities.Pedido;
import com.pedidos.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/")
    public Pedido create(@RequestBody PedidoDTO pedidoDTO){
        return pedidoService.salvar(pedidoDTO);
    }

    @GetMapping("/{id}")
    public Pedido read(@PathVariable(name = "id") UUID id){
        return pedidoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Pedido update(@PathVariable(name = "id") UUID id, @RequestBody PedidoDTO pedidoDTO){
        return pedidoService.atualizar(id, pedidoDTO);
    }

    @DeleteMapping("/{id}")
    public Pedido delete(@PathVariable(name = "id") UUID id){
        return pedidoService.excluir(id);
    }

    @GetMapping("/")
    public Page<Pedido> list(
            @RequestParam(name = "page", required = false) Optional<Integer> page,
            @RequestParam(name = "size", required = false) Optional<Integer> size
    ){
        return pedidoService.listarPaginado(page, size);
    }

}
