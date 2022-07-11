/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.app.ventas.util.reportes;

import com.web.app.ventas.model.Cliente;
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

public class ClienteExporterEXCEL {

    private XSSFWorkbook cliente;
    private XSSFSheet hoja;

    private List<Cliente> listaClientes;

    public ClienteExporterEXCEL(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
        cliente = new XSSFWorkbook();
        hoja = cliente.createSheet("Clientes");
    }

    private void escribirCabeceraDeTabla() {
        Row fila = hoja.createRow(0);

        CellStyle estilo = cliente.createCellStyle();
        XSSFFont fuente = cliente.createFont();
        fuente.setBold(true);
        fuente.setFontHeight(16);
        estilo.setFont(fuente);

        Cell celda = fila.createCell(0);
        celda.setCellValue("ID");
        celda.setCellStyle(estilo);

        celda = fila.createCell(1);
        celda.setCellValue("Nombre");
        celda.setCellStyle(estilo);

        celda = fila.createCell(2);
        celda.setCellValue("Apellido");
        celda.setCellStyle(estilo);

        celda = fila.createCell(3);
        celda.setCellValue("DNI");
        celda.setCellStyle(estilo);

        celda = fila.createCell(4);
        celda.setCellValue("Telefono");
        celda.setCellStyle(estilo);

        celda = fila.createCell(5);
        celda.setCellValue("Correo");
        celda.setCellStyle(estilo);
    }
    
    private void escribirDatosDeLaTabla() {
        int numeroFilas = 1;
        
        CellStyle estilo = cliente.createCellStyle();
        XSSFFont fuente = cliente.createFont();
        fuente.setFontHeight(14);
        estilo.setFont(fuente);
        
        for(Cliente cli : listaClientes) {
            Row fila = hoja.createRow(numeroFilas++);
            
            Cell celda = fila.createCell(0);
            celda.setCellValue(cli.getIdcliente());
            hoja.autoSizeColumn(0);
            celda.setCellStyle(estilo);
            
            celda = fila.createCell(1);
            celda.setCellValue(cli.getNombres());
            hoja.autoSizeColumn(1);
            celda.setCellStyle(estilo);
            
            celda = fila.createCell(2);
            celda.setCellValue(cli.getApellidos());
            hoja.autoSizeColumn(2);
            celda.setCellStyle(estilo);
            
            celda = fila.createCell(3);
            celda.setCellValue(cli.getDni());
            hoja.autoSizeColumn(3);
            celda.setCellStyle(estilo);
            
            celda = fila.createCell(4);
            celda.setCellValue(cli.getTelefono());
            hoja.autoSizeColumn(4);
            celda.setCellStyle(estilo);
            
            celda = fila.createCell(5);
            celda.setCellValue(cli.getCorreo());
            hoja.autoSizeColumn(5);
            celda.setCellStyle(estilo);
        }
    }
    
    public void exportar(HttpServletResponse response) throws IOException{
        escribirCabeceraDeTabla();
        escribirDatosDeLaTabla();
        
        ServletOutputStream outPutStream = response.getOutputStream();
        cliente.write(outPutStream);
        
        cliente.close();
        outPutStream.close();
    }
}
