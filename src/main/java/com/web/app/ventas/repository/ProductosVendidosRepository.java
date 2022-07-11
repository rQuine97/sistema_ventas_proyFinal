/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.web.app.ventas.repository;

import com.web.app.ventas.model.ProductoVendido;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author rquin
 */
public interface ProductosVendidosRepository extends CrudRepository<ProductoVendido, Integer> {
    
}
