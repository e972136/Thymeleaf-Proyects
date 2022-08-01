package com.finsol.control.reportes;

import com.finsol.control.entity.Empleado;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

@Data
@AllArgsConstructor
public class PdfExporterEmpleado {

    private List<Empleado> listaEmpleados;

    private void escribirCabeceraTabla(PdfPTable tabla){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.CYAN);
        cell.setPadding(5);

        Font fuente = FontFactory.getFont(FontFactory.COURIER);
        fuente.setColor(Color.white);

        cell.setPhrase(new Phrase("ID",fuente));
        tabla.addCell(cell);

        cell.setPhrase(new Phrase("nombre",fuente));
        tabla.addCell(cell);

        cell.setPhrase(new Phrase("email",fuente));
        tabla.addCell(cell);

        cell.setPhrase(new Phrase("telefono",fuente));
        tabla.addCell(cell);

        cell.setPhrase(new Phrase("genero",fuente));
        tabla.addCell(cell);

        cell.setPhrase(new Phrase("salario",fuente));
        tabla.addCell(cell);

        cell.setPhrase(new Phrase("fechaCreado",fuente));
        tabla.addCell(cell);
    }

    private void escribirDatos(PdfPTable tabla){
        listaEmpleados.forEach(e->{
            tabla.addCell(e.getId().toString());
            tabla.addCell(e.getNombre());
            tabla.addCell(e.getEmail());
            tabla.addCell(e.getTelefono());
            tabla.addCell(e.getGenero());
            tabla.addCell(e.getSalarioStr());
            tabla.addCell(e.getFechaCreado().toString());
        });
    }

    public void exportar(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.LETTER);
        PdfWriter.getInstance(document,response.getOutputStream());
        document.open();
        Font fuente = FontFactory.getFont(FontFactory.COURIER_BOLD);
        fuente.setColor(Color.BLUE);
        fuente.setSize(18);
        Paragraph titulo = new Paragraph("Lista de Empleados",fuente);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(titulo);
        PdfPTable tabla = new PdfPTable(7);
        tabla.setWidthPercentage(100);
        tabla.setSpacingBefore(15);
        float medidas[]={1f,2.3f,6f,2.9f,1f,3.5f,3.2f};
        tabla.setWidths(medidas);
        tabla.setWidthPercentage(110);
        escribirCabeceraTabla(tabla);
        escribirDatos(tabla);

        document.add(tabla);

        document.close();
    }
}
