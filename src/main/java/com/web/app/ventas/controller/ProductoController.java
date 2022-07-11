/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.app.ventas.controller;

import com.web.app.ventas.model.Producto;
import com.web.app.ventas.repository.ProductoRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productosRepository;

    @GetMapping("/agregar")
    public String agregarProducto(Model model) {
        model.addAttribute("producto", new Producto());
        return "productos/agregar_producto";
    }

    @GetMapping("/mostrar")
    public String mostrarProductos(Model model) {
        model.addAttribute("productos", productosRepository.findAll());
        return "productos/ver_productos";
    }

    @PostMapping("/eliminar")
    public String eliminarProducto(@ModelAttribute Producto producto, RedirectAttributes redirectAttrs) {
        redirectAttrs
                .addFlashAttribute("mensaje", "Eliminado correctamente")
                .addFlashAttribute("clase", "warning");
        productosRepository.deleteById(producto.getId());
        return "redirect:/productos/mostrar";
    }

    // Se colocó el parámetro ID para eso de los errores, ya sé el id se puede recuperar
    // a través del modelo, pero lo que yo quiero es que se vea la misma URL para regresar la vista con
    // los errores en lugar de hacer un redirect, ya que si hago un redirect, no se muestran los errores del formulario
    // y por eso regreso mejor la vista ;)
//    @PostMapping(value = "/editar/{id}")
//    public String actualizarProducto(@ModelAttribute @Valid Producto producto, BindingResult bindingResult, RedirectAttributes redirectAttrs, @RequestParam("file") MultipartFile imagen) {
//
//        if (bindingResult.hasErrors()) {
//            if (producto.getId() != null) {
//                return "productos/editar_producto";
//            }
//            return "redirect:/productos/mostrar";
//        }
//
//        Producto posibleProductoExistente = productosRepository.findFirstByCodigo(producto.getCodigo());
//
//        if (posibleProductoExistente != null && !posibleProductoExistente.getId().equals(producto.getId())) {
//            redirectAttrs
//                    .addFlashAttribute("mensaje", "Ya existe un producto con ese código")
//                    .addFlashAttribute("clase", "warning");
//            return "redirect:/productos/agregar";
//        }
//
//        if (!imagen.isEmpty()) {
//            String ruta = "D://proyectoLP//images";
//
//            try {
//                byte[] bytesImg = imagen.getBytes();
//                Path rutacompleta = Paths.get(ruta + "//" + imagen.getOriginalFilename());
//                Files.write(rutacompleta, bytesImg);
//                producto.setImagen(imagen.getOriginalFilename());
//                productosRepository.save(producto);
//                
//            } catch (IOException e) {
//                System.out.println("Error: " + e);
//            }
//
//        }else {
//            
//            productosRepository.save(producto);
//        }
//
//        //productosRepository.save(producto);
//        redirectAttrs
//                .addFlashAttribute("mensaje", "Editado correctamente")
//                .addFlashAttribute("clase", "success");
//        return "redirect:/productos/mostrar";
//    }
    @PostMapping("/editar/{id}")
    public String editProducto(@PathVariable Integer id, @ModelAttribute("producto") Producto producto, BindingResult bindingResult, RedirectAttributes redirectAttrs, @RequestParam("file") MultipartFile imagen) {
        if (bindingResult.hasErrors()) {
            if (producto.getId() != null) {
                return "productos/editar_producto";
            }
            return "redirect:/productos/mostrar";
        }
        
        Producto productoExistente = productosRepository.findById(id).orElse(null);

        if (!imagen.isEmpty()) {
            String ruta = "D://proyectoLP//images";

            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutacompleta = Paths.get(ruta + "//" + imagen.getOriginalFilename());
                Files.write(rutacompleta, bytesImg);
                //Producto productoExistente = service.read(idproducto);

                productoExistente.setId(id);
                productoExistente.setNombre(producto.getNombre());
                productoExistente.setCodigo(producto.getCodigo());
                productoExistente.setPrecio(producto.getPrecio());
                productoExistente.setExistencia(producto.getExistencia());
                productoExistente.setImagen(imagen.getOriginalFilename());

                productosRepository.save(productoExistente);

            } catch (IOException e) {
                System.out.println("Error: " + e);
            }
        } else {
            productoExistente.setId(id);
            productoExistente.setNombre(producto.getNombre());
            productoExistente.setCodigo(producto.getCodigo());
            productoExistente.setPrecio(producto.getPrecio());
            productoExistente.setExistencia(producto.getExistencia());

            productosRepository.save(productoExistente);
        }
        redirectAttrs
                .addFlashAttribute("mensaje", "Editado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/productos/mostrar";
    }

    @GetMapping(value = "/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable int id, Model model) {
        model.addAttribute("producto", productosRepository.findById(id).orElse(null));
        return "productos/editar_producto";
    }

    @PostMapping(value = "/agregar")
    public String guardarProducto(@ModelAttribute @Valid Producto producto, BindingResult bindingResult, RedirectAttributes redirectAttrs, @RequestParam("file") MultipartFile imagen) {
        //public String addProducto(@ModelAttribute("producto") Producto producto, @RequestParam("file") MultipartFile imagen) {        
        if (bindingResult.hasErrors()) {
            return "productos/agregar_producto";
        }

        if (productosRepository.findFirstByCodigo(producto.getCodigo()) != null) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un producto con ese código")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/productos/agregar";
        }

        if (!imagen.isEmpty()) {
            String ruta = "D://proyectoLP//images";
            //D:\proyectoLP\images

            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutacompleta = Paths.get(ruta + "//" + imagen.getOriginalFilename());
                Files.write(rutacompleta, bytesImg);
                producto.setImagen(imagen.getOriginalFilename());
            } catch (IOException e) {
                System.out.println("Error: " + e);
            }
            //productosRepository.save(producto);
        } else {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Agregue una imagen")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/productos/agregar";
        }

        productosRepository.save(producto);

        redirectAttrs
                .addFlashAttribute("mensaje", "Agregado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/productos/agregar";
    }

//    @GetMapping("/producto/detalle/{idproducto}")
//    public String detalleProducto(@PathVariable("idproducto") int idproducto, Model model) {
//        Producto producto = service.read(idproducto);
//        model.addAttribute("producto", producto);
//        return "detalle_producto";
//    }
    @GetMapping(value = "/detalle/{id}")
    public String detalleProducto(@PathVariable("id") int id, Model model) {
        //Producto producto = productosRepository.findFirstByCodigo(codigo);
        model.addAttribute("producto", productosRepository.findById(id).orElse(null));

        return "productos/detalle_producto";
    }

}
