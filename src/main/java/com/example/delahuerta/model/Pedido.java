package com.example.delahuerta.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Lo guardamos como texto ISO (string) para no complicarnos con formatos de fecha
    private String timestamp;

    private String nombre;
    private String telefono;
    private String ciudad;
    private String direccion;

    @JsonProperty("otros_datos")
    @Column(name = "otros_datos")
    private String otrosDatos;

    @Column(columnDefinition = "TEXT")
    private String productos;

    @JsonProperty("valor_total")
    @Column(name = "valor_total")
    private Double valorTotal;

    // pendiente | atendido
    private String estado = "pendiente";

    public Pedido() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getOtrosDatos() { return otrosDatos; }
    public void setOtrosDatos(String otrosDatos) { this.otrosDatos = otrosDatos; }

    public String getProductos() { return productos; }
    public void setProductos(String productos) { this.productos = productos; }

    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
