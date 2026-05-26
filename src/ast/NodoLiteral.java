/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ast;

/**
 *
 * @author Chava27
 */
public class NodoLiteral extends Nodo {
    
    private final Object valor;

    public NodoLiteral(Object valor) {
        this.valor = valor;
    }

    public Object getValor() {
        return valor;
    }

    @Override
    public void imprimir(String prefijo) {

        System.out.println(
                prefijo
                + "NodoLiteral -> "
                + valor
        );
    }
    
}
