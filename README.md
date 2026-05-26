<div align="center">

# ⚙️ NGG Compiler

### Compilador experimental desarrollado en Java

<img src="https://img.shields.io/badge/Java-17+-orange?style=for-the-badge&logo=openjdk" />
<img src="https://img.shields.io/badge/GCC-w64devkit-blue?style=for-the-badge&logo=gnu" />
<img src="https://img.shields.io/badge/Build-Ant-red?style=for-the-badge&logo=apacheant" />
<img src="https://img.shields.io/badge/IDE-NetBeans-1B6AC6?style=for-the-badge&logo=apache-netbeans-ide" />

---

### 🚀 Compilador que transforma código NGG → C → Ejecutable nativo

</div>

---

# 📖 Descripción

NGG Compiler es un compilador experimental desarrollado completamente en Java que implementa las principales fases de compilación utilizadas en compiladores reales.

El compilador transforma programas escritos en el lenguaje **NGG** hacia código **C**, y posteriormente utiliza **GCC** para generar ejecutables nativos (`.exe`).

---

# ✨ Características

| Característica | Descripción |
|---|---|
| 🔍 Análisis Léxico | Conversión de código fuente a tokens |
| 🌳 AST | Construcción de Árbol de Sintaxis Abstracta |
| 📚 Tabla de símbolos | Gestión de variables y funciones |
| 🧠 Análisis semántico | Validación de tipos y scopes |
| ⚡ Optimización | Simplificación y mejoras |
| ⚙️ Generación C | Traducción automática a C |
| 🖥️ GCC | Compilación hacia ejecutables reales |
| ❌ Manejo de errores | Detección de errores léxicos, sintácticos y semánticos |

---

# 🛠️ Tecnologías utilizadas

| Tecnología | Uso |
|---|---|
| ☕ Java | Desarrollo principal |
| 🧰 Apache NetBeans | IDE |
| ⚙️ GCC | Compilación del código C |
| 🧱 w64devkit | Entorno GCC para Windows |
| 🏗️ Apache Ant | Sistema de build |

---

# 📥 Descargas necesarias

## ☕ Java JDK 17+

| Herramienta | Link |
|---|---|
| OpenJDK | https://adoptium.net/ |
| Oracle JDK | https://www.oracle.com/java/technologies/downloads/ |

Verificar instalación:

```bash
java -version
```

---

## ⚙️ w64devkit (GCC para Windows)

NGG Compiler utiliza GCC para convertir el código C generado en ejecutables reales.

| Herramienta | Link |
|---|---|
| w64devkit | https://w64devkit.net/ |

---

# 📦 Instalación de w64devkit

## 1️⃣ Descargar

Descargar el archivo `.zip` desde la página oficial.

---

## 2️⃣ Extraer

Extraer en una ruta como:

```txt
C:\w64devkit
```

---

## 3️⃣ Agregar al PATH

Agregar la carpeta:

```txt
C:\w64devkit\bin
```

al PATH del sistema.

---

## 4️⃣ Verificar instalación

Abrir CMD y ejecutar:

```bash
gcc --version
```

---

# 🚀 Compilar el proyecto

## Desde NetBeans

Abrir el proyecto y ejecutar:

```txt
Run Project
```

---

## Desde consola

```bash
ant clean
ant build
```

---

# 📁 Estructura del proyecto

```txt
src/
├── ast/
├── lexico/
├── sintactico/
├── semantico/
├── optimizador/
├── generadorc/
├── principal/
├── utils/
└── resultados/
```

---

# 🧩 Descripción de carpetas

| Carpeta | Función |
|---|---|
| 📂 ast | Nodos del Árbol de Sintaxis Abstracta |
| 📂 lexico | Lexer y generación de tokens |
| 📂 sintactico | Parser y validación gramatical |
| 📂 semantico | Verificación semántica |
| 📂 optimizador | Optimización del AST |
| 📂 generadorc | Traducción de AST → C |
| 📂 principal | Punto de entrada del compilador |
| 📂 utils | Funciones auxiliares |
| 📂 resultados | Archivos generados automáticamente |

---

# ⚙️ Fases del compilador

<div align="center">

```txt
Código fuente NGG
        ↓
🔍 Análisis Léxico
        ↓
📦 Tokens
        ↓
🧠 Análisis Sintáctico
        ↓
🌳 AST
        ↓
📚 Análisis Semántico
        ↓
⚡ Optimización
        ↓
⚙️ Generación de Código C
        ↓
📄 programa.c
        ↓
🛠️ GCC
        ↓
🖥️ programa.exe
```

</div>

---

# 🔍 Fase 1 — Análisis Léxico

El lexer lee carácter por carácter y genera tokens.

## Ejemplo

Código fuente:

```txt
x = 10 + 5;
```

Tokens generados:

```txt
IDENTIFIER ASSIGN NUMBER PLUS NUMBER SEMICOLON
```

---

# 🌳 Fase 2 — Análisis Sintáctico

El parser valida la gramática y construye el AST.

Ejemplo:

```txt
if (x > 10)
```

Aquí se verifica que la estructura sea válida.

---

# 🌲 Fase 3 — Construcción del AST

El programa se transforma en una estructura jerárquica.

```txt
AssignmentNode
 ├── IdentifierNode(x)
 └── BinaryExpressionNode(+)
```

---

# 🧠 Fase 4 — Análisis Semántico

Se validan:

- Variables declaradas
- Tipos compatibles
- Scope
- Funciones
- Parámetros

Aquí se utilizan estructuras como:

```txt
SymbolTable
```

y funciones como:

```txt
define()
lookup()
enter_scope()
exit_scope()
```

---

# ⚡ Fase 5 — Optimización

Se realizan optimizaciones sobre el AST.

Ejemplos:

| Optimización | Ejemplo |
|---|---|
| Constant Folding | `2 + 2 → 4` |
| Eliminación de código muerto | Remoción de nodos innecesarios |

---

# ⚙️ Fase 6 — Generación de código C

El AST se traduce automáticamente hacia código C.

## Ejemplo

Código NGG:

```txt
print("Hola");
```

Código C generado:

```c
printf("Hola");
```

Salida generada:

```txt
programa.c
```

---

# 🛠️ Fase 7 — Compilación con GCC

El archivo C generado se compila usando GCC.

Comando equivalente:

```bash
gcc programa.c -o programa.exe
```

Resultado:

```txt
programa.exe
```

---

# 🔄 Flujo interno de ejecución

```txt
Main
 └── Lexer
      └── Tokens
           └── Parser
                └── AST
                     └── SemanticAnalyzer
                          └── SymbolTable
                               └── Optimizer
                                    └── CCodeGenerator
                                         └── GCC
```

---

# 🧠 Orden de ejecución detallado

| Paso | Descripción |
|---|---|
| 1️⃣ | `Main.java` inicia el compilador |
| 2️⃣ | Se carga el archivo fuente `.ngg` |
| 3️⃣ | El Lexer genera tokens |
| 4️⃣ | El Parser consume tokens |
| 5️⃣ | Se construye el AST |
| 6️⃣ | El SemanticAnalyzer valida el programa |
| 7️⃣ | El Optimizer mejora el AST |
| 8️⃣ | El CCodeGenerator genera código C |
| 9️⃣ | GCC genera el ejecutable |

---

# ▶️ Ejecutar programas generados

Una vez generado el ejecutable:

```txt
programa.exe
```

debe ejecutarse manualmente desde CMD.

---

# 📌 Paso 1

Abrir CMD.

---

# 📌 Paso 2

Ir a la carpeta de resultados.

```bash
cd resultados
```

---

# 📌 Paso 3

Ejecutar el programa:

```bash
.\programa.exe
```

---

# 💻 Ejemplo completo

```bash
cd resultados
.\programa.exe
```

---

# 📂 Archivos generados

Durante la compilación se generan automáticamente:

| Archivo | Descripción |
|---|---|
| 📄 programa.c | Código C generado |
| 🖥️ programa.exe | Ejecutable final |

---

# 🚫 Archivos ignorados por Git

El proyecto utiliza `.gitignore` para evitar subir:

- Ejecutables
- Archivos compilados
- Resultados automáticos
- Configuración privada de NetBeans

---

# ✅ Archivos importantes que sí deben subirse

```txt
src/
nbproject/
build.xml
manifest.mf
README.md
.gitignore
```

---
# 📚 Sintaxis del lenguaje NGG

La siguiente tabla muestra las características y estructuras actualmente soportadas por el lenguaje NGG Compiler.

---

| Categoría | Sintaxis | Descripción |
|---|---|---|
| 🔢 Variables | `int x = 10;` | Declaración de variables |
| ➕ Operaciones aritméticas | `x + y` | Suma |
| ➖ Operaciones aritméticas | `x - y` | Resta |
| ✖️ Operaciones aritméticas | `x * y` | Multiplicación |
| ➗ Operaciones aritméticas | `x / y` | División |
| 📌 Asignación | `x = 20;` | Asignación de valores |
| 🧠 Comparaciones | `x > y` | Comparadores relacionales |
| 🧠 Comparaciones | `x < y` | Comparadores relacionales |
| 🧠 Comparaciones | `x == y` | Comparación de igualdad |
| 🧠 Comparaciones | `x != y` | Comparación diferente |
| 🔀 Condicionales | `if (x > 10)` | Estructuras condicionales |
| 🔀 Condicionales | `else` | Bloque alternativo |
| 🖨️ Salida | `print("Hola");` | Impresión en consola |
| 🔤 Strings | `"Hola Mundo"` | Literales de texto |
| 🔢 Números enteros | `123` | Literales numéricos |
| 🧱 Bloques | `{ }` | Agrupación de instrucciones |
| 📝 Expresiones | `x + y * z` | Expresiones complejas |
| 📦 Tokens | `IDENTIFIER`, `NUMBER` | Tipos de tokens soportados |
| 🌳 AST | `BinaryExpressionNode` | Construcción del AST |
| ⚙️ Generación C | `printf()` | Traducción automática hacia C |
| 🖥️ Ejecutables | `programa.exe` | Generación de ejecutables reales |

---

# ✅ Ejemplo de programa válido

```txt
int x = 10;
int y = 20;

if (x < y) {
    print("x es menor que y");
}
```

---

# ⚠️ Características NO soportadas actualmente

Actualmente NGG Compiler aún NO soporta:

- Funciones definidas por el usuario
- Parámetros
- Retorno de funciones
- Ciclos `for`
- Ciclos `while`
- Ciclos `do-while`
- Arreglos
- Matrices
- Objetos
- Clases
- Herencia
- Interfaces
- Structs
- Punteros
- Recursividad
- Librerías externas
- Entrada de usuario (`input`)
- Tipos flotantes (`float`, `double`)
- Booleanos (`true`, `false`)
- Switch / Case
- Break / Continue
- Operadores lógicos complejos
- Manejo de excepciones
- Múltiples archivos fuente
- Compilación cruzada
- Generación de binarios Linux
- Optimización avanzada
- Garbage Collector
- Tipado dinámico

---

# 🚧 Estado del proyecto

NGG Compiler actualmente se encuentra en una etapa experimental y educativa, enfocado principalmente en demostrar el funcionamiento interno de un compilador moderno:

```txt
Código fuente → AST → Semántica → C → GCC → Ejecutable
```

El objetivo principal del proyecto es académico y de aprendizaje sobre:

- Diseño de lenguajes
- Compiladores
- AST
- Parsers
- Semántica
- Generación de código
- Backend de compiladores
---

# 👨‍💻 Autor

<div align="center">

## Salvador Vargas Pelayo

Compilador experimental desarrollado con Java y GCC.

</div>