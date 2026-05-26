/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package semantico;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Chava27
 */
public class TablaSimbolos {
    
    private final Map<String, Simbolo> simbolos;

    public TablaSimbolos() {
        simbolos = new HashMap<>();
    }

    public void registrar(String nombre, String tipo) {

        simbolos.put(
                nombre,
                new Simbolo(nombre, tipo)
        );
    }

    public boolean existe(String nombre) {
        return simbolos.containsKey(nombre);
    }

    public String obtenerTipo(String nombre) {

        Simbolo simbolo = simbolos.get(nombre);

        if (simbolo == null) {
            return null;
        }

        return simbolo.getTipo();
    }

    public void imprimir() {

        System.out.println();
        System.out.println("TABLA DE SÍMBOLOS");
        System.out.println("-------------------------");

        for (Simbolo simbolo : simbolos.values()) {

            System.out.println(
                    simbolo.getNombre()
                    + " -> "
                    + simbolo.getTipo()
            );
        }
    }
    
}
