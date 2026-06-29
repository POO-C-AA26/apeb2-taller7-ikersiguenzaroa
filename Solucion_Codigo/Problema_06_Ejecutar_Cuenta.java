/**
 El banco UN BANCO mantiene las cuentas de varios clientes. 
Los datos que describen a cada una de las cuentas consisten en el número de 
cuenta, el nombre del cliente y el balance actual. Escriba una clase para 
implementar dicha cuenta bancaria. El método constructor debe aceptar como 
parámetros el número de cuenta y el nombre. Debe proporcionarse métodos para 
depositar o retirar una cantidad de dinero y obtener el balance actual.
El banco ofrece a sus clientes dos tipos de cuentas, una de CHEQUES y una de 
AHORROS. Una cuenta de cheques puede sobregirarse (el balance puede ser menor 
que cero), pero una cuenta de ahorros no. Al final de cada mes, se calcula el 
interés sobre la cantidad que tenga la cuenta de ahorros. Este interés se suma
al balance. Escriba clases para describir cada uno de estos tipos de cuentas, 
haciendo un máximo uso de la herencia. La clase de la cuenta de ahorros debe 
proporcionar un método que sea invocado para calcular el interés. Además, el 
banco está pensando en implementar una cuenta PLATINO que viene siendo similar 
a los otros dos tipos anteriores de cuentas bancarias, ésta tiene el interés 
del 10%, sin cargos ni castigos por sobregiro. 
 * @author Iker
 */
class Cuenta {

    private int numeroCuenta;
    private String nombreCliente;
    private double balanceActual;

    public Cuenta(int numeroCuenta, String nombreCliente) {
        this.numeroCuenta = numeroCuenta;
        this.nombreCliente = nombreCliente;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public double getBalanceActual() {
        return balanceActual;
    }

    public void setBalanceActual(double balanceActual) {
        this.balanceActual = balanceActual;
    }

    public void depositar(double monto) {
        this.balanceActual += monto;
    }

    public void retirarDinero(double monto) {
        if (monto > 0 && monto <= balanceActual) {
            this.balanceActual -= monto;
        }
    }

    @Override
    public String toString() {
        return "Cuenta{" + "numeroCuenta=" + numeroCuenta + ", nombreCliente=" + nombreCliente + ", balanceActual=" + balanceActual + '}';
    }

}

class Cheque extends Cuenta {

    private double sobregiro;

    public Cheque(double sobregiro, int numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
        this.sobregiro = sobregiro;
    }

    public double getSobregiro() {
        return sobregiro;
    }

    @Override
    public void retirarDinero(double monto) {
        if (monto > 0 && (getBalanceActual() - monto) >= -sobregiro) {
            setBalanceActual(getBalanceActual() - monto);
        }
    }

    @Override
    public String toString() {
        return super.toString() + " Cheque{" + "sobregiro=" + sobregiro + '}';
    }

}

class Ahorro extends Cuenta {

    private double interes;

    public Ahorro(double interes, int numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
        this.interes = interes;
    }

    public void calcularInteres() {
        double aux;
        aux = (this.getBalanceActual() * (this.interes / 100));
        setBalanceActual(this.getBalanceActual() + aux);
    }

    @Override
    public String toString() {
        return super.toString() + " Ahorro{" + "interes=" + interes + '}';
    }

}

class Platino extends Cuenta {

    private double interes = 10;

    public Platino(int numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
    }

    public void calcularInteres() {
        double aux;
        aux = (this.getBalanceActual() * (this.interes / 100));
        setBalanceActual(getBalanceActual() + aux);
    }

    @Override
    public void retirarDinero(double monto) {
        if (monto > 0) {
            setBalanceActual(getBalanceActual() - monto);
        }
    }

    @Override
    public String toString() {
        return super.toString() + " Platino{" + "interes=" + interes + '}';
    }

}

public class Problema_06_Ejecutar_Cuenta {

    public static void main(String[] args) {
        Cheque cheque = new Cheque(500, 1, "Iker");
        cheque.depositar(1000);
        cheque.retirarDinero(400);
        System.out.println(cheque);

        Ahorro ahorro = new Ahorro(20, 2, "Daniel");
        ahorro.depositar(1000);
        ahorro.calcularInteres();
        ahorro.retirarDinero(500);
        System.out.println(ahorro);

        Platino platino = new Platino(3, "Raul");
        platino.depositar(1000);
        platino.retirarDinero(600);
        System.out.println(platino);

    }
}
/*
run:
Cuenta{numeroCuenta=1, nombreCliente=Iker, balanceActual=600.0} Cheque{sobregiro=500.0}
Cuenta{numeroCuenta=2, nombreCliente=Daniel, balanceActual=700.0} Ahorro{interes=20.0}
Cuenta{numeroCuenta=3, nombreCliente=Raul, balanceActual=400.0} Platino{interes=10.0}
BUILD SUCCESSFUL (total time: 0 seconds)
*/