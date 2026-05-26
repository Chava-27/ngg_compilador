/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ast;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Chava27
 */
public class NodoPrograma extends Nodo {
    
    private final List<Nodo> instrucciones;

    public NodoPrograma() {
        instrucciones = new ArrayList<>();
    }

    public void agregarInstruccion(Nodo nodo) {
        instrucciones.add(nodo);
    }

    public List<Nodo> getInstrucciones() {
        return instrucciones;
    }

    @Override
    public void imprimir(String prefijo) {

        System.out.println(prefijo + "NodoPrograma");

        for (Nodo nodo : instrucciones) {
            nodo.imprimir(prefijo + "   ");
        }
    }
    
}
