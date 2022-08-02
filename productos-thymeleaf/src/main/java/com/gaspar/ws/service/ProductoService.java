package com.gaspar.ws.service;

import com.gaspar.ws.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductoService {

    Page<Producto> findAll(Pageable page);

    Producto saveProducto(Producto producto);

    Producto obtenerProducto(Integer id);
}
