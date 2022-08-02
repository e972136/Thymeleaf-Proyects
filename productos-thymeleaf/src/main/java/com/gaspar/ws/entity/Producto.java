package com.gaspar.ws.entity;

import lombok.Data;
import org.springframework.cglib.core.internal.LoadingCache;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
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

    @Min(value = 0)
    private BigDecimal precioUnidad;

    @Min(value = 0)
    private Integer existencia;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime fechaCreado;

    @PrePersist
    public void prePersist() {
        fechaCreado= LocalDateTime.now();
    }
}
