/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.web.app.ventas.service;

import com.web.app.ventas.dto.UsuarioRegistroDTO;
import com.web.app.ventas.model.Usuario;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioService extends UserDetailsService {

    public Usuario guardar(UsuarioRegistroDTO registroDTO);

    public List<Usuario> listarUsuarios();
    
    public Usuario obtenerUsuarioPorId(Long id);
//
    public Usuario actualizarUsuario(Usuario usuario);
//
    public void eliminarUsuario(Long id);
}
