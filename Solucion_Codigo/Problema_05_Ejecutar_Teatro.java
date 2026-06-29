/**
 *
 * @author Iker
 */
class Zona {

    public String nombreZona;
    public int cantidadesLocalidades;
    public double precioNormal;
    public double precioAbonado;

    public Zona(String nombreZona, int cantidadesLocalidades, double precioNormal, double precioAbonado) {
        this.nombreZona = nombreZona;
        this.cantidadesLocalidades = cantidadesLocalidades;
        this.precioNormal = precioNormal;
        this.precioAbonado = precioAbonado;
    }

    @Override
    public String toString() {
        return "Zona{" + "nombreZona=" + nombreZona + ", cantidadesLocalidades=" + cantidadesLocalidades + ", precioNormal=" + precioNormal + ", precioAbonado=" + precioAbonado + '}';
    }

}

class Entrada {

    public int idEntrada, numeroEntrada;
    public Zona zona;
    public String nombreComprado;
    public double costiFinalEntrada;

    public Entrada(int idEntrada, int numeroEntrada, Zona zona, String nombreComprado) {
        this.idEntrada = idEntrada;
        this.numeroEntrada = numeroEntrada;
        this.zona = zona;
        this.nombreComprado = nombreComprado;
    }

    public double calcularCostoFinalEntrada(double precioEntrada) {
        this.costiFinalEntrada = (this.numeroEntrada * precioEntrada);
        return this.costiFinalEntrada;
    }

    @Override
    public String toString() {
        return "Entrada{" + "idEntrada=" + idEntrada + ", numeroEntrada=" + numeroEntrada + ", zona=" + zona + ", nombreComprado=" + nombreComprado + ", costiFinalEntrada=" + costiFinalEntrada + '}';
    }

}

class Entrada_Normal extends Entrada {

    public Entrada_Normal(int idEntrada, int numeroEntrada, Zona zona, String nombreComprado) {
        super(idEntrada, numeroEntrada, zona, nombreComprado);
    }

    public double calcularCostoFinalEntrada() {
        this.costiFinalEntrada = (super.calcularCostoFinalEntrada(zona.precioNormal));
        return this.costiFinalEntrada;
    }

    @Override
    public String toString() {
        return "Entrada_Normal{" + '}' + super.toString();
    }

}

class Entrada_Reducida extends Entrada {

    public double porcentajeRebaja;

    public Entrada_Reducida(double porcentajeRebaja, int idEntrada, int numeroEntrada, Zona zona, String nombreComprado) {
        super(idEntrada, numeroEntrada, zona, nombreComprado);
        this.porcentajeRebaja = porcentajeRebaja;
    }

    public double calcularCostoFinalEntrada() {
        this.costiFinalEntrada = (super.calcularCostoFinalEntrada(zona.precioNormal - (zona.precioNormal * (porcentajeRebaja / 100))));
        return this.costiFinalEntrada;
    }

    @Override
    public String toString() {
        return "Entrada_Reducida{" + "porcentajeRebaja=" + porcentajeRebaja + '}' + super.toString();
    }

}

class Entrada_Abonada extends Entrada {

    public Entrada_Abonada(int idEntrada, int numeroEntrada, Zona zona, String nombreComprado) {
        super(idEntrada, numeroEntrada, zona, nombreComprado);
    }

    public double calcularCostoFinalEntrada() {
        this.costiFinalEntrada = (super.calcularCostoFinalEntrada(zona.precioAbonado));
        return this.costiFinalEntrada;
    }

    @Override
    public String toString() {
        return "Entrada_Abonada{" + '}' + super.toString();
    }

}

public class Problema_05_Ejecutar_Teatro {
    public static void main(String[] args) {
        Zona zona1 = new Zona("principal", 200, 25, 17.5);
        Zona zona2 = new Zona("palco", 40, 70, 40);
        Zona zona3 = new Zona("central", 400, 20, 14);
        Zona zona4 = new Zona("lateral", 100, 15, 10);
        Entrada_Normal entNormal = new Entrada_Normal(1, 1, zona4, "Iker");
        Entrada_Reducida entReducida = new Entrada_Reducida(15, 2, 2, zona2, "Andrey");
        Entrada_Abonada entAbonado = new Entrada_Abonada(3, 2, zona3, "Daniel y Enrique");
        entNormal.calcularCostoFinalEntrada();
        entReducida.calcularCostoFinalEntrada();
        entAbonado.calcularCostoFinalEntrada();
        System.out.println(entNormal);
        System.out.println(entReducida);
        System.out.println(entAbonado);
    }
}
/*
run:
Entrada_Normal{}Entrada{idEntrada=1, numeroEntrada=1, zona=Zona{nombreZona=lateral, cantidadesLocalidades=100, precioNormal=15.0, precioAbonado=10.0}, nombreComprado=Iker, costiFinalEntrada=15.0}
Entrada_Reducida{porcentajeRebaja=15.0}Entrada{idEntrada=2, numeroEntrada=2, zona=Zona{nombreZona=palco, cantidadesLocalidades=40, precioNormal=70.0, precioAbonado=40.0}, nombreComprado=Andrey, costiFinalEntrada=119.0}
Entrada_Abonada{}Entrada{idEntrada=3, numeroEntrada=2, zona=Zona{nombreZona=central, cantidadesLocalidades=400, precioNormal=20.0, precioAbonado=14.0}, nombreComprado=Daniel y Enrique, costiFinalEntrada=28.0}
BUILD SUCCESSFUL (total time: 0 seconds)

*/