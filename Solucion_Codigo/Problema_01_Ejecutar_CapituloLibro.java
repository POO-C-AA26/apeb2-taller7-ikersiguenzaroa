/**
 *Dibuje un diagrama de clases que muestre la estructura de un capítulo de libro; 
 * un capítulo está compuesto por varias secciones, * cada una de las cuales comprende varios párrafos y figuras. 
 * Un párrafo incluye varias sentencias, cada una de las cuales contiene varias palabras.
 *Note
 *Suponga que en un futuro se prevé que el sistema gestione además de párrafos y figuras otros componentes, como tablas, listas, viñetas, etc.
 *Suponga además que una palabra puede aparecer en varias sentencias.
 * @author Iker
 * @version 1.0
 */
import java.util.ArrayList;

class Palabra {
    private String valor; // Encapsulamiento

    public Palabra(String valor) {
        this.valor = valor;
    }

    public String getValor() { return valor; }

    @Override
    public String toString() { return valor; }
}

class Sentencia {
    private ArrayList<Palabra> palabras;
    
    public Sentencia() {
        this.palabras = new ArrayList<>();
    }
    
    public void agregarPalabra(Palabra p) {
        if(p != null) this.palabras.add(p);
    }
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("    Sentencia: ");
        for (Palabra p : palabras) {
            result.append(p.getValor()).append(" ");
        }
        return result.toString();
    }
}


abstract class Componente {
    protected String tipo; // Protected para que las subclases lo hereden

    public Componente(String tipo) {
        this.tipo = tipo;
    }
    
    public String getTipo() { return tipo; }
    
    
    @Override
    public abstract String toString(); 
}

class Parrafo extends Componente {
    private ArrayList<Sentencia> sentencias;
    
    public Parrafo() {
        super("parrafo");
        this.sentencias = new ArrayList<>();
    }
    
    public void agregarSentencia(Sentencia s) {
        if(s != null) this.sentencias.add(s);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("  Parrafo:\n");
        for (Sentencia s : sentencias) {
            result.append(s.toString()).append("\n");
        }
        return result.toString();
    }
}

class Figura extends Componente {
    private String descripcion;
    private String url;

    public Figura(String descripcion, String url) {
        super("figura");
        this.descripcion = descripcion;
        this.url = url;
    }
    
    @Override
    public String toString() {
        return "  Figura:\n    descripcion=" + descripcion + " | url=" + url;
    }
}

class Seccion {
    private String titulo;
    private ArrayList<Componente> componentes; // 

    public Seccion(String titulo) {
        this.titulo = titulo;
        this.componentes = new ArrayList<>();
    }
    
    public void agregarComponente(Componente c) {
        if(c != null) this.componentes.add(c);
    }
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(" Seccion: " + titulo + "\n");
        for (Componente c : componentes) {
            result.append(c.toString()).append("\n");
        }
        return result.toString();
    }
}

class Capitulo {
    private String titulo;
    private int numero;
    private ArrayList<Seccion> secciones;

    public Capitulo(String titulo, int numero) {
        this.titulo = titulo;
        this.numero = numero;
        this.secciones = new ArrayList<>();
    }
    
    public void agregarSeccion(Seccion s) {
        if(s != null) this.secciones.add(s);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Capitulo " + numero + ": " + titulo + "\n");
        for (Seccion s : secciones) {
            result.append(s.toString()).append("\n");
        }
        return result.toString();
    }  
}

class LibroController {
    private ArrayList<Capitulo> capitulos;

    public LibroController() {
        this.capitulos = new ArrayList<>();
    }

    public void agregarCapitulo(Capitulo c) {
        if(c != null) this.capitulos.add(c);
    }

    public String mostrarLibro() {
        StringBuilder sb = new StringBuilder("=== ESTRUCTURA DEL LIBRO ===\n");
        for (Capitulo c : capitulos) {
            sb.append(c.toString()).append("\n");
        }
        return sb.toString();
    }
}

public class Problema_01_Ejecutar_CapituloLibro {
    public static void main(String[] args) {
        LibroController miLibro = new LibroController();
        
        // 1. Creación de Palabras (Se pueden reutilizar en múltiples sentencias)
        Palabra p1 = new Palabra("Python");
        Palabra p2 = new Palabra("es");
        Palabra p3 = new Palabra("eficaz en programacion");
        Palabra p4 = new Palabra("utilizado");
        Palabra p5 = new Palabra("para");
        Palabra p6 = new Palabra("aplicaciones");

        // 2. Composición de Sentencias
        Sentencia s1 = new Sentencia();
        s1.agregarPalabra(p1); // "Java"
        s1.agregarPalabra(p2);
        s1.agregarPalabra(p3);

        Sentencia s2 = new Sentencia();
        s2.agregarPalabra(p1); // Se reutiliza "Java" exitosamente
        s2.agregarPalabra(p2);
        s2.agregarPalabra(p4);
        s2.agregarPalabra(p5);
        s2.agregarPalabra(p6);
        
        // 3. Creación de Componentes Polimórficos
        Parrafo parrafo1 = new Parrafo();
        parrafo1.agregarSentencia(s1);
        parrafo1.agregarSentencia(s2);
        
        Figura figura1 = new Figura("Diagrama de clases UML", "img/diagrama.png");
        
        // 4. Agrupación en Sección
        Seccion seccion1 = new Seccion("Introduccion a POO");
        seccion1.agregarComponente(parrafo1); // Inserta un Párrafo en la lista de Componentes
        seccion1.agregarComponente(figura1);  // Inserta una Figura en la misma lista
        
        // 5. Agrupación en Capítulo y Controlador
        Capitulo capitulo1 = new Capitulo("Programacion Orientada a Objetos", 1);
        capitulo1.agregarSeccion(seccion1);
        
        miLibro.agregarCapitulo(capitulo1);
        
        // 6. Impresión Final
        System.out.println(miLibro.mostrarLibro());
    }
}
/*
run:
=== ESTRUCTURA DEL LIBRO ===
Capitulo 1: Programacion Orientada a Objetos
 Seccion: Introduccion a POO
  Parrafo:
    Sentencia: Python es eficaz en programacion 
    Sentencia: Python es utilizado para aplicaciones 

  Figura:
    descripcion=Diagrama de clases UML | url=img/diagrama.png



BUILD SUCCESSFUL (total time: 0 seconds)

*/