package com.example.delahuerta.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.delahuerta.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByEstado(String estado);

    List<Pedido> findByNombreContainingIgnoreCase(String nombre);

    List<Pedido> findByNombreContainingIgnoreCaseAndEstado(String nombre, String estado);
}
