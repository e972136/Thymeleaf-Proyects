package com.finsol.control.service;

import com.finsol.control.entity.Empleado;
import com.finsol.control.repository.EmpleadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpleadoServiceImp implements EmpleadoService{

    private final EmpleadoRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Empleado> findAll() {
        return (List<Empleado>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Empleado> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional
    public Empleado save(Empleado empleado) {
        return repository.save(empleado);
    }

    @Override
    @Transactional(readOnly = true)
    public Empleado findEmpleado(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Empleado delete(Integer id) {
        Empleado db = repository.findById(id).orElse(null);
        if(db==null)
        {
            return null;
        }
        repository.delete(db);
        return db;
    }
}
