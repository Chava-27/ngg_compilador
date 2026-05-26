/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lexico;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Chava27
 */
public class AnalizadorLexico {
    
    private final String codigoFuente;
    private final List<Token> tokens;

    private int posicion;
    private int linea;

    public AnalizadorLexico(String codigoFuente) {
        this.codigoFuente = codigoFuente;
        this.tokens = new ArrayList<>();

        this.posicion = 0;
        this.linea = 1;
    }

    public List<Token> analizar() {

        while (!haTerminado()) {

            char caracterActual = obtenerCaracterActual();

            // Espacios
            if (Character.isWhitespace(caracterActual)) {

                if (caracterActual == '\n') {
                    linea++;
                }

                avanzar();
                continue;
            }

            // Identificadores o palabras reservadas
            if (Character.isLetter(caracterActual)) {
                analizarPalabra();
                continue;
            }

            // Números
            if (Character.isDigit(caracterActual)) {
                analizarNumero();
                continue;
            }

            // Textos
            if (caracterActual == '"') {
                analizarTexto();
                continue;
            }

            switch (caracterActual) {

                case '+':
                    agregarToken(TipoToken.MAS, "+");
                    break;

                case '-':
                    agregarToken(TipoToken.MENOS, "-");
                    break;

                case '*':
                    agregarToken(TipoToken.MULTIPLICACION, "*");
                    break;

                case '/':
                    agregarToken(TipoToken.DIVISION, "/");
                    break;

                case '=':
                    agregarToken(TipoToken.IGUAL, "=");
                    break;

                case ';':
                    agregarToken(TipoToken.PUNTO_COMA, ";");
                    break;

                case '(':
                    agregarToken(TipoToken.PARENTESIS_IZQUIERDO, "(");
                    break;

                case ')':
                    agregarToken(TipoToken.PARENTESIS_DERECHO, ")");
                    break;

                default:
                    System.out.println(
                            "ERROR LÉXICO -> Carácter no válido: "
                            + caracterActual
                            + " en línea "
                            + linea
                    );
                    break;
            }

            avanzar();
        }

        tokens.add(new Token(TipoToken.FIN_ARCHIVO, "EOF", linea));

        return tokens;
    }

    private void analizarPalabra() {

        StringBuilder palabra = new StringBuilder();

        while (!haTerminado()
                && (Character.isLetterOrDigit(obtenerCaracterActual())
                || obtenerCaracterActual() == '_')) {

            palabra.append(obtenerCaracterActual());
            avanzar();
        }

        String lexema = palabra.toString();

        switch (lexema) {

            case "var":
                agregarToken(TipoToken.VAR, lexema);
                break;

            case "imprimir":
                agregarToken(TipoToken.IMPRIMIR, lexema);
                break;

            case "entero":
                agregarToken(TipoToken.TIPO_ENTERO, lexema);
                break;

            case "decimal":
                agregarToken(TipoToken.TIPO_DECIMAL, lexema);
                break;

            case "texto":
                agregarToken(TipoToken.TIPO_TEXTO, lexema);
                break;

            case "booleano":
                agregarToken(TipoToken.TIPO_BOOLEANO, lexema);
                break;

            case "verdadero":
            case "falso":
                agregarToken(TipoToken.BOOLEANO, lexema);
                break;

            default:
                agregarToken(TipoToken.IDENTIFICADOR, lexema);
                break;
        }
    }

    private void analizarNumero() {

        StringBuilder numero = new StringBuilder();
        boolean esDecimal = false;

        while (!haTerminado()
                && (Character.isDigit(obtenerCaracterActual())
                || obtenerCaracterActual() == '.')) {

            if (obtenerCaracterActual() == '.') {

                if (esDecimal) {
                    break;
                }

                esDecimal = true;
            }

            numero.append(obtenerCaracterActual());
            avanzar();
        }

        if (esDecimal) {
            agregarToken(TipoToken.DECIMAL, numero.toString());
        } else {
            agregarToken(TipoToken.NUMERO, numero.toString());
        }
    }

    private void analizarTexto() {

        avanzar();

        StringBuilder texto = new StringBuilder();

        while (!haTerminado() && obtenerCaracterActual() != '"') {

            texto.append(obtenerCaracterActual());
            avanzar();
        }

        avanzar();

        agregarToken(TipoToken.TEXTO, texto.toString());
    }

    private void agregarToken(TipoToken tipo, String lexema) {
        tokens.add(new Token(tipo, lexema, linea));
    }

    private char obtenerCaracterActual() {
        return codigoFuente.charAt(posicion);
    }

    private void avanzar() {
        posicion++;
    }

    private boolean haTerminado() {
        return posicion >= codigoFuente.length();
    }
    
}
