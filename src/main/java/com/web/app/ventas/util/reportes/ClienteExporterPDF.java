/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.app.ventas.util.reportes;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.web.app.ventas.model.Cliente;
import java.awt.Color;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;


public class ClienteExporterPDF {
    
    private List<Cliente> listaClientes;
    
    public ClienteExporterPDF(List<Cliente> listaClientes){
        super();
        this.listaClientes = listaClientes;
    }
    
    private void escribirCabeceraDeLaTabla(PdfPTable tabla){
        PdfPCell celda = new PdfPCell();
        
        celda.setBackgroundColor(Color.GRAY);
        celda.setPadding(5);
        
        Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
        fuente.setColor(Color.WHITE);
        
        celda.setPhrase(new Phrase("ID", fuente));
        tabla.addCell(celda);
        
        celda.setPhrase(new Phrase("Nombre", fuente));
        tabla.addCell(celda);
        
        celda.setPhrase(new Phrase("Apellido", fuente));
        tabla.addCell(celda);
        
        celda.setPhrase(new Phrase("DNI", fuente));
        tabla.addCell(celda);
        
        celda.setPhrase(new Phrase("Teléfono", fuente));
        tabla.addCell(celda);
        
        celda.setPhrase(new Phrase("correo", fuente));
        tabla.addCell(celda);
        
        
        //nombres
        //apellidos
        //dni
        //telefono
        //correo
    }
    
    private void escribirDatosDeLaTabla(PdfPTable tabla){
        for(Cliente cliente : listaClientes){
            tabla.addCell(String.valueOf(cliente.getIdcliente()));
            tabla.addCell(cliente.getNombres());
            tabla.addCell(cliente.getApellidos());
            tabla.addCell(cliente.getDni());
            tabla.addCell(cliente.getTelefono());
            tabla.addCell(cliente.getCorreo());
        }
    }
    
    public void exportar(HttpServletResponse response) throws DocumentException, IOException{
        Document documento = new Document(PageSize.A4);
        PdfWriter.getInstance(documento, response.getOutputStream());
        
        documento.open();
        
        Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fuente.setColor(Color.BLACK);
        fuente.setSize(18);
        
        Paragraph titulo = new Paragraph("Lista de Clientes", fuente);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        documento.add(titulo);
        
        PdfPTable tabla = new PdfPTable(6);
        tabla.setWidthPercentage(100);
        tabla.setSpacingBefore(15);
        tabla.setWidths(new float[]{1f, 2.3f, 2.3f, 2.3f, 2.9f, 3.5f});
        tabla.setWidthPercentage(110);
        
        escribirCabeceraDeLaTabla(tabla);
        escribirDatosDeLaTabla(tabla);
        
        documento.add(tabla);
        documento.close();
    }
    
}
