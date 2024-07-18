package com.example.demo.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "carroCompras")
public class CarroCompras {
    @Id
    private int id;
    @OneToMany
    private List<Producto> productosList;
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuarioCarrito;

    public CarroCompras(int id, List<Producto> productosList, Usuario usuarioCarrito) {
        this.id = id;
        this.productosList = productosList;
        this.usuarioCarrito = usuarioCarrito;
    }

    public CarroCompras() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Producto> getProductosList() {
        return productosList;
    }

    public void setProductosList(List<Producto> productosList) {
        this.productosList = productosList;
    }

    public Usuario getUsuarioCarrito() {
        return usuarioCarrito;
    }

    public void setUsuarioCarrito(Usuario usuarioCarrito) {
        this.usuarioCarrito = usuarioCarrito;
    }
}
