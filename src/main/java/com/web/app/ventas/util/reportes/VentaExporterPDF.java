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
import com.web.app.ventas.model.Venta;
import java.awt.Color;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;


public class VentaExporterPDF {
    
    private List<Venta> listaVentas;
    
    public VentaExporterPDF(List<Venta> listaVentas){
        super();
        this.listaVentas = listaVentas;
    }
    
    private void escribirCabeceraDeLaTabla(PdfPTable tabla) {
        PdfPCell celda = new PdfPCell();
        
        celda.setBackgroundColor(Color.GRAY);
        celda.setPadding(5);
        
        Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
        fuente.setColor(Color.WHITE);
        
        celda.setPhrase(new Phrase("ID Venta", fuente));
        tabla.addCell(celda);
        
        celda.setPhrase(new Phrase("Fecha y Hora", fuente));
        tabla.addCell(celda);
        
        celda.setPhrase(new Phrase("Total Monto", fuente));
        tabla.addCell(celda);
    }
    
    private void escribirDatosDeLaTabla(PdfPTable tabla) {
        for(Venta venta : listaVentas){
            tabla.addCell(String.valueOf(venta.getId()));
            tabla.addCell(venta.getFechaYHora());
            tabla.addCell(String.valueOf("S/." + venta.getTotal()));
           
        }
    }
    
    public void exportar(HttpServletResponse response) throws DocumentException, IOException{
        Document documento = new Document(PageSize.A4);
        PdfWriter.getInstance(documento, response.getOutputStream());
        
        documento.open();
        
        Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fuente.setColor(Color.BLACK);
        fuente.setSize(18);
        
        Paragraph titulo = new Paragraph("Lista de Ventas", fuente);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        documento.add(titulo);
        
        PdfPTable tabla = new PdfPTable(3);
        tabla.setWidthPercentage(100);
        tabla.setSpacingBefore(15);
        tabla.setWidths(new float[]{1f, 2.3f, 2.3f});
        tabla.setWidthPercentage(110);
        
        escribirCabeceraDeLaTabla(tabla);
        escribirDatosDeLaTabla(tabla);
        
        documento.add(tabla);
        documento.close();
    }
    
}
