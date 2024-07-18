package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "valoracion")
public class Valoracion {
    @Id
    private int id;
    @OneToOne
    private Usuario usuarioValoracion;
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto productoValoracion;

    public Valoracion(int id, Usuario usuarioValoracion, String descripcion, Producto productoValoracion) {
        this.id = id;
        this.usuarioValoracion = usuarioValoracion;
        this.descripcion = descripcion;
        this.productoValoracion = productoValoracion;
    }

    public Valoracion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuarioValoracion() {
        return usuarioValoracion;
    }

    public void setUsuarioValoracion(Usuario usuarioValoracion) {
        this.usuarioValoracion = usuarioValoracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Producto getProductoValoracion() {
        return productoValoracion;
    }

    public void setProductoValoracion(Producto productoValoracion) {
        this.productoValoracion = productoValoracion;
    }
}
