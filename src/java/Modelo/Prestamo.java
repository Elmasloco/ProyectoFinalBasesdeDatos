/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.HashMap;

/**
 *
 * @author samue
 */
public class Prestamo {

    private int id_prestamo;
    private String fecha_prestamo;
    private String fecha_devolucion;
    private int libro_id;
    private int persona_id;
    private boolean devuelto;
    private String tituloLibro;

    public Prestamo(int id_prestamo, String fecha_prestamo, String fecha_devolucion, int libro_id, int persona_id, boolean devolver) {
        this.id_prestamo = id_prestamo;
        this.fecha_prestamo = fecha_prestamo;
        this.fecha_devolucion = fecha_devolucion;
        this.libro_id = libro_id;
        this.persona_id = persona_id;
        this.devuelto = devolver;
    }

    public Prestamo(int id_prestamo, String fecha_prestamo, String fecha_devolucion, int libro_id, int persona_id, boolean devuelto, String tituloLibro) {
        this.id_prestamo = id_prestamo;
        this.fecha_prestamo = fecha_prestamo;
        this.fecha_devolucion = fecha_devolucion;
        this.libro_id = libro_id;
        this.persona_id = persona_id;
        this.devuelto = devuelto;
        this.tituloLibro = tituloLibro;
    }

    public int getId_prestamo() {
        return id_prestamo;
    }

    public void setId_prestamo(int id_prestamo) {
        this.id_prestamo = id_prestamo;
    }

    public String getFecha_prestamo() {
        return fecha_prestamo;
    }

    public void setFecha_prestamo(String fecha_prestamo) {
        this.fecha_prestamo = fecha_prestamo;
    }

    public String getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(String fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    public int getLibro_id() {
        return libro_id;
    }

    public void setLibro_id(int libro_id) {
        this.libro_id = libro_id;
    }

    public int getPersona_id() {
        return persona_id;
    }

    public void setPersona_id(int persona_id) {
        this.persona_id = persona_id;
    }

    public boolean isDevuelto() {
        return devuelto;
    }

    public void setDevuelto(boolean devuelto) {
        this.devuelto = devuelto;
    }

    public String getTituloLibro() {
        return tituloLibro;
    }

    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
    }

    public HashMap toHashMap() {
        HashMap prestamo = new HashMap();
        prestamo.put("id", this.id_prestamo);
        prestamo.put("fecha_prestamo", this.fecha_prestamo);
        prestamo.put("fecha_devolucion", this.fecha_devolucion);
        prestamo.put("libro_id", this.libro_id);
        prestamo.put("persona_id", this.persona_id);
        prestamo.put("devolver", this.devuelto);
        prestamo.put("titulo", this.tituloLibro);
        return prestamo;
    }

}
