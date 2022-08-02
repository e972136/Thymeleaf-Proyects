package com.gaspar.ws.controller;

import com.gaspar.ws.entity.Producto;
import com.gaspar.ws.service.ProductoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping({"/","/listar"})
    String index(@PageableDefault(size = 5) Pageable page,
                 Model model){
        Page<Producto> productos = productoService.findAll(page);
        model.addAttribute("productos",productos);
        return "index";
    }

    @GetMapping("producto/nuevo")
    String formProductoNuevo(Producto producto){
        return "formulario_nuevo";
    }

    @PostMapping("/producto/guardar")
    String saveProductoNuevo(@Valid Producto producto,
                             BindingResult result){
        if(result.hasErrors()){
            System.out.println("hay error" + result.toString());
            return "formulario_nuevo";
        }
        Producto saved=productoService.saveProducto(producto);
        return "redirect:/listar";
    }

    @GetMapping("/producto/editar/{id}")
    String formUpdateProducto(Producto producto,Model model){
        producto = productoService.obtenerProducto(producto.getId());
        model.addAttribute("producto",producto);
        return "formulario_editar";
    }

    @PostMapping("/producto/eliminar/{id}")
    String deleteProducto(Producto producto){
        productoService.deleteProducto(producto.getId());
        return "redirect:/listar";
    }
}
