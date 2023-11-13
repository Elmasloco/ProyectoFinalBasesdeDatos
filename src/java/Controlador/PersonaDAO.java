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

    public PersonaDAO() {
    }

    public void insertarUsuario(User user) {
        String SQLString = "INSERT INTO biblioteca.persona(nombre, apellido, direccion, telefono, correo, contraseña) VALUES(?,?,?,?,?,?)";
        PreparedStatement ps;
        try {
            Connection cn = con.abrirConexion();
            ps = cn.prepareStatement(SQLString, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getNombre());
            ps.setString(2, user.getApellido());
            ps.setString(3, user.getDireccion());
            ps.setString(4, user.getTelefono());
            ps.setString(5, user.getCorreo());
            ps.setString(6, "...");
            int registrosAfectados = ps.executeUpdate();
            if (registrosAfectados == 1) {
                System.out.println("Usuario insertado correctamente!");
                ResultSet rs = ps.getGeneratedKeys();
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
        String SQLString = "INSERT INTO biblioteca.persona(nombre, apellido, direccion, telefono, correo, contraseña) VALUES(?,?,?,?,?,?)";
        PreparedStatement ps;
        try {
            Connection cn = con.abrirConexion();
            ps = cn.prepareStatement(SQLString, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, admin.getNombre());
            ps.setString(2, admin.getApellido());
            ps.setString(3, "...");
            ps.setString(4, "...");
            ps.setString(5, admin.getCorreo());
            ps.setString(6, admin.getContraseña());
            int registrosAfectados = ps.executeUpdate();
            if (registrosAfectados == 1) {
                System.out.println("Administrador insertado correctamente!");
                ResultSet rs = ps.getGeneratedKeys();
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
        String SQLString = "INSERT INTO biblioteca.libro(titulo, autor, isbn, genero, fecha_publicacion, seccion_id, ejemplares) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement ps;
        try {
            Connection cn = con.abrirConexion();
            ps = cn.prepareStatement(SQLString, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setString(3, libro.getIsbn());
            ps.setString(4, libro.getGenero());
            ps.setString(5, libro.getFecha_publicacion());
            ps.setInt(6, libro.getSeccion_id());
            ps.setInt(7, libro.getEjemplares());
            int registrosAfectados = ps.executeUpdate();
            if (registrosAfectados == 1) {
                System.out.println("Libro insertado correctamente!");
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    libro.setIdLibro(id);
                }
            } else {
                System.out.println("Error al insertar libro");
            }
            con.cerrarConexion();
        } catch (SQLException e) {
            System.out.println("Error al insertar libro: " + e);
        }
    }

    private void actualizarEjemplares(int idLibro) throws SQLException {
        String SQLString = "UPDATE libro SET ejemplares = ejemplares - 1 WHERE id = ?";
        Connection cn = con.abrirConexion();
        PreparedStatement ps = cn.prepareStatement(SQLString);
        ps.setInt(1, idLibro);
        ps.executeUpdate();
        con.cerrarConexion();
    }

    private void crearPrestamo(int idUsuario, int idLibro, String fechaP, String fechaD) throws SQLException {
        String SQLString = "INSERT INTO biblioteca.prestamo(fecha_prestamo, fecha_devolucion, libro_id, persona_id) VALUES(?,?,?,?)";
        PreparedStatement ps;

        Connection cn = con.abrirConexion();
        ps = cn.prepareStatement(SQLString, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, fechaP);
        ps.setString(2, fechaD);
        ps.setInt(3, idLibro);
        ps.setInt(4, idUsuario);
        ps.executeUpdate();

        con.cerrarConexion();

    }

    public void agregarPrestamo(int idUsuario, int idLibro, String fechaP, String fechaD) {
        try {
            crearPrestamo(idUsuario, idLibro, fechaP, fechaD);
            actualizarEjemplares(idLibro);
            con.cerrarConexion();
        } catch (SQLException e) {
            System.out.println("Error al agregar prestamo: " + e);
        }
    }

    private void insertarRol(String rol, int id) {
        String SQLString = "INSERT INTO biblioteca.roles(rol, id_persona) VALUES(?,?)";
        PreparedStatement ps;
        try {
            Connection cn = con.abrirConexion();
            ps = cn.prepareStatement(SQLString);
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

    public ArrayList<HashMap> mostrarLibros() {
        String SQLString = "SELECT * FROM libro WHERE ejemplares > 0";
        PreparedStatement ps;
        ArrayList<HashMap> resultado = new ArrayList<>();
        try {
            Connection cn = con.abrirConexion();
            ps = cn.prepareStatement(SQLString);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idLibro = rs.getInt("id");
                String tituloLibro = rs.getString("titulo");
                String autorLibro = rs.getString("autor");
                String isbnLibro = rs.getString("isbn");
                String generoLibro = rs.getString("genero");
                String fechaLibro = rs.getString("fecha_publicacion");
                int seccionLibro = rs.getInt("seccion_id");
                int ejemplares = rs.getInt("ejemplares");
                Libro libro = new Libro(tituloLibro, autorLibro, isbnLibro, generoLibro, fechaLibro, seccionLibro, ejemplares);
                libro.setIdLibro(idLibro);
                resultado.add(libro.toHashMap());
            }
            con.cerrarConexion();
        } catch (SQLException e) {
            System.out.println("Error al leer registros de tabla libro: " + e);
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
        String SQLString = "SELECT * FROM biblioteca.persona";
        PreparedStatement ps;
        ArrayList<HashMap> resultado = new ArrayList<>();
        try {
            Connection cn = con.abrirConexion();
            ps = cn.prepareStatement(SQLString);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idPersona = rs.getInt("id");
                String nombrePersona = rs.getString("nombre");
                String apellidoPersona = rs.getString("apellido");
                String direccionPersona = rs.getString("direccion");
                String telefonoPersona = rs.getString("telefono");
                String correoPersona = rs.getString("correo");
                String contrasenaPersona = rs.getString("contraseña");
                if (contrasenaPersona != null && !contrasenaPersona.isEmpty()) {
                    Admin admin = new Admin(nombrePersona, apellidoPersona, direccionPersona, telefonoPersona, correoPersona, contrasenaPersona);
                    admin.setId(idPersona);
                    resultado.add(admin.toHashMap());
                } else {
                    User user = new User(nombrePersona, apellidoPersona, direccionPersona, telefonoPersona, correoPersona);
                    user.setId(idPersona);
                    resultado.add(user.toHashMap());
                }
            }
            con.cerrarConexion();
        } catch (SQLException e) {
            System.out.println("Error al leer registros de tabla persona: " + e);
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
    public ArrayList<HashMap> mostrarPrestamos(int personaId) {
        String SQLString = "SELECT P.*, L.titulo FROM `prestamo` P, libro L WHERE P.libro_id = L.id AND devuelto = false AND persona_id = ? ORDER BY P.id";
        PreparedStatement ps;
        ArrayList<HashMap> resultado = new ArrayList<>();
        try {
            Connection cn = con.abrirConexion();
            ps = cn.prepareStatement(SQLString);
            ps.setInt(1, personaId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idPrestamo = rs.getInt("id");
                String fechaPrestamo = rs.getString("fecha_prestamo");
                String fechaDevolucion = rs.getString("fecha_devolucion");
                int libroID = rs.getInt("libro_id");
                int personaID = rs.getInt("persona_id");
                boolean devolver = rs.getBoolean("devuelto");
                String titulo = rs.getString("titulo");
                Prestamo prestamo = new Prestamo(idPrestamo, fechaPrestamo, fechaDevolucion, libroID, personaID, devolver, titulo);
                resultado.add(prestamo.toHashMap());
            }
            con.cerrarConexion();
        } catch (SQLException e) {
            System.out.println("Error al leer registros de tabla persona: " + e);
        }
        return resultado;
    }

    public ArrayList<HashMap> mostrarPrestamosAdmin() {
        String SQLString = "SELECT P.*, L.titulo FROM prestamo P, libro L WHERE P.libro_id = L.id ORDER BY P.id";
        PreparedStatement ps;
        ArrayList<HashMap> resultado = new ArrayList<>();
        try {
            Connection cn = con.abrirConexion();
            ps = cn.prepareStatement(SQLString);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idPrestamo = rs.getInt("id");
                String fechaPrestamo = rs.getString("fecha_prestamo");
                String fechaDevolucion = rs.getString("fecha_devolucion");
                int libroID = rs.getInt("libro_id");
                int personaID = rs.getInt("persona_id");
                boolean devolver = rs.getBoolean("devuelto");
                String titulo = rs.getString("titulo");
                Prestamo prestamo = new Prestamo(idPrestamo, fechaPrestamo, fechaDevolucion, libroID, personaID, devolver, titulo);
                resultado.add(prestamo.toHashMap());
            }
            con.cerrarConexion();
        } catch (SQLException e) {
            System.out.println("Error al leer registros de tabla persona: " + e);
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
    public ArrayList<HashMap> mostrarLibrosBuscados(String filtro, String buscador) {
        switch (filtro) {
            case "nombre":
                return buscarLibroNombre(buscador);
            case "autor":
                return buscarLibrosAutor(buscador);
            case "genero":
                System.out.println("buscarLibrosGenero(buscador): " + buscarLibrosGenero(buscador));
                return buscarLibrosGenero(buscador);
            default:
                return mostrarLibros();
        }
    }

    private ArrayList<HashMap> buscarLibroNombre(String nombreLibro) {
        String SQLString = "SELECT * FROM biblioteca.libro WHERE titulo LIKE ?";
        PreparedStatement ps;
        ArrayList<HashMap> libros = new ArrayList<>();
        String nombreLibroBuscado = nombreLibro;
        try {
            Connection cn = con.abrirConexion();
            ps = cn.prepareStatement(SQLString);
            ps.setString(1, "%" + nombreLibroBuscado + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idLibro = rs.getInt("id");
                String tituloLibro = rs.getString("titulo");
                String autorLibro = rs.getString("autor");
                String isbnLibro = rs.getString("isbn");
                String generoLibro = rs.getString("genero");
                String fechaLibro = rs.getString("fecha_publicacion");
                int seccionLibro = rs.getInt("seccion_id");
                int ejemplares = rs.getInt("ejemplares");
                Libro libro = new Libro(tituloLibro, autorLibro, isbnLibro, generoLibro, fechaLibro, seccionLibro, ejemplares);
                libro.setIdLibro(idLibro);
                libros.add(libro.toHashMap());
            }
            con.cerrarConexion();
            return libros;
        } catch (SQLException e) {
            System.out.println("Error al buscar libros por autor: " + e);
        }
        return null;
    }

    private ArrayList<HashMap> buscarLibrosAutor(String nombreAutor) {
        String SQLString = "SELECT * FROM biblioteca.libro WHERE autor LIKE ?";
        ArrayList<HashMap> libros = new ArrayList<>();
        PreparedStatement ps;
        String nombreAutorBuscado = nombreAutor;
        try {
            Connection cn = con.abrirConexion();
            ps = cn.prepareStatement(SQLString);
            ps.setString(1, "%" + nombreAutorBuscado + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idLibro = rs.getInt("id");
                String tituloLibro = rs.getString("titulo");
                String autorLibro = rs.getString("autor");
                String isbnLibro = rs.getString("isbn");
                String generoLibro = rs.getString("genero");
                String fechaLibro = rs.getString("fecha_publicacion");
                int seccionLibro = rs.getInt("seccion_id");
                int ejemplares = rs.getInt("ejemplares");
                Libro libro = new Libro(tituloLibro, autorLibro, isbnLibro, generoLibro, fechaLibro, seccionLibro, ejemplares);
                libro.setIdLibro(idLibro);
                libros.add(libro.toHashMap());
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
    private ArrayList<HashMap> buscarLibrosGenero(String generoLibroBuscado) {
        PreparedStatement ps;
        String SQLString = "SELECT * FROM biblioteca.libro WHERE genero LIKE ?";
        ArrayList<HashMap> libros = new ArrayList<>();
        String generoBuscado = generoLibroBuscado;
        try {
            Connection cn = con.abrirConexion();
            ps = cn.prepareStatement(SQLString);
            ps.setString(1, "%" + generoBuscado + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idLibro = rs.getInt("id");
                String tituloLibro = rs.getString("titulo");
                String autorLibro = rs.getString("autor");
                String isbnLibro = rs.getString("isbn");
                String generoLibro = rs.getString("genero");
                String fechaLibro = rs.getString("fecha_publicacion");
                int seccionLibro = rs.getInt("seccion_id");
                int ejemplares = rs.getInt("ejemplares");
                Libro libro = new Libro(tituloLibro, autorLibro, isbnLibro, generoLibro, fechaLibro, seccionLibro, ejemplares);
                libro.setIdLibro(idLibro);
                libros.add(libro.toHashMap());
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
        PreparedStatement ps;
        String SQLString = "SELECT * FROM biblioteca.persona WHERE id = ?";
        int idPersonaBuscada = idPersona;
        try {
            Connection cn = con.abrirConexion();
            ps = cn.prepareStatement(SQLString);
            ps.setInt(1, idPersonaBuscada);
            ResultSet rs = ps.executeQuery();
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
        PreparedStatement ps;
        String SQLString = "SELECT * FROM biblioteca.persona WHERE correo = ?";
        String correoBuscar = correo;
        try {
            Connection cn = con.abrirConexion();
            ps = cn.prepareStatement(SQLString);
            ps.setString(1, correoBuscar);
            ResultSet rs = ps.executeQuery();
            con.cerrarConexion();
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
        }
        return null;
    }

    public Admin buscarAdminCorreo(String correo) {
        Admin admin = new Admin();
        PreparedStatement ps;
        String SQLString = "SELECT * FROM biblioteca.persona WHERE correo = ?";
        String correoBuscar = correo;
        try {
            Connection cn = con.abrirConexion();
            ps = cn.prepareStatement(SQLString);
            ps.setString(1, correoBuscar);
            ResultSet rs = ps.executeQuery();
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
            System.out.println("Ingreso exitoso Admin: " + admin.getNombre());
            con.cerrarConexion();

        } catch (SQLException e) {
            System.out.println("Error al buscar persona: " + e);
        }
        return null;
    }

    public boolean validarIngresoAdmin(String correo, String contraseña) {
        String SQLString = "SELECT correo, contraseña FROM biblioteca.persona WHERE correo=? AND contraseña=?";
        PreparedStatement ps;
        try {
            Connection cn = con.abrirConexion();
            ps = cn.prepareStatement(SQLString);
            ps.setString(1, correo);
            ps.setString(2, contraseña);
            ResultSet rs = ps.executeQuery();
            con.cerrarConexion();
            if(contraseña.equalsIgnoreCase("...")){
                return false;
            }
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

        return false;
    }

    public boolean validarIngresoUser(String correo) {
        PreparedStatement ps;
        String SQLString = "SELECT correo, contraseña FROM biblioteca.persona WHERE correo=? AND contraseña=?";
        try {
            Connection cn = con.abrirConexion();
            ps = cn.prepareStatement(SQLString);
            ps.setString(1, correo);
            ps.setString(2, "null");
            ResultSet rs = ps.executeQuery();
            boolean result = rs.next();
            if (result) {
                System.out.println("Usuario: " + correo + " encontrado correctamente");
            }
            con.cerrarConexion();
            return result;
        } catch (SQLException e) {
            System.out.println("Error al validar ingreso: " + e);
            return false;
        }
    }

    private void aumentarEjemplares(int idLibro) throws SQLException {
        String SQLString = "UPDATE libro SET ejemplares = ejemplares + 1 WHERE id = ?";
        Connection cn = con.abrirConexion();
        PreparedStatement ps = cn.prepareStatement(SQLString);
        ps.setInt(1, idLibro);
        ps.executeUpdate();
        con.cerrarConexion();
    }

    public void devolverLibro(int prestamoId, int idLibro) {
        PreparedStatement ps;
        String SQLString = "UPDATE prestamo SET devuelto=true WHERE id=?";
        try {
            Connection cn = con.abrirConexion();
            ps = cn.prepareStatement(SQLString);
            ps.setInt(1, prestamoId);
            ps.executeUpdate();
            con.cerrarConexion();

            aumentarEjemplares(idLibro);
        } catch (SQLException e) {
            System.out.println("Error al devoler libro: " + e);
        }
    }

}
