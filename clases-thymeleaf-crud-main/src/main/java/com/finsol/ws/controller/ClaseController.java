/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finsol.ws.controller;


import com.finsol.ws.entity.Clase;
import com.finsol.ws.service.ClaseService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ds010102
 */
@Controller
@RequiredArgsConstructor
public class ClaseController {
  private final ClaseService service;
    
    @GetMapping({"/listado","/"})
    public String listado(Model modelo){   
        modelo.addAttribute("clases", service.obtenerClases());
        return "listado";
              
    }
    
    @GetMapping("/listado/nuevo")
    public String crearEstudianteFormulario(Clase clase){
//        Clase clase = new Clase();
//        modelo.addAttribute("clase",clase);
        return "crear_clase";
    }
    
    @PostMapping("/guardar_clase")
    public String guardarClase(@Valid Clase clase,Errors error){
        if(error.hasErrors()){
            System.out.println("hay error" + error.toString());
            return "crear_clase";
        }
        service.guardarClase(clase);
        return "redirect:/listado";
    }
    
    @GetMapping("/listado/editar/{clase_Id}")
    public String modificarClaseFormulario(Clase clase, Model modelo){
        
        clase=service.obtenerClase(clase.getClase_Id());
        System.out.println("modificarClaseFormulario Clase:" +clase);
        modelo.addAttribute("clase", clase);
        return "crear_clase";
    }
    
   
    @GetMapping("/listado/{id}")
    public String eliminarClase(@PathVariable Integer id){
        service.eliminarClase(id);
        return "redirect:/listado"; 
    }

    
}
