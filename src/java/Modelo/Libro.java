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
public class Libro {
    
    private int idLibro;
    private String titulo;
    private String autor;
    private String isbn;
    private String genero;
    private String fecha_publicacion;
    private int seccion_id;
    private int ejemplares;

    public Libro() {
    }

    public Libro(String titulo, String autor, String isbn, String genero, String fecha_publicacion, int seccion_id, int ejemplares) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.genero = genero;
        this.fecha_publicacion = fecha_publicacion;
        this.seccion_id = seccion_id;
        this.ejemplares = ejemplares;
    }

    public int getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(int ejemplares) {
        this.ejemplares = ejemplares;
    }

    public int getSeccion_id() {
        return seccion_id;
    }

    public void setSeccion_id(int seccion_id) {
        this.seccion_id = seccion_id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(String fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }
      
    public HashMap toHashMap(){
        HashMap libro = new HashMap();
        libro.put("id", this.idLibro);
        libro.put("titulo", this.titulo);
        libro.put("autor", this.autor);
        libro.put("isbn", this.isbn);
        libro.put("genero", this.genero);
        libro.put("fecha_publicacion", this.fecha_publicacion);
        libro.put("seccion_id", this.seccion_id);
        libro.put("ejemplares", this.ejemplares);
        return libro;
    }
    
    
}
