package com.gaspar.ws.repository;

import com.gaspar.ws.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto,Integer> {
    Page<Producto> findBydescripcionContaining(String descripcion, Pageable page);
}
