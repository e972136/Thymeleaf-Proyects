package com.gaspar.ws.entity;

import lombok.Data;
import org.springframework.cglib.core.internal.LoadingCache;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="productos")
@Data
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private String descripcion;

    private BigDecimal precioUnidad;

    private Integer existencia;

    private LocalDateTime fechaCreado;

    @PrePersist
    public void prePersist() {
        fechaCreado= LocalDateTime.now();
    }
}
