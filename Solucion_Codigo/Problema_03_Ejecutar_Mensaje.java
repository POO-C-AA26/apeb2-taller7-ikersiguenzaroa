/**
 * Implemente un sistema de envío de mensajes a móviles. Existen dos tipos de mensajes 
 * que se pueden enviar entre móviles, mensajes de texto (SMS) y mensajes que contienen 
 * imágenes (MMS). Por un lado, los mensajes de texto contienen un mensaje en caracteres 
 * que se desea enviar de un móvil a otro. Por otro lado, los mensajes que contienen 
 * imágenes almacenan información sobre la imagen a enviar, la cual se representará por 
 * el nombre del fichero que la contiene. Independientemente del tipo de mensaje, cada 
 * mensaje tendrá asociado un remitente de dicho mensaje y un destinatario. Ambos estarán 
 * definidos obligatoriamente por un número de móvil, y opcionalmente se podrá guardar 
 * información sobre su nombre. Además, los métodos enviarMensaje y visualizarMensaje 
 * deben estar definidos.
 * 
 * @author Iker Siguenza
 * @version 1.0
 */

import java.util.ArrayList;

abstract class Mensaje {
    // Atributos privados para garantizar el encapsulamiento estricto
    private String remitenteNum;
    private String destinatarioNum;
    private String remitente;
    private String destinatario;

    // Constructor con solo números
    public Mensaje(String remitenteNum, String destinatarioNum) {
        this.remitenteNum = validarNumero(remitenteNum);
        this.destinatarioNum = validarNumero(destinatarioNum);
        this.remitente = "";
        this.destinatario = "";
    }

    // Constructor con números y nombres
    public Mensaje(String remitenteNum, String destinatarioNum, String remitente, String destinatario) {
        this(remitenteNum, destinatarioNum);
        this.remitente = validarString(remitente);
        this.destinatario = validarString(destinatario);
    }

    // Métodos de validación internos
    private String validarNumero(String num) {
        if (num == null || num.trim().isEmpty() || !num.matches("\\d+")) {
            throw new IllegalArgumentException("El número de teléfono debe contener solo dígitos y no estar vacío");
        }
        return num.trim();
    }

    private String validarString(String str) {
        return (str != null) ? str.trim() : "";
    }

    // Método abstracto para forzar la obtención del contenido de forma polimórfica
    public abstract String getContenido();

    // Método para enviar mensaje (final para asegurar el formato base)
    public final String enviarMensaje() {
        String contenido = getContenido();
        String espacio = destinatario.isEmpty() ? "" : " ";
        return String.format("(%s) ENVIADO A %s%s%s", 
            contenido, remitente, espacio, destinatarioNum);
    }

    // Método para visualizar el mensaje completo
    public String visualizarMensaje() {
        String contenido = getContenido();
        String espacioRem = remitente.isEmpty() ? "" : " ";
        String espacioDest = destinatario.isEmpty() ? "" : " ";
        return String.format("De: %s%s%s\nPara: %s%s%s\n%s\n",
            remitente, espacioRem, remitenteNum, destinatario, espacioDest, destinatarioNum, contenido);
    }

    // Getters y Setters públicos para control de acceso exterior
    public String getRemitenteNum() { return remitenteNum; }
    public String getDestinatarioNum() { return destinatarioNum; }
    public String getRemitente() { return remitente; }
    public String getDestinatario() { return destinatario; }

    @Override
    public String toString() {
        return String.format("remitente=%s %s, destinatario=%s %s",
            remitente, remitenteNum, destinatario, destinatarioNum);
    }
}

// ==================== CLASE SMS ====================
class SMS extends Mensaje {
    private String mensaje;

    public SMS(String mensaje, String remitenteNum, String destinatarioNum) {
        super(remitenteNum, destinatarioNum);
        this.mensaje = validarMensaje(mensaje);
    }

    public SMS(String mensaje, String remitenteNum, String destinatarioNum, String remitente, String destinatario) {
        super(remitenteNum, destinatarioNum, remitente, destinatario);
        this.mensaje = validarMensaje(mensaje);
    }

    private String validarMensaje(String msg) {
        if (msg == null || msg.trim().isEmpty()) {
            throw new IllegalArgumentException("El mensaje SMS no puede estar vacío");
        }
        return msg.trim();
    }

    @Override
    public String getContenido() {
        return mensaje;
    }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) {
        this.mensaje = validarMensaje(mensaje);
    }

    @Override
    public String toString() {
        return "SMS{mensaje='" + mensaje + "', " + super.toString() + "}";
    }
}

// ==================== CLASE MMS ====================
class MMS extends Mensaje {
    private String fichero;

    public MMS(String fichero, String remitenteNum, String destinatarioNum) {
        super(remitenteNum, destinatarioNum);
        this.fichero = validarFichero(fichero);
    }

    public MMS(String fichero, String remitenteNum, String destinatarioNum, String remitente, String destinatario) {
        super(remitenteNum, destinatarioNum, remitente, destinatario);
        this.fichero = validarFichero(fichero);
    }

    private String validarFichero(String file) {
        if (file == null || file.trim().isEmpty()) {
            throw new IllegalArgumentException("El fichero MMS no puede estar vacío");
        }
        return file.trim();
    }

    @Override
    public String getContenido() {
        return fichero;
    }

    public String getFichero() { return fichero; }
    public void setFichero(String fichero) {
        this.fichero = validarFichero(fichero);
    }

    @Override
    public String toString() {
        return "MMS{fichero='" + fichero + "', " + super.toString() + "}";
    }
}

// ==================== CONTROLADOR ====================
class MensajeController {
    private final ArrayList<Mensaje> mensajes;

    public MensajeController() {
        this.mensajes = new ArrayList<>();
    }

    public void agregarMensaje(Mensaje mensaje) {
        if (mensaje == null) {
            throw new IllegalArgumentException("El mensaje no puede ser null");
        }
        mensajes.add(mensaje);
    }

    public void eliminarMensaje(int index) {
        if (index >= 0 && index < mensajes.size()) {
            mensajes.remove(index);
        }
    }

    public String enviarTodos() {
        if (mensajes.isEmpty()) {
            return "No hay mensajes para enviar";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("=== ENVIO DE MENSAJES ===\n");
        for (int i = 0; i < mensajes.size(); i++) {
            sb.append(i + 1).append(". ")
              .append(mensajes.get(i).enviarMensaje())
              .append("\n");
        }
        return sb.toString();
    }

    public String visualizarTodos() {
        if (mensajes.isEmpty()) {
            return "No hay mensajes para visualizar";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("=== VISUALIZACION DE MENSAJES ===\n");
        for (int i = 0; i < mensajes.size(); i++) {
            sb.append("Mensaje ").append(i + 1).append(":\n");
            sb.append(mensajes.get(i).visualizarMensaje());
            sb.append("\n");
        }
        return sb.toString();
    }

    public String mostrarInformacionCompleta() {
        if (mensajes.isEmpty()) {
            return "No hay mensajes registrados";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("=== INFORMACION COMPLETA ===\n");
        for (int i = 0; i < mensajes.size(); i++) {
            sb.append(i + 1).append(". ")
              .append(mensajes.get(i).toString())
              .append("\n");
        }
        return sb.toString();
    }

    public ArrayList<Mensaje> getMensajes() {
        return new ArrayList<>(mensajes);
    }

    public int getCantidadMensajes() {
        return mensajes.size();
    }
}

// ==================== VISTA / EJECUTOR ====================
public class Problema_03_Ejecutar_Mensaje {
    public static void main(String[] args) {
        try {
            MensajeController controller = new MensajeController();

            // Los números telefónicos ahora se manejan correctamente como Strings
            SMS msj1 = new SMS("Hola, Como estas?", "0988888888", "0999999999");
            SMS msj2 = new SMS("Hermano, si jugamos hoy?", "444", "777", "Juan", "Iker");
            
            MMS img1 = new MMS("C://Mi_Emergencia/2024/Emergencia1", "124", "911", "Iker", "Emergencias Loja");
            MMS img2 = new MMS("C://tareas/tarearealizar/poo_taller7", "111", "222222");

            controller.agregarMensaje(msj1);
            controller.agregarMensaje(msj2);
            controller.agregarMensaje(img1);
            controller.agregarMensaje(img2);

            // Ejecución limpia mediante comportamiento polimórfico
            System.out.print(controller.enviarTodos());
            System.out.println();
            System.out.print(controller.visualizarTodos());
            System.out.println();
            System.out.print(controller.mostrarInformacionCompleta());
            System.out.println();
            
            // Sección de Estadísticas generales
            System.out.println("=== ESTADISTICAS ===");
            System.out.println("Total mensajes: " + controller.getCantidadMensajes());
            System.out.println("Tipos: SMS=" + contarTipo(controller, "SMS") + 
                               ", MMS=" + contarTipo(controller, "MMS"));

        } catch (IllegalArgumentException e) {
            System.err.println("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
    }

    private static int contarTipo(MensajeController controller, String tipo) {
        int contador = 0;
        for (Mensaje m : controller.getMensajes()) {
            if (tipo.equals("SMS") && m instanceof SMS) {
                contador++;
            } else if (tipo.equals("MMS") && m instanceof MMS) {
                contador++;
            }
        }
        return contador;
    }
}

/*
run:
=== ENVIO DE MENSAJES ===
1. (Hola, Como estas?) ENVIADO A 0999999999
2. (Hermano, si jugamos hoy?) ENVIADO A Juan 777
3. (C://Mi_Emergencia/2024/Emergencia1) ENVIADO A Iker 911
4. (C://tareas/tarearealizar/poo_taller7) ENVIADO A 222222

=== VISUALIZACION DE MENSAJES ===
Mensaje 1:
De: 0988888888
Para: 0999999999
Hola, Como estas?

Mensaje 2:
De: Juan 444
Para: Iker 777
Hermano, si jugamos hoy?

Mensaje 3:
De: Iker 124
Para: Emergencias Loja 911
C://Mi_Emergencia/2024/Emergencia1

Mensaje 4:
De: 111
Para: 222222
C://tareas/tarearealizar/poo_taller7


=== INFORMACION COMPLETA ===
1. SMS{mensaje='Hola, Como estas?', remitente= 0988888888, destinatario= 0999999999}
2. SMS{mensaje='Hermano, si jugamos hoy?', remitente=Juan 444, destinatario=Iker 777}
3. MMS{fichero='C://Mi_Emergencia/2024/Emergencia1', remitente=Iker 124, destinatario=Emergencias Loja 911}
4. MMS{fichero='C://tareas/tarearealizar/poo_taller7', remitente= 111, destinatario= 222222}

=== ESTADISTICAS ===
Total mensajes: 4
Tipos: SMS=2, MMS=2
BUILD SUCCESSFUL (total time: 0 seconds)

*/