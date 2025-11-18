package com.example.delahuerta.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.delahuerta.model.Pedido;
import com.example.delahuerta.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    // 1) Captura de pedidos (cliente público)
    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido) {
        Pedido creado = pedidoService.crearPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // 2) Consulta de pedidos (empleados autenticados)
    // /api/pedidos?estado=pendiente&cliente=juan
    @GetMapping
    public List<Pedido> listarPedidos(
            @RequestParam(name = "estado", required = false) String estado,
            @RequestParam(name = "cliente", required = false) String cliente) {
        return pedidoService.buscarPedidos(estado, cliente);
    }

    // 3) Cambiar estado de un pedido
    @PatchMapping("/{id}/estado")
    public ResponseEntity<Pedido> cambiarEstado(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        String nuevoEstado = body.getOrDefault("estado", "").toLowerCase();
        if (!"pendiente".equals(nuevoEstado) && !"atendido".equals(nuevoEstado)) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Pedido actualizado = pedidoService.cambiarEstado(id, nuevoEstado);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
