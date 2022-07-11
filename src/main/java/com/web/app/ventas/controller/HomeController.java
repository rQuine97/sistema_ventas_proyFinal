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

/**
 *
 * @author rquin
 */
@Controller
public class HomeController {

    @Autowired
    private UsuarioService servicio;

//    @GetMapping({"/index","/"})
//    public String index(){
//        return "index";
//    }
    @GetMapping("/login")
    public String iniciarSesion() {
        return "login";
    }

//    @GetMapping("/")
//    public String verPaginaDeInicio(Model model) {
//        model.addAttribute("usuarios", servicio.listarUsuarios());
//        return "index";
//    }
    
    @GetMapping("/")
    public String verPaginaDeInicio() {
        
        return "index";
    }
    
}
