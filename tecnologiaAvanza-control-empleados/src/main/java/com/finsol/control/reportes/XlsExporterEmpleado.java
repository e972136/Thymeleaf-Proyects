package com.finsol.control.reportes;

import com.finsol.control.entity.Empleado;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class XlsExporterEmpleado {

    XSSFWorkbook libro;
    XSSFSheet hoja;

    private List<Empleado> listaEmpleados;

    public XlsExporterEmpleado(List<Empleado> listaEmpleados) {
        this.libro = new XSSFWorkbook();
        this.hoja = libro.createSheet("Empleados");
        this.listaEmpleados = listaEmpleados;
    }

    private void escribirCabecera(){
        Row row = hoja.createRow(0);
        CellStyle style = libro.createCellStyle();
        XSSFFont font = libro.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        Cell celda = row.createCell(0);
        celda.setCellValue("ID");
        celda.setCellStyle(style);

         celda = row.createCell(1);
        celda.setCellValue("nombre");
        celda.setCellStyle(style);

         celda = row.createCell(2);
        celda.setCellValue("email");
        celda.setCellStyle(style);

         celda = row.createCell(3);
        celda.setCellValue("telefono");
        celda.setCellStyle(style);

         celda = row.createCell(4);
        celda.setCellValue("genero");
        celda.setCellStyle(style);

         celda = row.createCell(5);
        celda.setCellValue("salario");
        celda.setCellStyle(style);

         celda = row.createCell(6);
        celda.setCellValue("fechaCreado");
        celda.setCellStyle(style);
    }

    private void escribirTabla(){
        CellStyle style = libro.createCellStyle();
        XSSFFont font = libro.createFont();
        font.setBold(false);
        font.setFontHeight(12);
        font.setFontName("courier");
        style.setFont(font);

        int numeroFilas = 1;
        for(Empleado e: listaEmpleados){
            int columna=0;
            Row fila = hoja.createRow(numeroFilas++);
            Cell celda = fila.createCell(columna++);
            celda.setCellValue(e.getId());
            celda.setCellStyle(style);

             celda = fila.createCell(columna++);
            celda.setCellValue(e.getNombre());
            celda.setCellStyle(style);

             celda = fila.createCell(columna++);
            celda.setCellValue(e.getEmail());
            celda.setCellStyle(style);

             celda = fila.createCell(columna++);
            celda.setCellValue(e.getTelefono());
            celda.setCellStyle(style);

             celda = fila.createCell(columna++);
            celda.setCellValue(e.getGenero());
            celda.setCellStyle(style);

             celda = fila.createCell(columna++);
            celda.setCellValue(e.getSalarioStr());
            celda.setCellStyle(style);

            celda = fila.createCell(columna++);
            celda.setCellValue(e.getFechaCreado().toString());
            celda.setCellStyle(style);

            for(int i=0;i<8;i++){
                hoja.autoSizeColumn(i);
            }

        };
    }

    public void exportar(HttpServletResponse response) throws IOException {
        escribirCabecera();
        escribirTabla();

        ServletOutputStream outputStream = response.getOutputStream();

        libro.write(outputStream);

        libro.close();

        outputStream.close();

    }
}
