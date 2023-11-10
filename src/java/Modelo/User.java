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
public class User extends Persona{

    public User() {
    }

    public User(String nombre, String apellido, String direccion, String telefono, String correo) {
        super(nombre, apellido, direccion, telefono, correo);
    }
    
    public HashMap toHashMap(){
        HashMap libro = new HashMap();
        libro.put("id", super.getId());
        libro.put("nombre", super.getNombre());
        libro.put("apellido", super.getApellido());
        libro.put("direccion", super.getDireccion());
        libro.put("telefono", super.getTelefono());
        libro.put("correo", super.getCorreo());
        return libro;
    }
    
    
}