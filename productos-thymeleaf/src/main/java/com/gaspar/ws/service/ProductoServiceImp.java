package com.gaspar.ws.service;

import com.gaspar.ws.entity.Producto;
import com.gaspar.ws.repository.ProductoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImp implements ProductoService{

    private final ProductoRepository productoRepository;

    public ProductoServiceImp(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public Page<Producto> findAll(Pageable page) {
        return productoRepository.findAll(page);
    }

    @Override
    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto obtenerProducto(Integer id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteProducto(Integer id) {
        productoRepository.deleteById(id);
    }
}
