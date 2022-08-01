package com.finsol.control.paginacion;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageItem {
    int numero;
    boolean actual;
}
