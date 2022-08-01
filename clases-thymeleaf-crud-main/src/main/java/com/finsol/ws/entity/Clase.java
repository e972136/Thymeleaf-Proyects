/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finsol.ws.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author ds010102
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class Clase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)        
    Integer clase_Id;
    
    @NotEmpty(message = "Falta nombre")
    String clase_nombre;
    
    @Email
    @NotEmpty(message = "Falta correo")
    String clase_email;
    
    @Min(1)
    @Max(5)
    Integer clase_unidades;
}
