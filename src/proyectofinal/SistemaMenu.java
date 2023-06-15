/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal;

/**
 *
 * @author osamc
 */
import java.util.List;
import java.util.Scanner;

/**
 * Clase que representa el menú principal del sistema.
 */
public class SistemaMenu {
    private final Scanner scanner;
    private final ServiciosEscolares serviciosEscolares;
    private boolean salir;

    /**
     * Constructor de la clase SistemaMenu.
     * Inicializa el scanner, crea una instancia de ServiciosEscolares, genera la lista de alumnos,
     * ordena la lista por número de inscripción y establece el estado inicial de salir como falso.
     */
    public SistemaMenu() {
        scanner = new Scanner(System.in);
        serviciosEscolares = new ServiciosEscolares();
        serviciosEscolares.generarListaAlumnos();
        serviciosEscolares.ordenarListaAlumnosPorInscripcion();
        salir = false;
    }

    /**
     * Método para ejecutar el menú principal del sistema.
     * Muestra las opciones del menú y maneja la entrada del usuario.
     */
    public void ejecutarMenuPrincipal() {
        // Crear el archivo CSV con la lista de alumnos ordenada
        serviciosEscolares.crearArchivoDatosAlumnoCSV();
        System.out.println("Archivo CSV creado exitosamente.");
        while (!salir) {
            System.out.println("--Sistema de Inscripcion Facultad de Ingenieria--");
            System.out.println("--------------- Menú Principal ..-------------");
            System.out.println("1. Ingresar como Alumno");
            System.out.println("2. Ingresar como Trabajador");
            System.out.println("3. Salir");
            System.out.print("Ingrese su opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> ejecutarMenuAlumno();
                case 2 -> ingresarComoTrabajador();
                case 3 -> {
                    salir = true;
                    System.out.println("Saliendo del programa...");
                }
                default -> System.out.println("Opción inválida. Por favor, ingrese una opción válida.");
            }
        }
    }

    /**
     * Método para ejecutar el menú del alumno.
     * Muestra las opciones del menú del alumno y maneja la entrada del usuario.
     */
    private void ejecutarMenuAlumno() {
        boolean salirMenuAlumno = false;

        while (!salirMenuAlumno) {
            System.out.println("----- Busqueda de Alumno -----");
            System.out.println("1. Buscar Alumno por Nombre");
            System.out.println("2. Buscar Alumno por Número de Cuenta");
            System.out.println("3. Salir");
            System.out.print("Ingrese su opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese el nombre del alumno: ");
                    String nombreBuscado = scanner.nextLine();
                    List<Alumno> buscarAlumnoPorNombre = this.serviciosEscolares.buscarAlumnoPorNombre(nombreBuscado);
                    System.out.println(buscarAlumnoPorNombre);
                }
                case 2 -> {
                    System.out.print("Ingrese el número de cuenta del alumno: ");
                    int numeroCuentaBuscado = scanner.nextInt();
                    Alumno buscarAlumnoPorNumeroCuenta = this.serviciosEscolares.buscarAlumnoPorNumeroCuenta(numeroCuentaBuscado);
                    System.out.println(buscarAlumnoPorNumeroCuenta);
                }
                case 3 -> salirMenuAlumno = true;
                default -> System.out.println("Opción inválida. Por favor, ingrese una opción válida.");
            }
        }
    }

    /**
     * Método para ingresar como trabajador.
     * Solicita el usuario y la contraseña, y verifica si son correctos.
     * Si son correctos, ejecuta el menú del trabajador.
     */
    private void ingresarComoTrabajador() {
        System.out.print("Ingrese el usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Ingrese la contraseña: ");
        String contrasena = scanner.nextLine();

        if (usuario.equals("Admin") && contrasena.equals("Admin")) {
            ejecutarMenuTrabajador();
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }
    }

    /**
     * Método para ejecutar el menú del trabajador.
     * Muestra las opciones del menú del trabajador y maneja la entrada del usuario.
     */
    private void ejecutarMenuTrabajador() {
        boolean salirMenuTrabajador = false;
        while (!salirMenuTrabajador) {
            System.out.println("----- Menú Trabajador -----");
            System.out.println("1. Mostrar Lista de Alumnos");
            System.out.println("2. Buscar Alumno");
            System.out.println("3. Cambiar Datos del Alumno");
            System.out.println("4. Generar nueva base de alumnos");
            System.out.println("5. Realizar copia de seguridad.");
            System.out.println("6. Salir");
            System.out.print("Ingrese su opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.println("Lista de Alumnos:");
                    System.out.println(serviciosEscolares.getListaAlumnos());
                }
                case 2 -> ejecutarMenuAlumno();
                case 3 -> modificarDatosAlumno();
                case 4 -> {
                    serviciosEscolares.generarListaAlumnos();
                    serviciosEscolares.ordenarListaAlumnosPorInscripcion();
                    serviciosEscolares.crearArchivoDatosAlumnoCSV();
                    System.out.println("Nueva base de alumnos generada y guardada correctamente.");
                }
                case 5 -> serviciosEscolares.crearArchivoDatosAlumnoCSV();
                case 6 -> salirMenuTrabajador = true;
                default -> System.out.println("Opción inválida. Por favor, ingrese una opción válida.");
            }
        }
    }

    /**
     * Método para modificar los datos de un alumno.
     * Solicita el número de cuenta del alumno y verifica si existe.
     * Si existe, solicita los nuevos datos y llama al método correspondiente de ServiciosEscolares para modificar los datos del alumno.
     */
    private void modificarDatosAlumno() {
        System.out.print("Ingrese el número de cuenta del alumno: ");
        int numeroCuenta = scanner.nextInt();
        scanner.nextLine();

        Alumno alumno = this.serviciosEscolares.buscarAlumnoPorNumeroCuenta(numeroCuenta);

        if (alumno != null) {
            boolean salirModificarAlumno = false;
            while (!salirModificarAlumno) {
                System.out.println("Seleccione el campo que desea modificar:");
                System.out.println("1. Nombre");
                System.out.println("2. Apellido Paterno");
                System.out.println("3. Apellido Materno");
                System.out.println("4. Dirección");
                System.out.println("5. Edad");
                System.out.println("6. Salir");
                System.out.print("Ingrese su opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1 -> {
                        System.out.print("Ingrese el nuevo nombre del alumno: ");
                        String nuevoNombre = scanner.nextLine();
                        alumno.setNombre(nuevoNombre);
                    }
                    case 2 -> {
                        System.out.print("Ingrese el nuevo apellido paterno del alumno: ");
                        String nuevoApellidoPaterno = scanner.nextLine();
                        alumno.setApPaterno(nuevoApellidoPaterno);
                    }
                    case 3 -> {
                        System.out.print("Ingrese el nuevo apellido materno del alumno: ");
                        String nuevoApellidoMaterno = scanner.nextLine();
                        alumno.setApMaterno(nuevoApellidoMaterno);
                    }
                    case 4 -> {
                        System.out.print("Ingrese la nueva dirección del alumno: ");
                        String nuevaDireccion = scanner.nextLine();
                        alumno.setDireccion(nuevaDireccion);
                    }
                    case 5 -> {
                        System.out.print("Ingrese la nueva edad del alumno: ");
                        int nuevaEdad = scanner.nextInt();
                        scanner.nextLine();
                        alumno.setEdad(nuevaEdad);
                    }
                    case 6 -> salirModificarAlumno = true;
                    default -> {
                        System.out.println("Opción inválida. No se realizaron modificaciones.");
                    }
                }    
            }
            serviciosEscolares.crearArchivoDatosAlumnoCSV();
            System.out.println("Los datos del alumno han sido modificados y guardados correctamente.");
        } 
        else {
                System.out.println("No se encontró ningún alumno con el número de cuenta especificado.");
            }
    }
}
