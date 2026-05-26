/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package semantico;

import ast.*;

/**
 *
 * @author Chava27
 */
public class AnalizadorSemantico {
    
    private final TablaSimbolos tablaSimbolos;

    public AnalizadorSemantico() {
        tablaSimbolos = new TablaSimbolos();
    }

    public void analizar(NodoPrograma programa) {

        for (Nodo nodo : programa.getInstrucciones()) {

            if (nodo instanceof NodoDeclaracion declaracion) {
                analizarDeclaracion(declaracion);
            }

            else if (nodo instanceof NodoImpresion impresion) {
                obtenerTipoNodo(impresion.getExpresion());
            }
        }

        System.out.println();
        System.out.println("ANÁLISIS SEMÁNTICO CORRECTO");

        tablaSimbolos.imprimir();
    }

    private void analizarDeclaracion(
            NodoDeclaracion declaracion
    ) {

        String nombre = declaracion.getNombreVariable();
        String tipo = declaracion.getTipoDato();

        if (tablaSimbolos.existe(nombre)) {

            error(
                    "La variable ya existe: "
                    + nombre
            );
        }

        String tipoValor =
                obtenerTipoNodo(declaracion.getValor());

        if (!tipoCompatible(tipo, tipoValor)) {

            error(
                    "Tipos incompatibles: "
                    + tipo
                    + " y "
                    + tipoValor
            );
        }

        tablaSimbolos.registrar(nombre, tipo);

        System.out.println(
                "Variable registrada -> "
                + nombre
                + " : "
                + tipo
        );
    }

    private String obtenerTipoNodo(Nodo nodo) {

        if (nodo instanceof NodoLiteral literal) {

            Object valor = literal.getValor();

            if (valor instanceof Integer) {
                return "entero";
            }

            if (valor instanceof Double) {
                return "decimal";
            }

            if (valor instanceof String) {
                return "texto";
            }

            if (valor instanceof Boolean) {
                return "booleano";
            }
        }

        if (nodo instanceof NodoVariable variable) {

            String nombre = variable.getNombre();

            if (!tablaSimbolos.existe(nombre)) {

                error(
                        "Variable no declarada: "
                        + nombre
                );
            }

            return tablaSimbolos.obtenerTipo(nombre);
        }

        if (nodo instanceof NodoOperacion operacion) {

            String tipoIzquierdo =
                    obtenerTipoNodo(
                            operacion.getIzquierdo()
                    );

            String tipoDerecho =
                    obtenerTipoNodo(
                            operacion.getDerecho()
                    );

            String operador =
                    operacion.getOperador();

            return validarOperacion(
                    tipoIzquierdo,
                    tipoDerecho,
                    operador
            );
        }

        return "desconocido";
    }

    private String validarOperacion(
            String izquierdo,
            String derecho,
            String operador
    ) {

        // CONCATENACIÓN
        if (operador.equals("+")) {

            if (
                    izquierdo.equals("texto")
                    || derecho.equals("texto")
            ) {

                return "texto";
            }
        }

        // NUMÉRICOS
        if (esNumero(izquierdo)
                && esNumero(derecho)) {

            if (
                    izquierdo.equals("decimal")
                    || derecho.equals("decimal")
            ) {

                return "decimal";
            }

            return "entero";
        }

        error(
                "Operación inválida entre "
                + izquierdo
                + " y "
                + derecho
        );

        return "desconocido";
    }

    private boolean esNumero(String tipo) {

        return tipo.equals("entero")
                || tipo.equals("decimal");
    }

    private boolean tipoCompatible(
            String esperado,
            String recibido
    ) {

        if (esperado.equals(recibido)) {
            return true;
        }

        // entero ← decimal NO
        // decimal ← entero SÍ

        if (
                esperado.equals("decimal")
                && recibido.equals("entero")
        ) {

            return true;
        }

        return false;
    }

    private void error(String mensaje) {

        System.out.println();
        System.out.println(
                "ERROR SEMÁNTICO -> "
                + mensaje
        );

        System.exit(1);
    }
    
}
