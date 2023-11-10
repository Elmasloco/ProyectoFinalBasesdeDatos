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
public class Admin extends Persona {

    private String contraseña;

    public Admin() {
    }

    public Admin(String nombre, String apellido, String direccion, String telefono, String correo, String contraseña) {
        super(nombre, apellido, direccion, telefono, correo);
        this.contraseña = contraseña;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public HashMap toHashMap() {
        HashMap admin = new HashMap();
        admin.put("id", super.getId());
        admin.put("nombre", super.getNombre());
        admin.put("apellido", super.getApellido());
        admin.put("direccion", super.getDireccion());
        admin.put("telefono", super.getTelefono());
        admin.put("correo", super.getCorreo());
        admin.put("contraseña", this.contraseña);
        return admin;
    }

}
