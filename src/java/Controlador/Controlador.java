/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import ConexionSQL.ConexionSQL;
import Modelo.Admin;
import Modelo.Libro;
import Modelo.Persona;
import Modelo.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author samue
 */
@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {

    private final PersonaDAO pdao = new PersonaDAO();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Controlador</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controlador at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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

        switch (accion) {
            case "resultado":
                String rol = (String) request.getSession().getAttribute("rol");
                if ("admin".equals(rol)) {
                    ArrayList<HashMap> resultado = pdao.mostrarPrestamosAdmin();
                    request.setAttribute("resultado", resultado);
                } else {
                    User user = (User) request.getSession().getAttribute("userLogin");
                    int idUsuario = user.getId();
                    System.out.println("ID USUARIO: " + idUsuario);
                    ArrayList<HashMap> resultado = pdao.mostrarPrestamos(idUsuario);
                    request.setAttribute("resultado", resultado);
                }
                break;
            case "buscarLibro":
                String filtro = request.getParameter("filtro");
                String info = request.getParameter("info");
                System.out.println("INFO: " + info + " - FILTRO: " + filtro);
                filtro = (filtro != null) ? filtro : "todos";
                try {
                    if ("todos".equals(filtro)) {
                        ArrayList<HashMap> libros = pdao.mostrarLibros();
                        request.setAttribute("buscarLibro", libros);
                        request.getRequestDispatcher("mostrarLibros.jsp").forward(request, response);
                    } else if ("autor".equals(filtro)) {
                        ArrayList<Libro> libros = pdao.buscarLibrosAutor(info);
                        request.setAttribute("buscarLibro", libros);
                        request.getRequestDispatcher("mostrarLibros.jsp").forward(request, response);
                    } else if ("genero".equals(filtro)) {
                        ArrayList<Libro> libros = pdao.buscarLibrosGenero(info);
                        request.setAttribute("buscarLibro", libros);
                        request.getRequestDispatcher("mostrarLibros.jsp").forward(request, response);
                    } else {
                        request.getRequestDispatcher("home.jsp").forward(request, response);
                        throw new IllegalArgumentException("Filtro no válido: " + filtro);
                    }
                } catch (Exception e) {
                    System.out.println("ERORR AL FILTRAR: " + e);
                }
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
        switch (accion) {
            case "cerrarSesion":
                ConexionSQL c = new ConexionSQL();
                System.out.println(c.cerrarConexion());
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
                String contraseñaCreada = request.getParameter("correo");
                String correoAdmin = request.getParameter("contraseña");
                Admin admin = new Admin(nombreAdmin, apellidosAdmin, "", "", correoAdmin, contraseñaCreada);
                pdao.insertarAdmin(admin);
                System.out.println("----------------------------------------");
                System.out.println("NOMBRE DEL ADMIN: " + admin.getNombre());
                response.sendRedirect("index.jsp");
                break;
            case "ingresar":
                PersonaDAO userDao = new PersonaDAO();
                String rol = request.getParameter("rol");
                String correo = request.getParameter("correo");
                String contraseña = request.getParameter("contra");
                boolean validacion = false;
                Persona pers = null;
                if ("seleccioneRol".equals(rol)) {
                    response.sendRedirect("login.jsp");
                } else {
                    if ("admin".equals(rol)) {
                        validacion = userDao.validarIngresoAdmin(correo, contraseña);
                        if (validacion == true) {
                            pers = new Admin();
                            pers = userDao.buscarAdminCorreo(correo);
                        }
                    } else {
                        validacion = userDao.validarIngresoUser(correo);
                        if (validacion == true) {
                            pers = new User();
                            pers = userDao.buscarUsuarioCorreo(correo);
                        }
                    }
                }
                request.getSession().setAttribute("userLogin", pers);
                request.getSession().setAttribute("rol", rol);
                response.sendRedirect("home.jsp");
                break;
            default:
                response.sendRedirect("index.jsp");
                break;
        }
        processRequest(request, response);
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
