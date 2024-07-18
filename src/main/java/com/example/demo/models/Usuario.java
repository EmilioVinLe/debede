package com.example.demo.models;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;


@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    private Long rut;
    private String nombre;
    private  String correo;
    private   String pass;
    @ManyToOne
    private Direction direccion;
    @OneToMany(mappedBy = "usuario")
    private List<Producto> productosLista;

    @OneToOne(mappedBy = "usuarioListaDeseo")
    private ListaDeseos listaDeseos;

    @OneToOne(mappedBy = "usuarioCarrito")
    private CarroCompras carroCompras;
    @Column
    @Enumerated(EnumType.STRING)
    private Usuario.Rol rol;



    public enum Rol{
        ADMIN,
        PYME,
        CLIENTE
    }
    private int pyme_id;

    public Usuario(Long rut, String nombre, String correo, String pass, Direction direccion, List<Producto> productosLista, ListaDeseos listaDeseos, CarroCompras carroCompras, Rol rol, int pyme_id) {
        this.rut = rut;
        this.nombre = nombre;
        this.correo = correo;
        this.pass = pass;
        this.direccion = direccion;
        this.productosLista = productosLista;
        this.listaDeseos = listaDeseos;
        this.carroCompras = carroCompras;
        this.rol = rol;
        this.pyme_id = pyme_id;
    }

    public Usuario() {

    }

    public ListaDeseos getListaDeseos() {
        return listaDeseos;
    }

    public void setListaDeseos(ListaDeseos listaDeseos) {
        this.listaDeseos = listaDeseos;
    }

    public CarroCompras getCarroCompras() {
        return carroCompras;
    }

    public void setCarroCompras(CarroCompras carroCompras) {
        this.carroCompras = carroCompras;
    }

    public Long getRut() {
        return rut;
    }

    public void setRut(Long rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Direction getDireccion() {
        return direccion;
    }

    public void setDireccion(Direction direccion) {
        this.direccion = direccion;
    }

    public List<Producto> getProductosLista() {
        return productosLista;
    }

    public void setProductosLista(List<Producto> productosLista) {
        this.productosLista = productosLista;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public int getPyme_id() {
        return pyme_id;
    }

    public void setPyme_id(int pyme_id) {
        this.pyme_id = pyme_id;
    }
}
