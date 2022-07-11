/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.web.app.ventas.service;

import com.web.app.ventas.model.Cliente;
import java.util.List;


public interface ClienteService {
    
    public List<Cliente> readAll();
    public Cliente create(Cliente cliente);
    public Cliente update(Cliente cliente);
    public Cliente read(Long idcliente);
    public void delete(Long idcliente);
}
