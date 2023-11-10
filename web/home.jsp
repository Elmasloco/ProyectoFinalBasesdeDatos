<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Controlador.PersonaDAO" %>
<%@ page import="ConexionSQL.ConexionSQL" %>
<%@ page import="Modelo.User" %>
<%@ page import="Modelo.Admin" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.io.IOException" %>
<jsp:include page="/Controlador?accion=resultado" />
<% 
    String rol = (String) request.getSession().getAttribute("rol");
    System.out.println("ROL: "+rol);
    Admin admin = null;
    User user = null;
    
    if ("admin".equals(rol)) {
        admin = (Admin) request.getSession().getAttribute("userLogin");
    } else {
        user = (User) request.getSession().getAttribute("userLogin");
    }
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="normalize.css">
        <link rel="preload" href="normalize.css" as="style">
        <link rel="stylesheet" href="estiloPrin.css">
        <link rel="preload" href="estiloPrin.css" as="style">
        <title>Home</title>
    </head>
    <body>
        <header class="headerIndex">
            <div class="titulo">
                <h1>BIBLIOTECA</h1>
            </div>
            <div class="wrapper">
                <nav class="nav">
                    <a href="home.jsp" class="nav-item is-active" active-color="orange">Home</a>
                    <a href="mostrarLibros.jsp" class="nav-item" active-color="green">Catalogo de libros</a>
                    <% if ("admin".equals(rol)) { %>
                    <a href="signupAdmin.jsp" class="nav-item is-active" active-color="blue">Sign Up</a>
                    <% } %>
                    <a href="index.jsp" href="javascript:void(0);" onclick="cerrarSesion();" class="nav-item" name="accion" value="cerrarSesion" active-color="red">Cerrar sesion</a>
                    <span class="nav-indicator"></span>
                </nav>
            </div>
        </header>
        <main>
            <h1>LIBROS RESERVADOS </h1>
            <div class="container">
                <table class="tablaLibros">
                    <thead>
                        <tr>
                            <th scope="col" class="tablaLibros__columna">#</th>
                            <th scope="col" class="tablaLibros__columna">fecha_prestamo</th>
                            <th scope="col" class="tablaLibros__columna">fecha_devolucion</th>
                            <th scope="col" class="tablaLibros__columna">estado</th>
                            <th scope="col" class="tablaLibros__columna">libro_id</th>
                            <th scope="col" class="tablaLibros__columna">persona_id</th>
                            <th class="tablaLibros__columna" class="thOpcion">Devolver</th>
                        </tr>
                    </thead>
                    <tbody>
<%
    ArrayList<HashMap> resultado = (ArrayList<HashMap>) request.getAttribute("resultado");
    if(resultado == null || resultado.isEmpty()) {
        out.print("<tr><td colspan='8'>...</td></tr>");
    } else {
        for (HashMap resultados : resultado) {
            out.print("<tr>");
            out.print("<td scope='row'>"+resultados.get("id")+"</td>");
            out.print("<td>"+resultados.get("fecha_prestamo")+"</td>");           
            out.print("<td>"+resultados.get("fecha_devolucion")+"</td>");
            out.print("<td>"+resultados.get("estado")+"</td>");
            out.print("<td>"+resultados.get("libro_id")+"</td>");
            out.print("<td>"+resultados.get("persona_id")+"</td>");
            if (!"admin".equals(rol)) {
                out.print("<td>"
                        + "<form action='' method='POST' class='formReservar'>"
                        + "<input title='Devolver' class='imagenReservar' alt='Devolver' src='img/devolver.png' type='image'/>"
                        + "</form>"
                        + "</td>");
            } else {
                out.print("<td></td>");
            }
            out.print("</tr>");
        }
    }
%>
                    </tbody>
                </table>
            </div>
        </main>
        <script src="menu.js"></script>
        <script>
                        function cerrarSesion() {
                            fetch('CerrarSesionServlet')
                                    .then(response => response.text())
                                    .then(() => {
                                        window.history.replaceState({}, document.title, 'index.jsp');
                                        window.location.href = 'index.jsp';
                                    });
                        }
        </script>
    </body>
</html>