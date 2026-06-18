# Taller 7 - DOO e implementación de soluciones en UML y Java, basados en Herencia

## Análisis, diseño y codificación de soluciones en escenarios/problemas de la vida real, por medio de una jerarquía de herencia con diagramas UML y el lenguaje de alto nivel Java.

* Leer detenidamente cada problemática propuesta.
* Usar el programa **DIA-UML** _(Open source)_ para generar la representación de su solución _(modelado)_, vía diagramas de clases.
* Para cada diagrama/solución _(modelado de su solución)_, genere/agregue 2 archivos _(fuente e img: \*.dia y \*.png \*.jpeg, etc)_. No olvide titular a cada clase, con el nombre representativo del análisis/solución. Para el nombre de cada archivo use el formato: _Problema-NroProbl_NombClase_. Ejemplo **Problema-1_Trabajador**. Todos estos archivos agréguelos en el subDirectorio: **Modelos_UML**
* En el subDirectorio **Solucion_Codigo** cree un único proyecto NetBeans - _Java Aplication_ (Proyect_Taller07) y en él, agregue todas las clases necesarias para la solución de cada problema _(no use paquetes aun)_. Y para facilitar la revisión, utilice para la clase base el formato _Problema_NroProbl_NombClaseBase_, y para la clase de prueba/ejecutor use _Problema_NroProbl_NombClaseEjecutor_ (Ésta última es la clase de prueba que genera/instancia con objetos cada entidad/clase y verifica su funcionalidad). 
* Respete la arquitectura **MVC**, es decir, no implemente entradas/salidas desde/hacia teclado-consola directamente en la clase base. Los datos de entrada y resultados deben ser ingresados/mostrados desde/hacia teclado-consola, en la clase de prueba/ejecutor.
* Para retornar los datos del objeto, usar el método _**toString()**_, vía cadena con formato legible. 
* Al diseñar su solución, usted tiene la potestad de elegir usar estructuras de datos estáticas y/o dinámicas así como el aprovechamiento de la herencia. 
* No olvide que la evaluación de este taller es entre pares académicos, sigue las instrucciones del Docente para este fin. 

___

## Problema 1 - Jerarquía de clases para el capítulo de libro

Dibuje un diagrama de clases que muestre la estructura de un **capítulo de libro**; un capítulo está compuesto por varias **secciones**, cada una de las cuales comprende varios **párrafos** y **figuras**. Un párrafo incluye varias **sentencias**, cada una de las cuales contiene varias **palabras**.

> [!Note]
> - Suponga que en un futuro se prevé que el sistema gestione además de párrafos y figuras otros componentes, como tablas, listas, viñetas, etc.
> - Suponga además que una palabra puede aparecer en varias sentencias.


## Problema 2 - Alquiler de peliculas

Un videoclub dispone de una serie de películas que pueden estar en **DVD** _(con capacidad en Gb.)_ o en **VHS** _(una sola cinta por película y con cierto tipo de cinta magnetica)_. De las películas interesa guardar el título, el autor, el año de edición y el idioma _(o los idiomas, en caso de DVD)_. El precio de alquiler de las películas varía en función del tipo de película. Las **DVD** siempre son 10% mas caras que las de **VHS**.

> [!Note]
> - Analice los tipos de relación de las siguientes posibles clases: Pelicula, Dvd, Vhs, Soporte, etc, y justifique su diseño.
> - Para probar el diseño jerarquico de clases, instancia en el clase de prueba Ejecutor _(la-s clase-s respectiva-s)_, con datos aleatorios.  
> - Los escenarios de prueba pueden darse para el alquiler de una o varias peliculas según la preferencia del usuario. 

## Problema 3 - Sistema de envío de mensajes a móviles

Implemente un sistema de envío de mensajes a móviles. Existen dos tipos de mensajes que se pueden enviar entre móviles, **_mensajes de texto (SMS)_** y **_mensajes que contienen imágenes (MMS)_**. Por un lado, los mensajes de texto contienen un mensaje en caracteres que se desea enviar de un móvil a otro. Por otro lado, los mensajes que contienen imágenes almacenan información sobre la imagen a enviar, la cual se representará por el nombre del fichero que la contiene. Independientemente del tipo de mensaje, cada mensaje tendrá asociado un remitente de dicho mensaje y un destinatario. Ambos estarán definidos obligatoriamente por un número de móvil, y opcionalmente se podrá guardar información sobre su nombre. Además, los métodos enviarMensaje y visualizarMensaje deben estar definidos.

> [!Note]
> - Para probar el diseño jerarquico de clases, instancia en el clase de prueba Ejecutor, con datos ficticios. 

## Problema 4 - Sistema de nómina para trabajadores

Se desea desarrollar un sistema de nómina para los trabajadores de una empresa. Los datos personales de los trabajadores son nombre y apellidos, dirección y DNI. Además, existen diferentes tipos de trabajadores:

- **Fijos Mensuales**: que cobran una cantidad fija al mes.
- **Comisionistas**: cobran un porcentaje fijo por las ventas que han realizado
- **Por Horas**: cobran un precio por cada una de las horas que han realizado durante el mes. El precio es fijo para las primeras 40 horas y es otro para las horas realizadas a partir de la 40 hora mensual.
- **Jefe**: cobra un sueldo fijo _(no hay que calcularlo)_. Cada empleado tiene obligatoriamente un jefe _(exceptuando los jefes que no tienen ninguno)_. El programa debe permitir dar de alta a trabajadores, así como fijar horas o ventas realizadas e imprimir la nómina correspondiente al final de mes.

> [!Note]
> - Diseñe la jerarquia de clases UML basado en herencia, que defina de mejor forma el escenario planteado. 
> - Para probar el diseño de clases, instancia en el clase de prueba Ejecutor _(la-s clase-s respectiva-s)_, con datos aleatorios. 
> - En los escenarios de prueba verifique su solución con al menos 2 tipos de trabajadores. 

## Problema 5 - Venta de entradas al teatro

Dadas las siguientes clases, que expresan una relación de herencia entre las entidades: 

Se desea gestionar la venta de entradas para un espectáculo en un teatro. El patio de butacas del teatro se divide en varias zonas, cada una identificada por su nombre. Los datos de las zonas son los mostrados en la siguiente tabla:

| NOMBRE ZONA     | NÚMERO DE LOCALIDADES | PRECIO NORMA | PRECIO ABONADO  |
| --------------- | --------------------- | ------------ | --------------- |
| Principal       | 200                   | 25$          | 17.5$           |
| PalcoB          | 40                    | 70$          | 40$             |
| Central         | 400                   | 20$          | 14$             |
| Lateral         | 100                   | 15.5$        | 10$             |

Para realizar la compra de una entrada, un espectador debe indicar la zona que desea y presentar al vendedor el documento que justifique que tiene algún tipo de descuento _(estudiante, abonado o pensionista)_. El vendedor sacará la entrada del tipo apropiado y de la zona indicada, en el momento de la compra se asignará a la entrada un identificador _(un número entero)_ que permitirá la identificación de la entrada en todas las operaciones que posteriormente se desee realizar con ella.

Una entrada tiene como datos asociados su identificador, la zona a la que pertenece y el nombre del comprador. 

Los precios de las entradas dependen de la zona y del tipo de entrada según lo explicado a continuación:

- **Entradas normales**: su precio es el precio normal de la zona elegida sin ningún tipo de descuento.
- **Entradas reducidas** (para estudiantes o pensionistas): su precio tiene una rebaja del 15% sobre el precio normal de la zona elegida.
- **Entradas abonado**: su precio es el precio para abonados de la zona elegida.

La interacción entre el vendedor y la aplicación es la descrita en los siguientes casos de usos.

> [!Note]
> Caso de uso “Vende entrada”:
> 
> 1.	El vendedor elige la opción “vende entrada” e introduce la zona deseada, el nombre del espectador y el tipo (normal, abonado o beneficiario de entrada reducida).
> 2.	Si la zona elegida no está completa, la aplicación genera una nueva entrada con los datos facilitados.
> 
> 	- Si no existe ninguna zona con ese nombre, se notifica y finaliza el caso de uso sin generar la entrada.
> 	- Si la zona elegida está completa lo notifica y finaliza el caso de uno sin generar la entrada.
> 
> 3.	La aplicación muestra el identificador y el precio de la entrada.
> 
> Caso de uso “Consulta entrada”:
> 
> 1.	El vendedor elige la opción “consulta entrada” e introduce el identificador de la entrada.
> 2.	La aplicación muestra los datos de la entrada: nombre del espectador, precio y nombre de la zona. Si no existe ninguna entrada con ese identificador, lo notifica y finaliza el caso de uso


## Problema 6 - Sistema Un Banco

El banco **UN BANCO** mantiene las cuentas de varios clientes. Los datos que describen a cada una de las cuentas consisten en el número de cuenta, el nombre del cliente y el balance actual. Escriba una clase para implementar dicha cuenta bancaria. El método constructor debe aceptar como parámetros el _número de cuenta_ y el _nombre_. Debe proporcionarse métodos para depositar o retirar una cantidad de dinero y obtener el _balance actual_. 

El banco ofrece a sus clientes dos tipos de cuentas, una de **CHEQUES** y una de **AHORROS**. Una cuenta de cheques puede sobregirarse _(el balance puede ser menor que cero)_, pero una cuenta de ahorros no. Al final de cada mes, se calcula el _interés_ sobre la cantidad que tenga la cuenta de ahorros. Este interés se suma al balance. Escriba clases para describir cada uno de estos tipos de cuentas, haciendo un máximo uso de la herencia. La clase de la cuenta de ahorros debe proporcionar un método que sea invocado para calcular el interés. Además, el banco está pensando en implementar una cuenta **PLATINO** que viene siendo similar a los otros dos tipos anteriores de cuentas bancarias, ésta tiene el interés del 10%, sin cargos ni castigos por sobregiro.

> [!Note]
> - Ud. debe implementar una clase de PRUEBA _(Clase de ejecución)_ desde la cual se pueda evidenciar el correcto funcionamiento de cada clase con n clientes, y que además se pueda mostrar el balance _(estado de cuenta)_ para cada cliente. 
