/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.app.ventas.util.reportes;

import com.web.app.ventas.model.Cliente;
import com.web.app.ventas.model.Venta;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class VentaExporterExcel {
    private XSSFWorkbook venta;
    private XSSFSheet hoja;
    
    private List<Venta> listaVentas;
    
    public VentaExporterExcel(List<Venta> listaVentas) {
        this.listaVentas = listaVentas;
        venta = new XSSFWorkbook();
        hoja = venta.createSheet("Ventas");
    }
    
    private void escribirCabeceraDeTabla() {
        Row fila = hoja.createRow(0);
        
        CellStyle estilo = venta.createCellStyle();
        XSSFFont fuente = venta.createFont();
        fuente.setBold(true);
        fuente.setFontHeight(16);
        estilo.setFont(fuente);
        
        Cell celda = fila.createCell(0);
        celda.setCellValue("Id Venta");
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(1);
        celda.setCellValue("Fecha y Hora");
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(2);
        celda.setCellValue("Total Monto");
        celda.setCellStyle(estilo);
    }
    
    private void escribirDatosDeLaTabla() {
        int numeroFilas= 1;
        
        CellStyle estilo = venta.createCellStyle();
        XSSFFont fuente = venta.createFont();
        fuente.setFontHeight(14);
        estilo.setFont(fuente);
        
        for(Venta ven : listaVentas) {
            Row fila = hoja.createRow(numeroFilas++);
            
            Cell celda = fila.createCell(0);
            celda.setCellValue(ven.getId());
            hoja.autoSizeColumn(0);
            celda.setCellStyle(estilo);
            
            celda = fila.createCell(1);
            celda.setCellValue(ven.getFechaYHora());
            hoja.autoSizeColumn(0);
            celda.setCellStyle(estilo);
            
            celda = fila.createCell(2);
            celda.setCellValue(ven.getTotal());
            hoja.autoSizeColumn(0);
            celda.setCellStyle(estilo);
        }
    }
    
    public void exportar(HttpServletResponse response) throws IOException{
        escribirCabeceraDeTabla();
        escribirDatosDeLaTabla();
        
        ServletOutputStream outPutStream = response.getOutputStream();
        venta.write(outPutStream);
        
        venta.close();
        outPutStream.close();
    }
}
