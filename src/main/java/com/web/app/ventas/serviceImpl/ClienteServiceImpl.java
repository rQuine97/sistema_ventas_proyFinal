/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.app.ventas.serviceImpl;

import com.web.app.ventas.model.Cliente;
import com.web.app.ventas.repository.ClienteRepository;
import com.web.app.ventas.service.ClienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClienteServiceImpl implements ClienteService{
    
    @Autowired
    private ClienteRepository repository;
    
    @Override
    public List<Cliente> readAll() {
        return repository.findAll();
    }

    @Override
    public Cliente create(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    public Cliente update(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    public Cliente read(Long idcliente) {
        return repository.findById(idcliente).get();
    }

    @Override
    public void delete(Long idcliente) {
        repository.deleteById(idcliente);
    }
    
}
