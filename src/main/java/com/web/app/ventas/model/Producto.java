/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.app.ventas.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author rquin
 */
@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //@NotNull(message = "Debes especificar el nombre")
    //@Size(min = 1, max = 50, message = "El nombre debe medir entre 1 y 50")
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    //@NotNull(message = "Debes especificar el código")
    //@Size(min = 1, max = 50, message = "El código debe medir entre 1 y 50")
    @Column(name = "codigo", nullable = false, length = 50)
    private String codigo;

    //@NotNull(message = "Debes especificar el precio")
    //@Min(value = 0, message = "El precio mínimo es 0")
    @Column(name = "precio", nullable = false)
    private Float precio;

    //@NotNull(message = "Debes especificar la existencia")
    //@Min(value = 0, message = "La existencia mínima es 0")
    @Column(name = "existencia", nullable = false)
    private Float existencia;
    
    @Column(name = "imagen", nullable = false, length=200)
    private String imagen;


    public Producto(String nombre, String codigo, Float precio, Float existencia, Integer id, String imagen) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.precio = precio;
        this.existencia = existencia;
        this.id = id;
        this.imagen = imagen;
    }

    public Producto(String nombre, String codigo, Float precio, Float existencia, String imagen) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.precio = precio;
        this.existencia = existencia;
        this.imagen = imagen;
    }

    public Producto( String codigo) {
        this.codigo = codigo;
    }

    public Producto() {
    }

    public boolean sinExistencia() {
        return this.existencia <= 0;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Float getExistencia() {
        return existencia;
    }

    public void setExistencia(Float existencia) {
        this.existencia = existencia;
    }

    public void restarExistencia(Float existencia) {
        this.existencia -= existencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", codigo=" + codigo + ", precio=" + precio + ", existencia=" + existencia + ", imagen=" + imagen + '}';
    }
}