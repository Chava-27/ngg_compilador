/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ast;

/**
 *
 * @author Chava27
 */
public class NodoImpresion extends Nodo {
    
    private final Nodo expresion;

    public NodoImpresion(Nodo expresion) {
        this.expresion = expresion;
    }

    public Nodo getExpresion() {
        return expresion;
    }

    @Override
    public void imprimir(String prefijo) {

        System.out.println(prefijo + "NodoImpresion");

        expresion.imprimir(prefijo + "   ");
    }
    
}
