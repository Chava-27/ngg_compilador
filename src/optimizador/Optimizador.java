/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package optimizador;

import ast.*;

/**
 *
 * @author Chava27
 */
public class Optimizador {
    
    public NodoPrograma optimizar(NodoPrograma programa) {

        NodoPrograma programaOptimizado =
                new NodoPrograma();

        for (Nodo nodo : programa.getInstrucciones()) {

            Nodo optimizado =
                    optimizarNodo(nodo);

            programaOptimizado
                    .agregarInstruccion(optimizado);
        }

        return programaOptimizado;
    }

    private Nodo optimizarNodo(Nodo nodo) {

        // DECLARACIÓN
        if (nodo instanceof NodoDeclaracion declaracion) {

            Nodo valorOptimizado =
                    optimizarNodo(
                            declaracion.getValor()
                    );

            return new NodoDeclaracion(
                    declaracion.getTipoDato(),
                    declaracion.getNombreVariable(),
                    valorOptimizado
            );
        }

        // IMPRESIÓN
        if (nodo instanceof NodoImpresion impresion) {

            Nodo expresionOptimizada =
                    optimizarNodo(
                            impresion.getExpresion()
                    );

            return new NodoImpresion(
                    expresionOptimizada
            );
        }

        // OPERACIONES
        if (nodo instanceof NodoOperacion operacion) {

            Nodo izquierdo =
                    optimizarNodo(
                            operacion.getIzquierdo()
                    );

            Nodo derecho =
                    optimizarNodo(
                            operacion.getDerecho()
                    );

            return optimizarOperacion(
                    operacion.getOperador(),
                    izquierdo,
                    derecho
            );
        }

        return nodo;
    }

    private Nodo optimizarOperacion(
            String operador,
            Nodo izquierdo,
            Nodo derecho
    ) {

        // =========================
        // CONSTANT FOLDING
        // =========================

        if (
                izquierdo instanceof NodoLiteral literalIzquierdo
                && derecho instanceof NodoLiteral literalDerecho
        ) {

            Object valorIzquierdo =
                    literalIzquierdo.getValor();

            Object valorDerecho =
                    literalDerecho.getValor();

            // ENTEROS
            if (
                    valorIzquierdo instanceof Integer
                    && valorDerecho instanceof Integer
            ) {

                int a = (Integer) valorIzquierdo;
                int b = (Integer) valorDerecho;

                return switch (operador) {

                    case "+" -> {
                        System.out.println(
                                "OPTIMIZACIÓN: "
                                + a + " + " + b
                                + " -> "
                                + (a + b)
                        );

                        yield new NodoLiteral(a + b);
                    }

                    case "-" -> {
                        System.out.println(
                                "OPTIMIZACIÓN: "
                                + a + " - " + b
                                + " -> "
                                + (a - b)
                        );

                        yield new NodoLiteral(a - b);
                    }

                    case "*" -> {
                        System.out.println(
                                "OPTIMIZACIÓN: "
                                + a + " * " + b
                                + " -> "
                                + (a * b)
                        );

                        yield new NodoLiteral(a * b);
                    }

                    case "/" -> {
                        System.out.println(
                                "OPTIMIZACIÓN: "
                                + a + " / " + b
                                + " -> "
                                + (a / b)
                        );

                        yield new NodoLiteral(a / b);
                    }

                    default -> new NodoOperacion(
                            operador,
                            izquierdo,
                            derecho
                    );
                };
            }

            // CONCATENACIÓN TEXTO
            if (
                    operador.equals("+")
                    && valorIzquierdo instanceof String
                    && valorDerecho instanceof String
            ) {

                String resultado =
                        valorIzquierdo.toString()
                        + valorDerecho.toString();

                System.out.println(
                        "OPTIMIZACIÓN: "
                        + "\"" + valorIzquierdo + "\""
                        + " + "
                        + "\"" + valorDerecho + "\""
                        + " -> "
                        + "\"" + resultado + "\""
                );

                return new NodoLiteral(resultado);
            }
        }

        // =========================
        // x + 0
        // =========================

        if (
                operador.equals("+")
                && derecho instanceof NodoLiteral literal
        ) {

            if (
                    literal.getValor() instanceof Integer valor
                    && valor == 0
            ) {

                System.out.println(
                        "OPTIMIZACIÓN: x + 0 -> x"
                );

                return izquierdo;
            }
        }

        // =========================
        // x * 1
        // =========================

        if (
                operador.equals("*")
                && derecho instanceof NodoLiteral literal
        ) {

            if (
                    literal.getValor() instanceof Integer valor
                    && valor == 1
            ) {

                System.out.println(
                        "OPTIMIZACIÓN: x * 1 -> x"
                );

                return izquierdo;
            }
        }

        return new NodoOperacion(
                operador,
                izquierdo,
                derecho
        );
    }
    
}
