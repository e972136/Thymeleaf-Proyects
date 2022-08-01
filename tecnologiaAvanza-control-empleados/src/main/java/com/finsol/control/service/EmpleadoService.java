package com.finsol.control.service;

import com.finsol.control.entity.Empleado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmpleadoService {
    List<Empleado> findAll();

    Page<Empleado> findAll(Pageable pageable);

    Empleado save(Empleado empleado);

    Empleado findEmpleado(Integer id);

    Empleado delete(Integer id);
}
