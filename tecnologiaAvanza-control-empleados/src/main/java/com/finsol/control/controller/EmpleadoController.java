package com.finsol.control.controller;


import com.finsol.control.entity.Empleado;
import com.finsol.control.paginacion.PageRender;
import com.finsol.control.reportes.PdfExporterEmpleado;
import com.finsol.control.reportes.XlsExporterEmpleado;
import com.finsol.control.service.EmpleadoService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoService service;

    @GetMapping("/ver/{id}")
    public String verDetalleEmpleado(Empleado empleado, Model modelo ){
        empleado = service.findEmpleado(empleado.getId());
        modelo.addAttribute("empleado", empleado);
        return "ver_empleado";
    }

    @GetMapping("/form")
    public String mostrarFormulario(Empleado empleado){
        return "ver_empleado";
    }

    @PostMapping("/guardar_empleado")
    public String guardarEmpleado(@Valid Empleado empleado, BindingResult error){
//    public String guardarEmpleado(@Valid Empleado empleado, Errors error){
        if(error.hasErrors()){
            System.out.println("hay error" + error.toString());
            return "ver_empleado";
        }
        Empleado save = service.save(empleado);
        return "redirect:/listar";
    }

    @GetMapping({"/listar","/"})
    public String listarEmpleados(@RequestParam(name = "page",defaultValue = "0") int page, Model modelo){
        Pageable pageRequest = PageRequest.of(page,2, Sort.by("id").descending());
        Page<Empleado> empleados = service.findAll(pageRequest);
        PageRender<Empleado> pageRender = new PageRender<>("/listar",empleados);

        modelo.addAttribute("titulo","listado empleados");
        modelo.addAttribute("empleados",empleados);
        modelo.addAttribute("page",pageRender);

        return "listar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable(value="id") Integer id){
        Empleado empleado = service.findEmpleado(id);
        if(empleado==null){
            return "redirect:/listar";
        }
        Empleado delete = service.delete(id);
        return "redirect:/listar";
    }

    @GetMapping("/exportar_pdf")
    public void exportar_pdf(HttpServletResponse response){
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormatter.format(new Date());
        String cabecera = "Content-disposition";
        String valor = "attachement; filename=Empleados_"+fechaActual+".pdf";
        response.setHeader(cabecera,valor);

        List<Empleado> all = service.findAll();

        PdfExporterEmpleado exporter = new PdfExporterEmpleado(all);

        try{
            exporter.exportar(response);
        }catch (Exception e){
            System.out.println(e+"");
        }
    }

    @GetMapping("/exportar_xls")
    public void exportar_xls(HttpServletResponse response){
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormatter.format(new Date());
        String cabecera = "Content-disposition";
        String valor = "attachement; filename=Empleados_"+fechaActual+".xlsx";
        response.setHeader(cabecera,valor);

        List<Empleado> all = service.findAll();

        XlsExporterEmpleado exporter = new XlsExporterEmpleado(all);

        try{
            exporter.exportar(response);
        }catch (Exception e){
            System.out.println(e+"");
        }
    }
}
