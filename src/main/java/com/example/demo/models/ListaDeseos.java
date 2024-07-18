package com.example.demo.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "listaDeseo")
public class ListaDeseos {
    @Id
    private int id;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuarioListaDeseo;
    @OneToMany
    private List<Producto> productosListaDeseo;

    public ListaDeseos(int id, Usuario usuarioListaDeseo, List<Producto> productosListaDeseo) {
        this.id = id;
        this.usuarioListaDeseo = usuarioListaDeseo;
        this.productosListaDeseo = productosListaDeseo;
    }

    public ListaDeseos() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuarioListaDeseo() {
        return usuarioListaDeseo;
    }

    public void setUsuarioListaDeseo(Usuario usuarioListaDeseo) {
        this.usuarioListaDeseo = usuarioListaDeseo;
    }

    public List<Producto> getProductosListaDeseo() {
        return productosListaDeseo;
    }

    public void setProductosListaDeseo(List<Producto> productosListaDeseo) {
        this.productosListaDeseo = productosListaDeseo;
    }
}
