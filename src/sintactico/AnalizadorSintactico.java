package sintactico;

import ast.*;
import java.util.List;
import lexico.TipoToken;
import lexico.Token;

public class AnalizadorSintactico {

    private final List<Token> tokens;
    private int posicion;

    public AnalizadorSintactico(List<Token> tokens) {
        this.tokens = tokens;
        this.posicion = 0;
    }

    public NodoPrograma parsear() {

        NodoPrograma programa = new NodoPrograma();

        while (!verificar(TipoToken.FIN_ARCHIVO)) {

            Nodo instruccion = analizarInstruccion();

            if (instruccion != null) {
                programa.agregarInstruccion(instruccion);
            }
        }

        return programa;
    }

    private Nodo analizarInstruccion() {

        if (coincide(TipoToken.VAR)) {
            return analizarDeclaracion();
        }

        if (coincide(TipoToken.IMPRIMIR)) {
            return analizarImpresion();
        }

        error("Instrucción no válida");
        return null;
    }

    private Nodo analizarDeclaracion() {

        Token tipo = consumirTipoDato();

        Token nombre =
                consumir(TipoToken.IDENTIFICADOR,
                        "Se esperaba nombre de variable");

        consumir(TipoToken.IGUAL,
                "Se esperaba '='");

        Nodo valor = analizarExpresion();

        consumir(TipoToken.PUNTO_COMA,
                "Se esperaba ';'");

        return new NodoDeclaracion(tipo.getLexema(), nombre.getLexema(), valor);
    }

    private Nodo analizarImpresion() {

        consumir(
                TipoToken.PARENTESIS_IZQUIERDO,
                "Se esperaba '('"
        );

        Nodo expresion = analizarExpresion();

        consumir(
                TipoToken.PARENTESIS_DERECHO,
                "Se esperaba ')'"
        );

        consumir(
                TipoToken.PUNTO_COMA,
                "Se esperaba ';'"
        );

        return new NodoImpresion(expresion);
    }

    private Nodo analizarExpresion() {

        Nodo izquierdo = analizarValor();

        while (
                coincide(TipoToken.MAS)
                || coincide(TipoToken.MENOS)
                || coincide(TipoToken.MULTIPLICACION)
                || coincide(TipoToken.DIVISION)
        ) {

            Token operador = anterior();

            Nodo derecho = analizarValor();

            izquierdo = new NodoOperacion(
                    operador.getLexema(),
                    izquierdo,
                    derecho
            );
        }

        return izquierdo;
    }

    private Nodo analizarValor() {

        if (coincide(TipoToken.NUMERO)) {
            return new NodoLiteral(
                    Integer.parseInt(anterior().getLexema())
            );
        }

        if (coincide(TipoToken.DECIMAL)) {
            return new NodoLiteral(
                    Double.parseDouble(anterior().getLexema())
            );
        }

        if (coincide(TipoToken.TEXTO)) {
            return new NodoLiteral(
                    anterior().getLexema()
            );
        }

        if (coincide(TipoToken.BOOLEANO)) {
            return new NodoLiteral(
                    Boolean.parseBoolean(anterior().getLexema())
            );
        }

        if (coincide(TipoToken.IDENTIFICADOR)) {
            return new NodoVariable(
                    anterior().getLexema()
            );
        }

        error("Valor no válido");
        return null;
    }

    private Token consumirTipoDato() {

        if (
                coincide(TipoToken.TIPO_ENTERO)
                || coincide(TipoToken.TIPO_DECIMAL)
                || coincide(TipoToken.TIPO_TEXTO)
                || coincide(TipoToken.TIPO_BOOLEANO)
        ) {

            return anterior();
        }

        error("Se esperaba tipo de dato");
        return null;
    }

    private boolean coincide(TipoToken tipo) {

        if (verificar(tipo)) {
            avanzar();
            return true;
        }

        return false;
    }

    private Token consumir(TipoToken tipo, String mensaje) {

        if (verificar(tipo)) {
            return avanzar();
        }

        error(mensaje);
        return null;
    }

    private boolean verificar(TipoToken tipo) {

        // if (haTerminado()) { return false; }

        return actual().getTipo() == tipo;
    }

    private Token avanzar() {

        if (!haTerminado()) {
            posicion++;
        }

        return anterior();
    }

    private boolean haTerminado() {
        return actual().getTipo() == TipoToken.FIN_ARCHIVO;
    }

    private Token actual() {
        return tokens.get(posicion);
    }

    private Token anterior() {
        return tokens.get(posicion - 1);
    }

    private void error(String mensaje) {

        System.out.println(
                "ERROR SINTÁCTICO -> "
                + mensaje
                + " en línea "
                + actual().getLinea()
        );

        System.exit(1);
    }
}