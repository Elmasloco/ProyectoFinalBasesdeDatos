/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import ConexionSQL.ConexionSQL;
import Modelo.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author samue
 */
public class PersonaDAO {

    public ConexionSQL con = new ConexionSQL();
    public Connection cn = con.abrirConexion();
    private PreparedStatement ps;
    private ResultSet rs;
    private String consultaSQL = "";

    public PersonaDAO() {
    }

    public void insertarUsuario(User user) {
        consultaSQL = "INSERT INTO biblioteca.persona(nombre, apellido, direccion, telefono, correo, contraseña) VALUES(?,?,?,?,?,?)";
        try {
            ps = cn.prepareStatement(consultaSQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getNombre());
            ps.setString(2, user.getApellido());
            ps.setString(3, user.getDireccion());
            ps.setString(4, user.getTelefono());
            ps.setString(5, user.getCorreo());
            ps.setString(6, "null");
            int registrosAfectados = ps.executeUpdate();
            if (registrosAfectados == 1) {
                System.out.println("Usuario insertado correctamente!");
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    user.setId(id);
                } else {
                    System.out.println("Error al crear la persona");
                }
            } else {
                System.out.println("Error al insertar el usuario");
            }
            insertarRol("Usuario", user.getId());
            con.cerrarConexion();
        } catch (SQLException e) {
            System.out.println("Error al insertar registro: " + e);
        }
    }

    public void insertarAdmin(Admin admin) {
        consultaSQL = "INSERT INTO biblioteca.persona(nombre, apellido, direccion, telefono, correo, contraseña) VALUES(?,?,?,?,?,?)";
        try {
            ps = cn.prepareStatement(consultaSQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, admin.getNombre());
            ps.setString(2, admin.getApellido());
            ps.setString(3, admin.getDireccion());
            ps.setString(4, "null");
            ps.setString(5, "null");
            ps.setString(6, admin.getContraseña());
            int registrosAfectados = ps.executeUpdate();
            if (registrosAfectados == 1) {
                System.out.println("Administrador insertado correctamente!");
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    admin.setId(id);
                } else {
                    System.out.println("Error al crear el administrador");
                }
            } else {
                System.out.println("Error al insertar admin");
            }
            insertarRol("Administrador", admin.getId());
            con.cerrarConexion();
        } catch (SQLException e) {
            System.out.println("Error al insertar admin: " + e);
        }
    }

    public void insertarLibro(Libro libro) {
        consultaSQL = "INSERT INTO biblioteca.libro(titulo, autor, isbn, genero, fecha_publicacion, ejemplares, seccion_id) VALUES(?,?,?,?,?,?,?)";
        try {
            ps = cn.prepareStatement(consultaSQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setString(3, libro.getIsbn());
            ps.setString(4, libro.getGenero());
            ps.setString(5, libro.getFecha_publicacion());
            ps.setInt(6, libro.getEjemplares());
            ps.setInt(7, libro.getSeccion_id());
            int registrosAfectados = ps.executeUpdate();
            if (registrosAfectados == 1) {
                System.out.println("Libro insertado correctamente!");
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    libro.setIdLibro(id);
                } else {
                    System.out.println("Error al crear el libro");
                }
            } else {
                System.out.println("Error al insertar libro");
            }
            con.cerrarConexion();
        } catch (SQLException e) {
            System.out.println("Error al insertar libro: " + e);
        }
    }

    public void agregarPrestamo(int idUsuario, int idLibro, String fechaP, String fechaD, String estado) {
        consultaSQL = "INSERT INTO biblioteca.prestamo(fecha_prestamo, fecha_devolucion, estado, libro_id, persona_id) VALUES(?,?,?,?,?)";
        try {
            ps = cn.prepareStatement(consultaSQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, fechaP);
            ps.setString(2, fechaD);
            ps.setString(3, estado);
            ps.setInt(4, idLibro);
            ps.setInt(5, idUsuario);
            int registrosAfectados = ps.executeUpdate();
            if (registrosAfectados == 1) {
                System.out.println("Prestamo agredado correctamente!");
            } else {
                System.out.println("Error al agregar prestamo");
            }
            con.cerrarConexion();
        } catch (SQLException e) {
            System.out.println("Error al agregar prestamo: " + e);
        }
    }

    private void insertarRol(String rol, int id) {
        consultaSQL = "INSERT INTO biblioteca.roles(rol, id_persona) VALUES(?,?)";
        try {
            ps = cn.prepareStatement(consultaSQL);
            ps.setString(1, rol);
            ps.setInt(2, id);
            int registrosAfectados = ps.executeUpdate();
            if (registrosAfectados == 1) {
                System.out.println("Rol insertado correctamente!");
            } else {
                System.out.println("Error al insertar rol");
            }
            con.cerrarConexion();
        } catch (SQLException e) {
            System.out.println("Error al insertar rol: " + e);
        }
    }

    public void eliminarPrestamo(int id){
        consultaSQL = "DELETE FROM biblioteca.prestamo WHERE id = ?";
        try{
            ps = cn.prepareStatement(consultaSQL);
            ps.setInt(1, id);
            int registroEliminado = ps.executeUpdate();
            if(registroEliminado == 1){
                System.out.println("Prestamo: "+id+" eliminado correctamente");
            }else{
                System.out.println("Error al eliminar prestamo: "+id);
            }
        con.cerrarConexion();
        }catch(SQLException e){
            System.out.println("Error al eliminar prestamo: "+e);
        }   
    }
  
    public void modificarPersona(int idPersona, Persona persona){
        consultaSQL = "UPDATE biblioteca.persona SET nombre=?, apellido=?, direccion=?, telefono=?, correo=?, contraseña=? WHERE id=?";
        try{
            ps = cn.prepareStatement(consultaSQL);
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getApellido());
            ps.setString(3, persona.getDireccion());
            ps.setString(4, persona.getTelefono());
            ps.setString(5, persona.getCorreo());
            if(persona instanceof Admin){
                ps.setString(6, ((Admin)persona).getContraseña());
            }else{
                ps.setString(6, "null");
            }
            ps.setInt(7, idPersona);
            int registroModificado = ps.executeUpdate();
            if(registroModificado == 1){
                System.out.println("Persona: "+idPersona+" ha sido modificado");
            }else{
                System.out.println("Error al modificar registro: "+idPersona);
            }
        con.cerrarConexion();
        }catch(SQLException e){
            System.out.println("Error al modificar datos del registro: "+e);
        }
    }
    
    public ArrayList<HashMap> mostrarLibros() {
        consultaSQL = "SELECT * FROM biblioteca.libro";
        ArrayList<HashMap> resultado = new ArrayList<>();
        try{
            ps = cn.prepareStatement(consultaSQL);
            rs = ps.executeQuery();
            while(rs.next()){
                int idLibro = rs.getInt("id");
                String tituloLibro = rs.getString("titulo");
                String autorLibro = rs.getString("autor");
                String isbnLibro = rs.getString("isbn");
                String generoLibro = rs.getString("genero");
                String fechaLibro = rs.getString("fecha_publicacion");
                int ejemplaresLibro = rs.getInt("ejemplares");
                int seccionLibro = rs.getInt("seccion_id");
                Libro libro = new Libro(tituloLibro, autorLibro, isbnLibro, generoLibro, fechaLibro, ejemplaresLibro, seccionLibro);
                libro.setIdLibro(idLibro);
                resultado.add(libro.toHashMap());
            }
        con.cerrarConexion();
        }catch(SQLException e){
            System.out.println("Error al leer registros de tabla libro: "+e);
        }
        return resultado;
    }
    
    /*Forma de devolver el resultado de los libros en el metodo mostrarLibros():
        ArrayList<HashMap> librosAlmacenados = pdao.mostrarLibros();
            for (HashMap libroMostrado : librosAlmacenados) {
                System.out.println("Id: " + libroMostrado.get("id") + "\n"
                        + "Titulo: " + libroMostrado.get("titulo") + "\n"
                        + "Autor: " + libroMostrado.get("autor") + "\n"
                        + "ISBN: " + libroMostrado.get("isbn") + "\n"
                        + "Genero: " + libroMostrado.get("genero") + "\n"
                        + "Fecha publicacion: " + libroMostrado.get("fecha_publicacion") + "\n"
                        + "Ejemplares: " + libroMostrado.get("ejemplares") + "\n"
                        + "Seccion: " + libroMostrado.get("seccion_id") + "\n");
            }
     */

    public ArrayList<HashMap> mostrarPersonas() {
        consultaSQL = "SELECT * FROM biblioteca.persona";
        ArrayList<HashMap> resultado = new ArrayList<>();
        try{
            ps = cn.prepareStatement(consultaSQL);
            rs = ps.executeQuery();
            while(rs.next()){
                int idPersona = rs.getInt("id");
                String nombrePersona = rs.getString("nombre");
                String apellidoPersona = rs.getString("apellido");
                String direccionPersona = rs.getString("direccion");
                String telefonoPersona = rs.getString("telefono");
                String correoPersona= rs.getString("correo");
                String contrasenaPersona = rs.getString("contraseña");
                if(contrasenaPersona != null && !contrasenaPersona.isEmpty()){
                    Admin admin = new Admin(nombrePersona, apellidoPersona, direccionPersona, telefonoPersona, correoPersona, contrasenaPersona);
                    admin.setId(idPersona);
                    resultado.add(admin.toHashMap());
                }else{
                    User user = new User(nombrePersona, apellidoPersona, direccionPersona, telefonoPersona, correoPersona);
                    user.setId(idPersona);
                    resultado.add(user.toHashMap());
                }
            }
        con.cerrarConexion();
        }catch(SQLException e){
            System.out.println("Error al leer registros de tabla persona: "+e);
        }
        return resultado;
    }
    
    /*Forma de devolver el resultado de los libros en el metodo mostrarPersonas():
        ArrayList<HashMap> personasAlmacenadas = pdao.mostrarPersonas();
            for (HashMap personas : personasAlmacenadas) {
                System.out.println("Id: " + personas.get("id") + "\n"
                        + "Nombre: " + personas.get("nombre") + "\n"
                        + "Apellido: " + personas.get("apellido") + "\n"
                        + "Direccion: " + personas.get("direccion") + "\n"
                        + "Telefono: " + personas.get("telefono") + "\n"
                        + "Correo: " + personas.get("correo") + "\n"
                        + "Contraseña: " + personas.get("contraseña") + "\n");
            }
     */
    
    public ArrayList<HashMap> mostrarPrestamos(int idUsuario) {
        consultaSQL = "SELECT * FROM biblioteca.prestamo WHERE id="+idUsuario;
        ArrayList<HashMap> resultado = new ArrayList<>();
        try{
            ps = cn.prepareStatement(consultaSQL);
            rs = ps.executeQuery();
            while(rs.next()){
                int idPrestamo = rs.getInt("id");
                String fechaPrestamo = rs.getString("fecha_prestamo");
                String fechaDevolucion = rs.getString("fecha_devolucion");
                String estado = rs.getString("estado");
                int libroID= rs.getInt("libro_id");
                int personaID= rs.getInt("persona_id");
                Prestamo prestamo = new Prestamo(idPrestamo, fechaPrestamo, fechaDevolucion, estado, libroID, personaID);
                resultado.add(prestamo.toHashMap());
            }
        con.cerrarConexion();
        }catch(SQLException e){
            System.out.println("Error al leer registros de tabla persona: "+e);
        }
        return resultado;
    }
    
    public ArrayList<HashMap> mostrarPrestamosAdmin() {
        consultaSQL = "SELECT * FROM biblioteca.prestamo";
        ArrayList<HashMap> resultado = new ArrayList<>();
        try{
            ps = cn.prepareStatement(consultaSQL);
            rs = ps.executeQuery();
            while(rs.next()){
                int idPrestamo = rs.getInt("id");
                String fechaPrestamo = rs.getString("fecha_prestamo");
                String fechaDevolucion = rs.getString("fecha_devolucion");
                String estado = rs.getString("estado");
                int libroID= rs.getInt("libro_id");
                int personaID= rs.getInt("persona_id");
                Prestamo prestamo = new Prestamo(idPrestamo, fechaPrestamo, fechaDevolucion, estado, libroID, personaID);
                resultado.add(prestamo.toHashMap());
            }
        con.cerrarConexion();
        }catch(SQLException e){
            System.out.println("Error al leer registros de tabla persona: "+e);
        }
        return resultado;
    }
    
    /*Forma de devolver el resultado de los libros en el metodo mostrarPrestamos():
        ArrayList<HashMap> personasAlmacenadas = pdao.mostrarPrestamos();
            for (HashMap personas : personasAlmacenadas) {
                System.out.println("Id: " + personas.get("id") + "\n"
                        + "Fecha prestamo: " + personas.get("fecha_prestamo") + "\n"
                        + "Fecha devolucion: " + personas.get("fecha_devolucion") + "\n"
                        + "Estado: " + personas.get("estado") + "\n"
                        + "Libro id: " + personas.get("libro_id") + "\n"
                        + "Persona id: " + personas.get("persona_id") + "\n");
            }
     */
    

    public ArrayList<Libro> buscarLibroNombre(String nombreLibro) {
        consultaSQL = "SELECT * FROM biblioteca.libro WHERE titulo = ?";
        ArrayList<Libro> libros = new ArrayList<>();
        String nombreLibroBuscado = nombreLibro;
        try {
            ps = cn.prepareStatement(consultaSQL);
            ps.setString(1, nombreLibroBuscado);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idLibro = rs.getInt("id");
                String tituloLibro = rs.getString("titulo");
                String autorLibro = rs.getString("autor");
                String isbnLibro = rs.getString("isbn");
                String generoLibro = rs.getString("genero");
                String fechaLibro = rs.getString("fecha_publicacion");
                int ejemplaresLibro = rs.getInt("ejemplares");
                int seccionLibro = rs.getInt("seccion_id");
                Libro libro = new Libro(tituloLibro, autorLibro, isbnLibro, generoLibro, fechaLibro, ejemplaresLibro, seccionLibro);
                libro.setIdLibro(idLibro);
                libros.add(libro);
            }
            con.cerrarConexion();
            return libros;
        } catch (SQLException e) {
            System.out.println("Error al buscar libros por autor: " + e);
        }
        return null;
    }
    
    public ArrayList<Libro> buscarLibrosAutor(String nombreAutor) {
        consultaSQL = "SELECT * FROM biblioteca.libro WHERE autor = ?";
        ArrayList<Libro> libros = new ArrayList<>();
        String nombreAutorBuscado = nombreAutor;
        try {
            ps = cn.prepareStatement(consultaSQL);
            ps.setString(1, nombreAutorBuscado);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idLibro = rs.getInt("id");
                String tituloLibro = rs.getString("titulo");
                String autorLibro = rs.getString("autor");
                String isbnLibro = rs.getString("isbn");
                String generoLibro = rs.getString("genero");
                String fechaLibro = rs.getString("fecha_publicacion");
                int ejemplaresLibro = rs.getInt("ejemplares");
                int seccionLibro = rs.getInt("seccion_id");
                Libro libro = new Libro(tituloLibro, autorLibro, isbnLibro, generoLibro, fechaLibro, ejemplaresLibro, seccionLibro);
                libro.setIdLibro(idLibro);
                libros.add(libro);
            }
            con.cerrarConexion();
            return libros;
        } catch (SQLException e) {
            System.out.println("Error al buscar libros por autor: " + e);
        }
        return null;
    }

    /*Forma de devolver el resultado de los libros en el metodo buscarLibrosAutor():
            ArrayList<Libro> librosAlmacenados = buscarLibrosAutor("Gabriel Garcia Marquez");
            for (Libro libroMostrado : librosAlmacenados) {
                System.out.println("Autor: " + libroMostrado.getAutor() + "\n"
                        + "Titulo: " + libroMostrado.getTitulo() + "\n"
                        + "Genero: " + libroMostrado.getGenero() + "\n");
            }
     */
    
    public ArrayList<Libro> buscarLibrosGenero(String generoLibroBuscado) {
        consultaSQL = "SELECT * FROM biblioteca.libro WHERE genero = ?";
        ArrayList<Libro> libros = new ArrayList<>();
        String generoBuscado = generoLibroBuscado;
        try {
            ps = cn.prepareStatement(consultaSQL);
            ps.setString(1, generoBuscado);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idLibro = rs.getInt("id");
                String tituloLibro = rs.getString("titulo");
                String autorLibro = rs.getString("autor");
                String isbnLibro = rs.getString("isbn");
                String generoLibro = rs.getString("genero");
                String fechaLibro = rs.getString("fecha_publicacion");
                int ejemplaresLibro = rs.getInt("ejemplares");
                int seccionLibro = rs.getInt("seccion_id");
                System.out.println("ID LIBRO BUSCADO: "+idLibro);
                Libro libro = new Libro(tituloLibro, autorLibro, isbnLibro, generoLibro, fechaLibro, ejemplaresLibro, seccionLibro);
                libro.setIdLibro(idLibro);
                libros.add(libro);
            }
            con.cerrarConexion();
            return libros;
        } catch (SQLException e) {
            System.out.println("Error al buscar libros por genero: " + e);
        }
        return null;
    }

    /*Forma de devolver el resultado de los libros en el metodo buscarLibrosGenero():
            ArrayList<Libro> librosAlmacenados = buscarLibrosAutor("Gabriel Garcia Marquez");
            for (Libro libroMostrado : librosAlmacenados) {
                System.out.println("Autor: " + libroMostrado.getAutor() + "\n"
                        + "Titulo: " + libroMostrado.getTitulo() + "\n"
                        + "Genero: " + libroMostrado.getGenero() + "\n");
            }
     */
    
    public String buscarPersona(int idPersona) {
        consultaSQL = "SELECT * FROM biblioteca.persona WHERE id = ?";
        int idPersonaBuscada = idPersona;
        try {
            ps = cn.prepareStatement(consultaSQL);
            ps.setInt(1, idPersonaBuscada);
            rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                String correoPersona = rs.getString("correo");
                String contraseña = rs.getString("contraseña");
                if (contraseña != null && !contraseña.isEmpty()) {
                    Admin administrador = new Admin(nombre, apellido, direccion, telefono, correoPersona, contraseña);
                    administrador.setId(id);
                    return "ID: " + administrador.getId() + "\n"
                            + "Nombre: " + administrador.getNombre() + "\n"
                            + "Apellido: " + administrador.getApellido() + "\n"
                            + "Direccion: " + administrador.getDireccion() + "\n"
                            + "Telefono: " + administrador.getTelefono() + "\n"
                            + "Correo: " + administrador.getCorreo() + "\n"
                            + "Contraseña: " + administrador.getContraseña();
                } else {
                    User usuario = new User(nombre, apellido, direccion, telefono, correoPersona);
                    usuario.setId(id);
                    return "ID: " + usuario.getId() + "\n"
                            + "Nombre: " + usuario.getNombre() + "\n"
                            + "Apellido: " + usuario.getApellido() + "\n"
                            + "Direccion: " + usuario.getDireccion() + "\n"
                            + "Telefono: " + usuario.getTelefono() + "\n"
                            + "Correo: " + usuario.getCorreo() + "\n"
                            + "Contraseña: null";
                }
            }
            con.cerrarConexion();
        } catch (SQLException e) {
            System.out.println("Error al buscar persona: " + e);
        }
        return null;
    }
    
    public User buscarUsuarioCorreo(String correo) {
        consultaSQL = "SELECT * FROM biblioteca.persona WHERE correo = ?";
        String correoBuscar = correo;
        try {
            ps = cn.prepareStatement(consultaSQL);
            ps.setString(1, correoBuscar);
            rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setNombre(rs.getString("nombre"));
                user.setApellido(rs.getString("apellido"));
                user.setDireccion(rs.getString("direccion"));
                user.setTelefono(rs.getString("telefono"));
                user.setCorreo(rs.getString("correo"));
                return user;
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar persona: " + e);
        } finally {
            // Cerrar la conexión en el bloque 'finally'
            con.cerrarConexion();
        }
        return null;
    }

    public Admin buscarAdminCorreo(String correo) {
        Admin admin = new Admin();
        consultaSQL = "SELECT * FROM biblioteca.persona WHERE correo = ?";
        String correoBuscar = correo;
        try {
            ps = cn.prepareStatement(consultaSQL);
            ps.setString(1, correoBuscar);
            rs = ps.executeQuery();
            if (rs.next()) {
                admin.setId(rs.getInt("id"));
                admin.setNombre(rs.getString("nombre"));
                admin.setApellido(rs.getString("apellido"));
                admin.setDireccion(rs.getString("direccion"));
                admin.setTelefono(rs.getString("telefono"));
                admin.setCorreo(rs.getString("correo"));
                admin.setContraseña(rs.getString("contraseña"));
                return admin;
            }
            System.out.println("Ingreso exitoso Admin: "+admin.getNombre());
            con.cerrarConexion();

        } catch (SQLException e) {
            System.out.println("Error al buscar persona: " + e);
        }
        return null;
    }

    public boolean validarIngresoAdmin(String correo, String contraseña) {
        consultaSQL = "SELECT correo, contraseña FROM biblioteca.persona WHERE correo=? AND contraseña=?";
        try {
            ps = cn.prepareStatement(consultaSQL);
            ps.setString(1, correo);
            ps.setString(2, contraseña);
            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Usuario: " + correo + " encontrado correctamente");
                return true;
            } else {
                System.out.println("No se ha encontrado el usuario: " + correo);
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al validar ingreso: " + e);
        }
        con.cerrarConexion();
        return false;
    }

    public boolean validarIngresoUser(String correo) {
        consultaSQL = "SELECT correo, contraseña FROM biblioteca.persona WHERE correo=? AND contraseña=?";
        try {
            ps = cn.prepareStatement(consultaSQL);
            ps.setString(1, correo);
            ps.setString(2, "null");
            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Usuario: " + correo + " encontrado correctamente");
                return true;
            } else {
                System.out.println("No se ha encontrado el usuario: " + correo);
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al validar ingreso: " + e);
        }
        con.cerrarConexion();
        return false;
    }
    

}