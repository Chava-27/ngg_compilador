/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ast;

/**
 *
 * @author Chava27
 */
public class NodoDeclaracion extends Nodo{
 
    private final String tipoDato;
    private final String nombreVariable;
    private final Nodo valor;

    public NodoDeclaracion(String tipoDato, String nombreVariable, Nodo valor) {
        this.tipoDato = tipoDato;
        this.nombreVariable = nombreVariable;
        this.valor = valor;
    }

    public String getTipoDato() {
        return tipoDato;
    }

    public String getNombreVariable() {
        return nombreVariable;
    }

    public Nodo getValor() {
        return valor;
    }

    @Override
    public void imprimir(String prefijo) {

        System.out.println(
                prefijo
                + "NodoDeclaracion -> "
                + tipoDato
                + " "
                + nombreVariable
        );

        valor.imprimir(prefijo + "   ");
    }
    
}
