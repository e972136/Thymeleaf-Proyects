package com.finsol.control.repository;

import com.finsol.control.entity.Empleado;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmpleadoRepository extends PagingAndSortingRepository<Empleado,Integer> {
}
