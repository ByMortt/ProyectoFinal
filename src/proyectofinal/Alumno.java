/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal;

/**
 *
 * @author osamc
 */

/**
 * 
 * clase que representa a un alumno y los datos que contiene
 */
public class Alumno {
    private String nombre; // Nombre del alumno
    private String apPaterno; // Apellido paterno del alumno
    private String apMaterno; // Apellido materno del alumno
    private String direccion; // Dirección del alumno
    private int numCuenta; // Número de cuenta del alumno
    private int numInscripcion; // Número de inscripción del alumno
    private int semestre; // Semestre actual del alumno
    private int creditos; // Créditos cursados por el alumno
    private int edad; // Edad del alumno

    public Alumno() {
    }

    /**
     * Constructor de la clase Alumno
     * 
     * @param nombre          Nombre del alumno
     * @param apPaterno       Apellido paterno del alumno
     * @param apMaterno       Apellido materno del alumno
     * @param direccion       Dirección del alumno
     * @param numCuenta       Número de cuenta del alumno
     * @param numInscripcion  Número de inscripción del alumno
     * @param semestre        Semestre actual del alumno
     * @param creditos        Créditos cursados por el alumno
     * @param edad            Edad del alumno
     */
    public Alumno(String nombre, String apPaterno, String apMaterno, String direccion, int numCuenta, int numInscripcion, int semestre, int creditos, int edad) {
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.direccion = direccion;
        this.numCuenta = numCuenta;
        this.numInscripcion = numInscripcion;
        this.semestre = semestre;
        this.creditos = creditos;
        this.edad = edad;
    }

    /**
     * Obtiene el nombre del alumno
     * 
     * @return Nombre del alumno
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del alumno
     * 
     * @param nombre Nombre del alumno
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido paterno del alumno
     * 
     * @return Apellido paterno del alumno
     */
    public String getApPaterno() {
        return apPaterno;
    }

    /**
     * Establece el apellido paterno del alumno
     * 
     * @param apPaterno Apellido paterno del alumno
     */
    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    /**
     * Obtiene el apellido materno del alumno
     * 
     * @return Apellido materno del alumno
     */
    public String getApMaterno() {
        return apMaterno;
    }

    /**
     * Establece el apellido materno del alumno
     * 
     * @param apMaterno Apellido materno del alumno
     */
    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    /**
     * Obtiene la dirección del alumno
     * 
     * @return Dirección del alumno
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del alumno
     * 
     * @param direccion Dirección del alumno
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene el número de cuenta del alumno
     * 
     * @return Número de cuenta del alumno
     */
    public int getNumCuenta() {
        return numCuenta;
    }

    /**
     * Establece el número de cuenta del alumno
     * 
     * @param numCuenta Número de cuenta del alumno
     */
    public void setNumCuenta(int numCuenta) {
        this.numCuenta = numCuenta;
    }

    /**
     * Obtiene el número de inscripción del alumno
     * 
     * @return Número de inscripción del alumno
     */
    public int getNumInscripcion() {
        return numInscripcion;
    }

    /**
     * Establece el número de inscripción del alumno
     * 
     * @param numInscripcion Número de inscripción del alumno
     */
    public void setNumInscripcion(int numInscripcion) {
        this.numInscripcion = numInscripcion;
    }

    /**
     * Obtiene el semestre actual del alumno
     * 
     * @return Semestre actual del alumno
     */
    public int getSemestre() {
        return semestre;
    }

    /**
     * Establece el semestre actual del alumno
     * 
     * @param semestre Semestre actual del alumno
     */
    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    /**
     * Obtiene los créditos cursados por el alumno
     * 
     * @return Créditos cursados por el alumno
     */
    public int getCreditos() {
        return creditos;
    }

    /**
     * Establece los créditos cursados por el alumno
     * 
     * @param creditos Créditos cursados por el alumno
     */
    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    /**
     * Obtiene la edad del alumno
     * 
     * @return Edad del alumno
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Establece la edad del alumno
     * 
     * @param edad Edad del alumno
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Devuelve una representación en cadena de texto del objeto Alumno
     * 
     * @return Representación en cadena de texto del objeto Alumno
     */
    @Override
    public String toString() {
        return nombre + "," + apPaterno + "," + apMaterno + "," + direccion + "," + numCuenta + "," + numInscripcion + "," + semestre + "," + creditos + "," + edad;
    }
}
