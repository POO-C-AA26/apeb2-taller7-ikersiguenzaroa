
/**
 *Un videoclub dispone de una serie de películas que pueden estar en DVD 
 *con capacidad en Gb.) o en VHS (una sola cinta por película y con cierto tipo de cinta magnetica)
 *.De las películas interesa guardar el título, el autor, el año de edición y el idioma (o los idiomas,
 *en caso de DVD). El precio de alquiler de las películas varía en función del tipo de película
 *.Las DVD siempre son 10% mas caras que las de VHS. 
 * @author Iker
 */
import java.util.Arrays;
class Pelicula {
    public String titulo;
    public String autor;
    public int anio;
    public Pelicula(String titulo, String autor, int anio) {
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
    }

    @Override
    public String toString() {
        return "Pelicula{" + "titulo=" + titulo + ", autor=" + autor + ", anio=" + anio + '}';
    }
}
class Soporte{
    public Pelicula pelicula;
    public int cantidad;
    public double precio, costoAlquiler;
    public Soporte(Pelicula pelicula, int cantidad, double precio) {
        this.pelicula = pelicula;
        this.cantidad = cantidad;
        this.precio = precio;
    }
    public double calcularCostAlq(){
        this.costoAlquiler = this.cantidad * this.precio;
        return this.costoAlquiler;
    }

    @Override
    public String toString() {
        return "Soporte{" + "pelicula=" + pelicula + ", cantidad=" + cantidad + ", precio=" + precio + ", costoAlquiler=" + costoAlquiler + '}';
    }
}
class Dvd extends Soporte{
    public String idiomas[];
    public double porcentajeRecargo;

    public Dvd(String[] idiomas, double porcentajeRecargo, Pelicula pelicula, int cantidad, double precio) {
        super(pelicula, cantidad, precio);
        this.idiomas = idiomas;
        this.porcentajeRecargo = porcentajeRecargo;
    }
    public double calcularCostAlq(){
        this.costoAlquiler = super.calcularCostAlq()+ (super.calcularCostAlq()*(this.porcentajeRecargo /100));
        return this.costoAlquiler;
    }

    @Override
    public String toString() {
        return "Dvd{" + "idiomas=" + Arrays.toString(idiomas) + ", porcentajeRecargo=" + porcentajeRecargo + '}'+ super.toString();
    }
}
class Vhs extends Soporte{
    public String idioma;
    public Vhs(String idioma, Pelicula pelicula, int cantidad, double precio) {
        super(pelicula, cantidad, precio);
        this.idioma = idioma;
    }
    @Override
    public String toString() {
        return "Vhs{" + "idioma=" + idioma + '}'+super.toString();
    }   
}

public class Problema_02_Ejecutar_VideoClub {
    public static void main(String[] args) {
        String idiomas []= {"ES","ENG"};
        Pelicula peli1 = new Pelicula("Mario Galaxy", "Andrey", 2002);
        Pelicula peli2 = new Pelicula("Intensamente 2", "Iker", 2002);
        Vhs peliejem_Vhs = new Vhs("ES", peli2, 2, 10.50);
        peliejem_Vhs.calcularCostAlq();
        Dvd peliMundial_Dvd = new Dvd(idiomas, 10, peli1, 4, 7.80);
        System.out.println(peliejem_Vhs);
        System.out.println(peliMundial_Dvd);
    }
}
/* 
run:
Vhs{idioma=ES}Soporte{pelicula=Pelicula{titulo=Intensamente 2, autor=Iker, anio=2002}, cantidad=2, precio=10.5, costoAlquiler=21.0}
Dvd{idiomas=[ES, ENG], porcentajeRecargo=10.0}Soporte{pelicula=Pelicula{titulo=Mario Galaxy, autor=Andrey, anio=2002}, cantidad=4, precio=7.8, costoAlquiler=0.0}
BUILD SUCCESSFUL (total time: 0 seconds)


*/