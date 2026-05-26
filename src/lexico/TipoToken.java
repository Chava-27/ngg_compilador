/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lexico;

/**
 *
 * @author Chava27
 */
public enum TipoToken {
 
    // Palabras reservadas
    VAR,
    IMPRIMIR,
    
    // Tipos de datos
    TIPO_ENTERO,
    TIPO_DECIMAL,
    TIPO_TEXTO,
    TIPO_BOOLEANO,
    
    // Identificadores y valores
    IDENTIFICADOR,
    NUMERO,
    DECIMAL,
    TEXTO,
    BOOLEANO,
    
    // Operadores
    MAS,
    MENOS,
    MULTIPLICACION,
    DIVISION,
    
    // Símbolos
    IGUAL,
    PUNTO_COMA,
    PARENTESIS_IZQUIERDO,
    PARENTESIS_DERECHO,
    
    // Especial
    FIN_ARCHIVO
    
}
