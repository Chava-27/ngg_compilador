/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generadorc;

import ast.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Chava27
 */
public class GeneradorC {
 
    private final StringBuilder codigoC;

    public GeneradorC() {

        codigoC = new StringBuilder();

        codigoC.append("#include <stdio.h>\n\n");

        codigoC.append("int main() {\n\n");
    }

    public void generar(NodoPrograma programa) {

        for (Nodo nodo : programa.getInstrucciones()) {
            generarNodo(nodo);
        }

        codigoC.append("\n");
        codigoC.append("    return 0;\n");
        codigoC.append("}\n");

        guardarArchivo();

        compilarC();
    }

    private void generarNodo(Nodo nodo) {

        // DECLARACIONES
        if (nodo instanceof NodoDeclaracion declaracion) {

            String tipo =
                    convertirTipo(
                            declaracion.getTipoDato()
                    );

            String expresion =
                    generarExpresion(
                            declaracion.getValor()
                    );

            codigoC.append(
                    "    "
                    + tipo
                    + " "
                    + declaracion.getNombreVariable()
                    + " = "
                    + expresion
                    + ";\n"
            );
        }

        // IMPRIMIR
        else if (nodo instanceof NodoImpresion impresion) {

            String expresion =
                    generarExpresion(
                            impresion.getExpresion()
                    );

            codigoC.append(
                    "    printf(\"%d\\n\", "
                    + expresion
                    + ");\n"
            );
        }
    }

    private String generarExpresion(Nodo nodo) {

        // LITERAL
        if (nodo instanceof NodoLiteral literal) {

            return literal.getValor().toString();
        }

        // VARIABLE
        if (nodo instanceof NodoVariable variable) {

            return variable.getNombre();
        }

        // OPERACIÓN
        if (nodo instanceof NodoOperacion operacion) {

            String izquierda =
                    generarExpresion(
                            operacion.getIzquierdo()
                    );

            String derecha =
                    generarExpresion(
                            operacion.getDerecho()
                    );

            return "("
                    + izquierda
                    + " "
                    + operacion.getOperador()
                    + " "
                    + derecha
                    + ")";
        }

        return "";
    }

    private String convertirTipo(String tipo) {

        return switch (tipo) {

            case "entero" -> "int";

            case "decimal" -> "float";

            case "texto" -> "char*";

            default -> "int";
        };
    }

    private void guardarArchivo() {

        try {

            File carpeta =
                    new File("resultados");

            if (!carpeta.exists()) {
                carpeta.mkdir();
            }

            File archivo =
                    new File(
                            "resultados/programa.c"
                    );

            FileWriter writer =
                    new FileWriter(archivo);

            writer.write(codigoC.toString());

            writer.close();

            System.out.println();
            System.out.println(
                    "ARCHIVO C GENERADO:"
            );

            System.out.println(
                    archivo.getAbsolutePath()
            );

        } catch (IOException e) {

            System.out.println(
                    "ERROR AL GUARDAR ARCHIVO C"
            );
        }
    }

    private void compilarC() {

        try {

            ProcessBuilder builder =
                    new ProcessBuilder(
                            "gcc",
                            "resultados/programa.c",
                            "-o",
                            "resultados/programa.exe"
                    );

            builder.inheritIO();

            Process proceso =
                    builder.start();

            int resultado =
                    proceso.waitFor();

            System.out.println();

            if (resultado == 0) {

                System.out.println(
                        "EJECUTABLE GENERADO:"
                );

                System.out.println(
                        "resultados/programa.exe"
                );

            } else {

                System.out.println(
                        "ERROR AL COMPILAR"
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "ERROR EJECUTANDO GCC"
            );
        }
    }
    
}
