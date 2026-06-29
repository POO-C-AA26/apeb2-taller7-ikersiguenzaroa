
/**
 *
 * 
 * 
 * 
 * @author Iker
 */
import java.util.ArrayList;
import java.util.Scanner;

abstract class Trabajador {
    private String nombre;
    private String apellido;
    private String direccion;
    private String dni;
    private Jefe jefe; 

    public Trabajador(String nombre, String apellido, String direccion, String dni, Jefe jefe) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.dni = dni;
        this.jefe = jefe;
    }

    
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getDireccion() { return direccion; }
    public String getDni() { return dni; }
    public Jefe getJefe() { return jefe; }

    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setJefe(Jefe jefe) { this.jefe = jefe; }

    
    public abstract double calcularNomina();

    @Override
    public String toString() {
        String infoJefe = (jefe != null) ? jefe.getNombre() + " " + jefe.getApellido() : "Ninguno";
        return String.format("DNI: %s | %s %s | Direccion: %s | Jefe: %s | Sueldo a percibir: $%.2f", 
                dni, nombre, apellido, direccion, infoJefe, calcularNomina());
    }
}

class FijoMensual extends Trabajador {
    private double sueldoMensual;

    public FijoMensual(String nombre, String apellido, String direccion, String dni, Jefe jefe, double sueldoMensual) {
        super(nombre, apellido, direccion, dni, jefe);
        this.sueldoMensual = sueldoMensual;
    }

    public double getSueldoMensual() { return sueldoMensual; }
    public void setSueldoMensual(double sueldoMensual) { this.sueldoMensual = sueldoMensual; }

    @Override
    public double calcularNomina() {
        return sueldoMensual;
    }

    @Override
    public String toString() {
        return "[Fijo Mensual] " + super.toString();
    }
}

class Comisionista extends Trabajador {
    private double porcentajeComision;
    private double ventasRealizadas;

    public Comisionista(String nombre, String apellido, String direccion, String dni, Jefe jefe, double porcentajeComision) {
        super(nombre, apellido, direccion, dni, jefe);
        this.porcentajeComision = porcentajeComision;
        this.ventasRealizadas = 0.0; // Inicia en 0 hasta que se fijen las ventas del mes
    }

    public double getPorcentajeComision() { return porcentajeComision; }
    public double getVentasRealizadas() { return ventasRealizadas; }
    
    public void setVentasRealizadas(double ventasRealizadas) {
        this.ventasRealizadas = ventasRealizadas;
    }

    @Override
    public double calcularNomina() {
        return (porcentajeComision / 100.0) * ventasRealizadas;
    }

    @Override
    public String toString() {
        return "[Comisionista] " + super.toString() + " (Ventas fijadas: $" + ventasRealizadas + ")";
    }
}

class PorHoras extends Trabajador {
    private double precioHoraNormal;
    private double precioHoraExtra;
    private int horasTrabajadas;

    public PorHoras(String nombre, String apellido, String direccion, String dni, Jefe jefe, double precioHoraNormal, double precioHoraExtra) {
        super(nombre, apellido, direccion, dni, jefe);
        this.precioHoraNormal = precioHoraNormal;
        this.precioHoraExtra = precioHoraExtra;
        this.horasTrabajadas = 0; // Inicia en 0 hasta que se fijen las horas del mes
    }

    public int getHorasTrabajadas() { return horasTrabajadas; }
    
    public void setHorasTrabajadas(int horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    @Override
    public double calcularNomina() {
        if (horasTrabajadas > 40) {
            return (40 * precioHoraNormal) + ((horasTrabajadas - 40) * precioHoraExtra);
        } else {
            return horasTrabajadas * precioHoraNormal;
        }
    }

    @Override
    public String toString() {
        return "[Por Horas]   " + super.toString() + " (Horas fijadas: " + horasTrabajadas + ")";
    }
}

class Jefe extends Trabajador {
    private double sueldoFijo;

    public Jefe(String nombre, String apellido, String direccion, String dni, double sueldoFijo) {
        super(nombre, apellido, direccion, dni, null); // Pasa null porque no tiene jefe superior
        this.sueldoFijo = sueldoFijo;
    }

    @Override
    public double calcularNomina() {
        return sueldoFijo;
    }

    @Override
    public String toString() {
        return "[Jefe]        " + super.toString();
    }
}

class EmpresaController {
    private ArrayList<Trabajador> trabajadores;

    public EmpresaController() {
        this.trabajadores = new ArrayList<>();
    }

    public void agregarTrabajador(Trabajador t) {
        trabajadores.add(t);
    }

    public ArrayList<Trabajador> getTrabajadores() {
        return trabajadores;
    }

    
    public String obtenerNominasMensuales() {
        if (trabajadores.isEmpty()) {
            return "No existen trabajadores registrados en el sistema.";
        }
        StringBuilder sb = new StringBuilder("\n========================================================================\n");
        sb.append("                  REPORTE DE NOMINA GENERAL - FIN DE MES\n");
        sb.append("========================================================================\n");
        for (Trabajador t : trabajadores) {
            sb.append(t.toString()).append("\n");
        }
        sb.append("========================================================================");
        return sb.toString();
    }
}


public class Problema_04_Ejecutar_Trabajador {
    
    
    public static void main(String[] args) {
        EmpresaController controlador = new EmpresaController();
        Scanner sc = new Scanner(System.in);
        
        
        Jefe jefe1 = new Jefe("Raul", "Armijos", "Enrique Tapia ", "777", 15000.0);
        Comisionista comisionista = new Comisionista("Enrique", "Perez", "Ciudad Victoria", "1", jefe1, 10.0);
        PorHoras trabajadorHoras = new PorHoras("Iker", "Siguenza", "Tierras Coloradas", "007", jefe1, 10.0, 15.0);
        
        controlador.agregarTrabajador(jefe1);
        controlador.agregarTrabajador(comisionista);
        controlador.agregarTrabajador(trabajadorHoras);

        System.out.println("====== SISTEMA DE GESTION DE NOMINA ======");
        
        // 2. Fijar dinámicamente las ventas realizadas (Entrada de Datos)
        System.out.print("\n[CONFIGURACION] Ingrese el monto de ventas del mes para el Comisionista (" 
                + comisionista.getNombre() + " " + comisionista.getApellido() + "): $");
        double ventas = sc.nextDouble();
        comisionista.setVentasRealizadas(ventas);

        // 3. Fijar dinámicamente las horas del mes (Entrada de Datos)
        System.out.print("[CONFIGURACION] Ingrese la cantidad de horas trabajadas por el empleado (" 
                + trabajadorHoras.getNombre() + " " + trabajadorHoras.getApellido() + "): ");
        int horas = sc.nextInt();
        trabajadorHoras.setHorasTrabajadas(horas);

        // 4. Imprimir la nómina correspondiente al final de mes (Salida de Datos)
        String reporteFinal = controlador.obtenerNominasMensuales();
        System.out.println(reporteFinal);
        
        sc.close();
    }
}
/*
run:
====== SISTEMA DE GESTION DE NOMINA ======

[CONFIGURACION] Ingrese el monto de ventas del mes para el Comisionista (Enrique Perez): $200
[CONFIGURACION] Ingrese la cantidad de horas trabajadas por el empleado (Iker Siguenza): 2000

========================================================================
                  REPORTE DE NOMINA GENERAL - FIN DE MES
========================================================================
[Jefe]        DNI: 777 | Raul Armijos | Direccion: Enrique Tapia  | Jefe: Ninguno | Sueldo a percibir: $15000.00
[Comisionista] DNI: 1 | Enrique Perez | Direccion: Ciudad Victoria | Jefe: Raul Armijos | Sueldo a percibir: $20.00 (Ventas fijadas: $200.0)
[Por Horas]   DNI: 007 | Iker Siguenza | Direccion: Tierras Coloradas | Jefe: Raul Armijos | Sueldo a percibir: $29800.00 (Horas fijadas: 2000)
========================================================================
BUILD SUCCESSFUL (total time: 8 seconds)

*/