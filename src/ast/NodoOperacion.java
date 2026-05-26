/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ast;

/**
 *
 * @author Chava27
 */
public class NodoOperacion extends Nodo {
    
    private final String operador;
    private final Nodo izquierdo;
    private final Nodo derecho;

    public NodoOperacion(String operador, Nodo izquierdo, Nodo derecho) {
        this.operador = operador;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }

    public String getOperador() {
        return operador;
    }

    public Nodo getIzquierdo() {
        return izquierdo;
    }

    public Nodo getDerecho() {
        return derecho;
    }

    @Override
    public void imprimir(String prefijo) {

        System.out.println(
                prefijo
                + "NodoOperacion -> "
                + operador
        );

        izquierdo.imprimir(prefijo + "   ");
        derecho.imprimir(prefijo + "   ");
    }
    
}
