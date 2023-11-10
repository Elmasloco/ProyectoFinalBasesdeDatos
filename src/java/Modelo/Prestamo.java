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
    private String estado;
    private int libro_id;
    private int persona_id;

    public Prestamo(int id_prestamo, String fecha_prestamo, String fecha_devolucion, String estado, int libro_id, int persona_id) {
        this.id_prestamo = id_prestamo;
        this.fecha_prestamo = fecha_prestamo;
        this.fecha_devolucion = fecha_devolucion;
        this.estado = estado;
        this.libro_id = libro_id;
        this.persona_id = persona_id;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
    
    public HashMap toHashMap(){
        HashMap prestamo = new HashMap();
        prestamo.put("id", this.id_prestamo);
        prestamo.put("fecha_prestamo", this.fecha_prestamo);
        prestamo.put("fecha_devolucion", this.fecha_devolucion);
        prestamo.put("estado", this.estado);
        prestamo.put("libro_id", this.libro_id);
        prestamo.put("persona_id", this.persona_id);
        return prestamo;
    }
    
    
}