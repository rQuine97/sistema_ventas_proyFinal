/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.app.ventas.controller;

import com.web.app.ventas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private UsuarioService service;
    

    @GetMapping("/")
    public String verPaginaDeInicio(Model model) {
        model.addAttribute("usuarios", service.listarUsuarios());
        return "empleados/ver_empleado";
    }
    
    
    @GetMapping("/{id}")
    public String eliminarUsuario(@PathVariable("id") Long id){
        service.eliminarUsuario(id);
        return "redirect:/empleados/";
    }
    
}
