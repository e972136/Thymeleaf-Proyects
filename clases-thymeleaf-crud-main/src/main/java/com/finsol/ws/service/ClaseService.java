/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finsol.ws.service;


import com.finsol.ws.entity.Clase;
import com.finsol.ws.repository.ClaseRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ds010102
 */

@Service
@RequiredArgsConstructor
public class ClaseService {
    private final ClaseRepository repository;
    
    @Transactional(readOnly = true)
    public List<Clase> obtenerClases(){
        return repository.findAll();
    }
    
    @Transactional
    public Clase guardarClase(Clase nuevo){
     
        return repository.save(nuevo);
    }
    
//    public Clase obtenerClase(Integer id) throws ClaseNoEncontrada{
//        Clase clase = repository.findById(id).orElse(null);
//        List<String> tmp = List.of("a","b","c");
//        if(clase!=null){
//            return clase;
//        }else{
//            throw new ClaseNoEncontrada("Usuario "+id+" no existe",tmp);
//        }
//         
//    }
    
    @Transactional(readOnly = true)
    public Clase obtenerClase(Integer id) {
        return repository.findById(id).orElse(null); 
    }
    
    @Transactional
    public Clase actualizarClase(Clase clase){
        return repository.save(clase);
    }
    
    @Transactional
    public void eliminarClase(Integer id){
        repository.deleteById(id);
    }
}
