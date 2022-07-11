/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.app.ventas.controller;

import com.lowagie.text.DocumentException;
import com.web.app.ventas.model.Cliente;
import com.web.app.ventas.service.ClienteService;
import com.web.app.ventas.util.reportes.ClienteExporterEXCEL;
import com.web.app.ventas.util.reportes.ClienteExporterPDF;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteservice;
    
    @GetMapping("/mostrar")
    public String getClientes(Model modelo) {
        modelo.addAttribute("clientes", clienteservice.readAll());
        return "clientes/ver_clientes";
    }
    
    
    @GetMapping("/agregar")
    public String showFormRegisterCliente(Model modelo){
        Cliente cliente = new Cliente();
        modelo.addAttribute("cliente", cliente);
        return "clientes/agregar_cliente";
    }
    
    @PostMapping("/agregar")
    public String addCliente(@ModelAttribute("cliente") Cliente cliente){
        clienteservice.create(cliente);
        return "redirect:/clientes/agregar";
    }
    
    @GetMapping("/editar/{idcliente}")
    public String showFormCliente(@PathVariable Long idcliente, Model modelo){
        modelo.addAttribute("cliente", clienteservice.read(idcliente));
        return "/clientes/editar_cliente";
    }
    
    @PostMapping("/{idcliente}")
    public String editCliente(@PathVariable Long idcliente, @ModelAttribute("cliente") Cliente cliente, Model modelo){
        Cliente clienteExistente = clienteservice.read(idcliente);
        clienteExistente.setIdcliente(idcliente);
        clienteExistente.setNombres(cliente.getNombres());
        clienteExistente.setApellidos(cliente.getApellidos());
        clienteExistente.setDni(cliente.getDni());
        clienteExistente.setTelefono(cliente.getTelefono());
        clienteExistente.setCorreo(cliente.getCorreo());
        
        clienteservice.update(cliente);
        return "redirect:/clientes/mostrar";
    }
    
    @GetMapping("/{idcliente}")
    public String deleteCliente(@PathVariable Long idcliente){
        clienteservice.delete(idcliente);
        return "redirect:/clientes/mostrar";
    }
    
    @GetMapping("/exportarPDF")
    public void exportarListadoDeClientesEnPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormatter.format(new Date());
        
        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Clientes_" + fechaActual + ".pdf";
        
        response.setHeader(cabecera, valor);
        
        List<Cliente> clientes = clienteservice.readAll();
        
        ClienteExporterPDF exporter = new ClienteExporterPDF(clientes);
        exporter.exportar(response);
    }
    
    @GetMapping("/exportarExcel")
    public void exportarListadoDeClientesEnExcel(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/octet-stream");
        
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormatter.format(new Date());
        
        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Cliente_" + fechaActual + ".xlsx";
        
        response.setHeader(cabecera, valor);
        
        List<Cliente> clientes = clienteservice.readAll();
        
        ClienteExporterEXCEL exporter = new ClienteExporterEXCEL(clientes);
        exporter.exportar(response);
    }
}
