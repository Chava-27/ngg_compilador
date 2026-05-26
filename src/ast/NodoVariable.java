/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ast;

/**
 *
 * @author Chava27
 */
public class NodoVariable extends Nodo {
    
    private final String nombre;

    public NodoVariable(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void imprimir(String prefijo) {

        System.out.println(
                prefijo
                + "NodoVariable -> "
                + nombre
        );
    }
    
}
