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

# 👨‍💻 Autor

<div align="center">

## Salvador Vargas Pelayo

Compilador experimental desarrollado con Java y GCC.

</div>