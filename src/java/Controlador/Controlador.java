/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Admin;
import Modelo.Libro;
import Modelo.Persona;
import Modelo.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author samue
 */
@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {

    private final PersonaDAO pdao = new PersonaDAO();
    public Persona sesionPersona;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        System.out.println("ACCION GET: " + accion);
        String rol;
        switch (accion) {
            case "libros":
                rol = (String) request.getSession().getAttribute("rol");
                System.out.println("ROL: " + rol);
                if ("admin".equals(rol)) {
                    ArrayList<HashMap> resultado = pdao.mostrarLibros();
                    System.out.println("RESULTADO ADMIN: " + resultado);
                    request.setAttribute("libros", resultado);
                } else {
                    User user = (User) request.getSession().getAttribute("userLogin");
                    int idUsuario = user.getId();
                    System.out.println("ID USUARIO: " + idUsuario);
                    ArrayList<HashMap> resultado = pdao.mostrarLibros();
                    System.out.println("ID USUARIO: " + idUsuario + " - RESULTADO");
                    request.setAttribute("libros", resultado);
                    System.out.println(resultado);
                }
                break;
            case "resultado":
                System.out.println("RESULTADO -----");
                rol = (String) request.getSession().getAttribute("rol");
                System.out.println("ROL: " + rol);
                if ("admin".equals(rol)) {
                    ArrayList<HashMap> resultado = pdao.mostrarPrestamosAdmin();
                    System.out.println("RESULTADO ADMIN: " + resultado);
                    request.setAttribute("resultado", resultado);
                } else {
                    User user = (User) request.getSession().getAttribute("userLogin");
                    int idUsuario = user.getId();
                    System.out.println("ID USUARIO: " + idUsuario);
                    ArrayList<HashMap> resultado = pdao.mostrarPrestamos(idUsuario);
                    System.out.println("ID USUARIO: " + idUsuario + " - RESULTADO");
                    request.setAttribute("resultado", resultado);
                    System.out.println(resultado);
                }
                break;
            case "personas":
                ArrayList<HashMap> personas = pdao.mostrarPersonas();
                request.setAttribute("personas", personas);
                break;
            default:
                response.sendRedirect("home.jsp");
                break;
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        System.out.println("----------------------------------------");
        System.out.println("AQUI: " + accion);
        String rol;
        String libroId;
        switch (accion) {
            case "cerrarSesion":
                sesionPersona = null;
                request.getSession().invalidate();
                response.sendRedirect("index.jsp");
                break;
            case "agregarUser":
                String nombre = request.getParameter("nombre");
                String apellidos = request.getParameter("apellidos");
                String direccion = request.getParameter("direccion");
                String telefono = request.getParameter("telefono");
                String correoUser = request.getParameter("correo");
                User user = new User(nombre, apellidos, direccion, telefono, correoUser);
                pdao.insertarUsuario(user);
                System.out.println("----------------------------------------");
                System.out.println("NOMBRE DE LA PERSONA: " + user.getNombre());
                response.sendRedirect("index.jsp");
                break;
            case "agregarAdmin":
                String nombreAdmin = request.getParameter("nombre");
                String apellidosAdmin = request.getParameter("apellidos");
                String contraseñaCreada = request.getParameter("contra");
                String correoAdmin = request.getParameter("correo");
                Admin admin = new Admin(nombreAdmin, apellidosAdmin, "", "", correoAdmin, contraseñaCreada);
                pdao.insertarAdmin(admin);
                System.out.println("----------------------------------------");
                System.out.println("NOMBRE DEL ADMIN: " + admin.getNombre());
                response.sendRedirect("index.jsp");
                break;
            case "agregarLibro":
                String titulo = request.getParameter("titulo");
                String autor = request.getParameter("autor");
                String isbn = request.getParameter("isbn");
                String genero = request.getParameter("genero");
                String fecha_publicacion = request.getParameter("fecha_publicacion");
                String seccion = request.getParameter("seccion");
                String ejemplares = request.getParameter("ejemplares");
                System.out.println("SECCION: "+seccion+ " - EJEMPLARES: "+ejemplares);
                Libro libro = new Libro(titulo, autor, isbn, genero, fecha_publicacion, Integer.parseInt(seccion), Integer.parseInt(ejemplares));
                pdao.insertarLibro(libro);
                response.sendRedirect("home.jsp");
                break;
            case "ingresar":
                rol = request.getParameter("rol");
                String correo = request.getParameter("correo");
                String contraseña = request.getParameter("contra");

                boolean validacion;
                Persona pers = null;

                System.out.println("");
                System.out.println("INGRESAR");
                System.out.println("ROL: " + rol);
                System.out.println("CONTRASEÑA: " + contraseña);

                if ("seleccioneRol".equals(rol)) {
                    response.sendRedirect("login.jsp");
                    return;
                }

                if ("admin".equals(rol)) {
                    validacion = pdao.validarIngresoAdmin(correo, contraseña);
                    if (validacion) {
                        pers = pdao.buscarAdminCorreo(correo);
                    }
                } else {
                    validacion = pdao.validarIngresoUser(correo);
                    System.out.println("Validacion " + validacion);
                    if (validacion) {
                        pers = pdao.buscarUsuarioCorreo(correo);
                    }
                }

                System.out.println("PERS " + pers);

                if (pers == null) {
                    response.sendRedirect("login.jsp");
                    return;
                }

                sesionPersona = pers;
                request.getSession().setAttribute("userLogin", pers);
                request.getSession().setAttribute("rol", rol);
                System.out.println("ROL" + rol);
                response.sendRedirect("home.jsp");
                break;
            case "devolver":
                correo = request.getParameter("correo");
                libroId = request.getParameter("libroId");
                String prestamoId = request.getParameter("prestamoId");
                rol = request.getParameter("rol");
                pdao.devolverLibro(Integer.parseInt(prestamoId), Integer.parseInt(libroId));
                pers = pdao.buscarUsuarioCorreo(correo);
                request.getSession().setAttribute("persona_id", pers);
                request.getSession().setAttribute("rol", rol);
                response.sendRedirect("home.jsp");
                break;
            case "reservar":
                LocalDate fechaHoy = LocalDate.now();
                LocalDate fechaDevolucion = fechaHoy.plusMonths(1);
                correo = request.getParameter("correo");
                rol = request.getParameter("rol");
                String personaId = request.getParameter("personaId");
                libroId = request.getParameter("libroId");
                pdao.agregarPrestamo(Integer.parseInt(personaId), Integer.parseInt(libroId), fechaHoy.format(DateTimeFormatter.ISO_DATE), fechaDevolucion.format(DateTimeFormatter.ISO_DATE));
                pers = pdao.buscarUsuarioCorreo(correo);
                request.getSession().setAttribute("persona_id", pers);
                request.getSession().setAttribute("rol", rol);
                response.sendRedirect("mostrarLibros.jsp");
                break;
            case "buscarLibro":
                String select = request.getParameter("filtro");
                String buscador = request.getParameter("buscador");
                request.setAttribute("resultado", pdao.mostrarLibrosBuscados(select, buscador));
                request.getRequestDispatcher("mostrarLibros.jsp").forward(request, response);
                break;
            default:
                response.sendRedirect("index.jsp");
                break;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
