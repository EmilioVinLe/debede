package com.example.demo.models;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany
    private List<Producto> productos;

    private Date fechaPedido;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "direccion_id")
    private Direction direccionEnvio;

    public Order(Long id, Usuario usuario, List<Producto> productos, Date fechaPedido, String estado, Direction direccionEnvio) {
        this.id = id;
        this.usuario = usuario;
        this.productos = productos;
        this.fechaPedido = fechaPedido;
        this.estado = estado;
        this.direccionEnvio = direccionEnvio;
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Direction getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(Direction direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }
}
