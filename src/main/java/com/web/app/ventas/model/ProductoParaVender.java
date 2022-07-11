
package com.web.app.ventas.model;


public class ProductoParaVender extends Producto{
    private Float cantidad;

    public ProductoParaVender(String nombre, String codigo, Float precio, Float existencia, Integer id, String imagen, Float cantidad) {
        super(nombre, codigo, precio, existencia, id, imagen);
        this.cantidad = cantidad;
    }

    public ProductoParaVender(String nombre, String codigo, Float precio, Float existencia,String imagen, Float cantidad) {
        super(nombre, codigo, precio, existencia, imagen);
        this.cantidad = cantidad;
    }

    public void aumentarCantidad() {
        this.cantidad++;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public Float getTotal() {
        return this.getPrecio() * this.cantidad;
    }
}
