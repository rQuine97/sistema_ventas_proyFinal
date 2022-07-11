/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.app.ventas.controller;

import com.lowagie.text.DocumentException;
import com.web.app.ventas.model.Venta;
import com.web.app.ventas.repository.VentaRepository;
import com.web.app.ventas.util.reportes.ClienteExporterPDF;
import com.web.app.ventas.util.reportes.VentaExporterExcel;
import com.web.app.ventas.util.reportes.VentaExporterPDF;
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
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author rquin
 */
@Controller
@RequestMapping(path = "/ventas")
public class VentasController {
    @Autowired
    VentaRepository ventasRepository;

    @GetMapping(value = "/")
    public String mostrarVentas(Model model) {
        model.addAttribute("ventas", ventasRepository.findAll());
        return "ventas/ver_ventas";
    }
    
    @GetMapping("/exportarPDF")
    public void exportarListadoDeVentasEnPDF(HttpServletResponse response) throws DocumentException, IOException{
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormatter.format(new Date());
        
        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Ventas_" + fechaActual + ".pdf";
        
        response.setHeader(cabecera, valor);
        
        List<Venta> ventas = (List<Venta>) ventasRepository.findAll();
        
        VentaExporterPDF exporter = new VentaExporterPDF(ventas);
        exporter.exportar(response);
    }
    
    @GetMapping("/exportarExcel")
    public void exportarListadoDeVentasEnExcel(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/octet-stream");
        
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormatter.format(new Date());
        
        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Cliente_" + fechaActual + ".xlsx";
        
        response.setHeader(cabecera, valor);
        
        List<Venta> ventas = (List<Venta>) ventasRepository.findAll();
        
        VentaExporterExcel exporter = new VentaExporterExcel(ventas);
        exporter.exportar(response);
    }
}
