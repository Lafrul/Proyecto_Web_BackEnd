package com.example.delahuerta.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.delahuerta.model.Pedido;
import com.example.delahuerta.repository.PedidoRepository;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepo;

    public PedidoService(PedidoRepository pedidoRepo) {
        this.pedidoRepo = pedidoRepo;
    }

    public Pedido crearPedido(Pedido pedido) {
        pedido.setId(null); // fuerza INSERT

        if (!StringUtils.hasText(pedido.getEstado())) {
            pedido.setEstado("pendiente");
        }

        if (!StringUtils.hasText(pedido.getTimestamp())) {
            pedido.setTimestamp(java.time.Instant.now().toString());
        }

        return pedidoRepo.save(pedido);
    }

    public List<Pedido> buscarPedidos(String estado, String cliente) {
        boolean hasEstado = StringUtils.hasText(estado);
        boolean hasCliente = StringUtils.hasText(cliente);

        if (hasEstado && hasCliente) {
            return pedidoRepo.findByNombreContainingIgnoreCaseAndEstado(cliente, estado);
        } else if (hasEstado) {
            return pedidoRepo.findByEstado(estado);
        } else if (hasCliente) {
            return pedidoRepo.findByNombreContainingIgnoreCase(cliente);
        } else {
            return pedidoRepo.findAll();
        }
    }

    public Pedido cambiarEstado(Long id, String nuevoEstado) {
        Pedido p = pedidoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        p.setEstado(nuevoEstado);
        return pedidoRepo.save(p);
    }
}
