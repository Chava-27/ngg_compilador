/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import ast.NodoPrograma;
import java.util.List;
import lexico.AnalizadorLexico;
import lexico.Token;
import sintactico.AnalizadorSintactico;
import semantico.AnalizadorSemantico;
import optimizador.Optimizador;
import generadorc.GeneradorC;


/**
 *
 * @author Chava27
 */
public class Main {
    
     public static void main(String[] args) {

        String codigoFuente = """
        
        var entero x = 5 + 10;
        var entero y = x + 20;
        
        imprimir(y);
        
        """;

        // =========================
        // ANÁLISIS LÉXICO
        // =========================

        System.out.println("=================================");
        System.out.println("ANÁLISIS LÉXICO");
        System.out.println("=================================");

        AnalizadorLexico lexico =
                new AnalizadorLexico(codigoFuente);

        List<Token> tokens =
                lexico.analizar();

        for (Token token : tokens) {
            System.out.println(token);
        }

        // =========================
        // ANÁLISIS SINTÁCTICO
        // =========================

        System.out.println();
        System.out.println("=================================");
        System.out.println("ANÁLISIS SINTÁCTICO");
        System.out.println("=================================");

        AnalizadorSintactico sintactico =
                new AnalizadorSintactico(tokens);

        NodoPrograma programa =
                sintactico.parsear();

        programa.imprimir("");
        
        // =========================
        // ANÁLISIS SEMÁNTICO
        // =========================

        System.out.println();
        System.out.println("=================================");
        System.out.println("ANÁLISIS SEMÁNTICO");
        System.out.println("=================================");

        AnalizadorSemantico semantico =
                new AnalizadorSemantico();

        semantico.analizar(programa);
        
        
        // =========================
        // OPTIMIZACIÓN
        // =========================

        System.out.println();
        System.out.println("=================================");
        System.out.println("OPTIMIZACIÓN");
        System.out.println("=================================");

        Optimizador optimizador =
                new Optimizador();

        NodoPrograma programaOptimizado =
                optimizador.optimizar(programa);

        System.out.println();
        System.out.println("AST OPTIMIZADO");
        System.out.println("---------------------------------");

        programaOptimizado.imprimir("");
        
        
        
        // =========================
        // GENERACIÓN C
        // =========================

        System.out.println();
        System.out.println("=================================");
        System.out.println("GENERACIÓN C");
        System.out.println("=================================");

        GeneradorC generador =
                new GeneradorC();

        generador.generar(programaOptimizado);
        
    }
    
}
