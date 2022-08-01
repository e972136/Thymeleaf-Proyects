package com.finsol.control.paginacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageRender<T> {
    String url;
    Page<T> page;
    int totalPaginas;
    int numElementosPagina;
    int paginaActual;
    List<PageItem> paginas;

    public PageRender(String url, Page<T> page){
        this.url=url;
        this.page = page;
        this.paginas = new ArrayList<>();
        numElementosPagina = 5;
        totalPaginas = page.getTotalPages();
        paginaActual = page.getNumber()+1;

        int desde,hasta;
        if(totalPaginas<=numElementosPagina){
            desde=1;
            hasta = totalPaginas;
        }else{
            if(paginaActual <= numElementosPagina/2){
                desde =1;
                hasta = numElementosPagina;
            }else if(paginaActual>=totalPaginas - numElementosPagina/2){
                desde = totalPaginas - numElementosPagina + 1;
                hasta = numElementosPagina;
            }else{
                desde = paginaActual - numElementosPagina/2;
                hasta = numElementosPagina;
            }
        }
        for(int i=0;i<hasta;i++){
            paginas.add(new PageItem(desde+i,paginaActual==(desde+i)));
        }

    }

    public boolean isLast(){
        return page.isLast();
    }

    public boolean isFirst(){ return page.isFirst();}

    public boolean hasNext()
    {
        return page.hasNext();
    }

    public boolean hasPrevius(){
        return page.hasPrevious();
    }
}
