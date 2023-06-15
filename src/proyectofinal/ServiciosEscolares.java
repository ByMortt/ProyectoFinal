/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal;

/**
 *
 * @author osamc
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.text.SimpleDateFormat;

/**
 * 
 * clase qque representa las funciones y utilidades generales del programa
 */

public class ServiciosEscolares {
    private static final String NOMBRES_FILE = "Nombres.txt";
    private static final String APELLIDOS_FILE = "Apellidos.txt";
    private static final String DIRECCIONES_FILE = "DireccionAlumno.csv";
    private static final int NUM_ALUMNOS = 500;
    private static final int NUM_MATERIAS = 50;
    private static final int CREDITOS_POR_MATERIA = 5;
    private static final int NUM_MATERIAS_POR_SEMESTRE = 5;
    private static final int EDAD_MINIMA = 18;
    private static final int EDAD_MAXIMA = 27;
    private static final int NUM_CUENTA_DIGITOS = 9;

    private final List<Alumno> listaAlumnos;

    /**
     * Constructor de la clase ServiciosEscolares
     */
    public ServiciosEscolares() {
        listaAlumnos = new ArrayList<>();
    }

    /**
     * Genera la lista de alumnos con datos aleatorios
     */
    public void generarListaAlumnos() {
        List<String> nombres = leerArchivo(NOMBRES_FILE);
        List<String> apellidos = leerArchivo(APELLIDOS_FILE);
        List<String> direcciones = leerArchivo(DIRECCIONES_FILE);

        Random random = new Random();
        for (int i = 0; i < NUM_ALUMNOS; i++) {
            String[] nombreArray = nombres.get(random.nextInt(nombres.size())).split(",");
            String nombre = nombreArray[random.nextInt(50)];
            String[] apellidoArray = apellidos.get(random.nextInt(apellidos.size())).split(",");
            String apPaterno = apellidoArray[random.nextInt(50)];
            String apMaterno = apellidoArray[random.nextInt(50)];
            String[] direccionArray = direcciones.get(random.nextInt(nombres.size())).split(",");
            String direccion = direccionArray[0];
            int numCuenta = generarNumeroCuenta();
            int semestre = random.nextInt(10) + 1;
            int creditos = semestre * NUM_MATERIAS_POR_SEMESTRE * CREDITOS_POR_MATERIA;
            int edad = random.nextInt(EDAD_MAXIMA - EDAD_MINIMA + 1) + EDAD_MINIMA;

            int numMateriasCursadas = semestre * NUM_MATERIAS_POR_SEMESTRE;
            int numMateriasAcreditadas = random.nextInt(numMateriasCursadas + 1);

            double promedio = random.nextDouble() * 10.0;
            double numCreditosTotales = 10 * NUM_MATERIAS_POR_SEMESTRE * CREDITOS_POR_MATERIA;
            double numMateriasTotales = semestre * NUM_MATERIAS_POR_SEMESTRE;

            double numInscripcion = promedio * ((creditos / numCreditosTotales) * 100)
                    * ((numMateriasAcreditadas / numMateriasTotales) * 100);

            Alumno alumno = new Alumno(nombre, apPaterno, apMaterno, direccion, numCuenta, (int) numInscripcion, semestre, creditos, edad);
            listaAlumnos.add(alumno);
        }
    }

    private List<String> leerArchivo(String nombreArchivo) {
        List<String> lineas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return lineas;
    }

    private int generarNumeroCuenta() {
        Random random = new Random();
        StringBuilder numeroCuentaBuilder = new StringBuilder();

        for (int i = 0; i < NUM_CUENTA_DIGITOS; i++) {
            int digito = random.nextInt(10);
            numeroCuentaBuilder.append(digito);
        }

        return Integer.parseInt(numeroCuentaBuilder.toString());
    }

    /**
     * Devuelve la lista de alumnos
     *
     * @return lista de alumnos
     */
    public List<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }

    /**
     * Ordena la lista de alumnos por número de inscripción utilizando el algoritmo Quicksort.
     * También actualiza el número de inscripción basado en la posición en la lista.
     */
    public void ordenarListaAlumnosPorInscripcion() {
        quicksort(0, listaAlumnos.size() - 1);
        // Actualizar el número de inscripción basado en la posición en la lista
        for (int i = 0; i < listaAlumnos.size(); i++) {
            Alumno alumno = listaAlumnos.get(i);
            alumno.setNumInscripcion(i + 1);
        }
    }

    private void quicksort(int inicio, int fin) {
        if (inicio < fin) {
            int indiceParticion = particion(inicio, fin);
            quicksort(inicio, indiceParticion - 1);
            quicksort(indiceParticion + 1, fin);
        }
    }

    private int particion(int inicio, int fin) {
        Alumno pivote = listaAlumnos.get(fin);
        int indiceMenor = inicio - 1;

        for (int i = inicio; i < fin; i++) {
            if (listaAlumnos.get(i).getNumInscripcion() < pivote.getNumInscripcion()) {
                indiceMenor++;
                intercambiar(indiceMenor, i);
            }
        }

        intercambiar(indiceMenor + 1, fin);
        return indiceMenor + 1;
    }

    private void intercambiar(int i, int j) {
        Alumno temp = listaAlumnos.get(i);
        listaAlumnos.set(i, listaAlumnos.get(j));
        listaAlumnos.set(j, temp);
    }

    /**
     * Busca un alumno por número de cuenta y devuelve el alumno correspondiente.
     *
     * @param numeroCuentaBuscado número de cuenta del alumno buscado
     * @return alumno encontrado o null si no se encontró
     */
    public Alumno buscarAlumnoPorNumeroCuenta(int numeroCuentaBuscado) {
        for (Alumno alumno : listaAlumnos) {
            if (alumno.getNumCuenta() == numeroCuentaBuscado) {
                return alumno;
            }
        }
        return null;
    }

    private int busquedaBinaria(int numeroCuenta) {
        int inicio = 0;
        int fin = listaAlumnos.size() - 1;

        while (inicio <= fin) {
            int medio = inicio + (fin - inicio) / 2;
            Alumno alumno = listaAlumnos.get(medio);
            int numeroCuentaAlumno = alumno.getNumCuenta();

            if (numeroCuenta == numeroCuentaAlumno) {
                return medio;
            } else if (numeroCuenta < numeroCuentaAlumno) {
                fin = medio - 1;
            } else {
                inicio = medio + 1;
            }
        }

        return -1; // No se encontró el alumno
    }

    /**
     * Busca alumnos por nombre y devuelve una lista de los alumnos encontrados.
     *
     * @param nombre nombre o parte del nombre a buscar
     * @return lista de alumnos encontrados
     */
    public List<Alumno> buscarAlumnoPorNombre(String nombre) {
        String nombreBuscado = nombre.toLowerCase(); // Convertir a minúsculas
        List<Alumno> resultados = new ArrayList<>();

        for (Alumno alumno : listaAlumnos) {
            if (alumno.getNombre().toLowerCase().contains(nombreBuscado)) {
                resultados.add(alumno);
            }
        }

        return resultados;
    }

    /**
     * Modifica los datos de un alumno existente.
     *
     * @param numeroCuenta      número de cuenta del alumno a modificar
     * @param nuevoNombre       nuevo nombre del alumno
     * @param nuevoApPaterno    nuevo apellido paterno del alumno
     * @param nuevoApMaterno    nuevo apellido materno del alumno
     * @param nuevaDireccion    nueva dirección del alumno
     * @param nuevaEdad         nueva edad del alumno
     */
    public void modificarDatosAlumno(int numeroCuenta, String nuevoNombre, String nuevoApPaterno,
                                     String nuevoApMaterno, String nuevaDireccion, int nuevaEdad) {
        Alumno alumno = buscarAlumnoPorNumeroCuenta(numeroCuenta);
        if (alumno != null) {
            alumno.setNombre(nuevoNombre);
            alumno.setApPaterno(nuevoApPaterno);
            alumno.setApMaterno(nuevoApMaterno);
            alumno.setDireccion(nuevaDireccion);
            alumno.setEdad(nuevaEdad);
            System.out.println("Datos del alumno modificados exitosamente.");
        } else {
            System.out.println("No se encontró un alumno con el número de cuenta proporcionado.");
        }
    }

    /**
     * Crea un archivo CSV con los datos de los alumnos.
     */
    public void crearArchivoDatosAlumnoCSV() {
        String formatoFechaHora = "yyyyMMdd_HHmm";
        SimpleDateFormat formateador = new SimpleDateFormat(formatoFechaHora);
        String fechaHoraActual = formateador.format(new Date());

        String nombreArchivo = "Datos Alumnos " + fechaHoraActual + ".csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            // Escribir encabezados
            writer.write("Nombre,Apellido Paterno,Apellido Materno,Dirección,Número de Cuenta,Número de Inscripción,Semestre,Créditos,Edad");
            writer.newLine();

            // Escribir datos de los alumnos
            for (Alumno alumno : listaAlumnos) {
                writer.write(alumno.toString());
                writer.newLine();
            }

            System.out.println("Archivo " + nombreArchivo + " creado exitosamente.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
