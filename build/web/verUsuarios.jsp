<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Controlador.PersonaDAO" %>
<%@ page import="ConexionSQL.ConexionSQL" %>
<%@ page import="Modelo.User" %>
<%@ page import="Modelo.Admin" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.io.IOException" %>
<jsp:include page="/Controlador?accion=personas" />
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
                    <a href="signupAdmin.jsp" class="nav-item is-active" active-color="blue">Sign Up</a>
                    <a href="signupAdmin.jsp" class="nav-item is-active" active-color="">Ver usuarios</a>
                    <a href="index.jsp" href="javascript:void(0);" onclick="cerrarSesion();" class="nav-item" name="accion" value="cerrarSesion" active-color="red">Cerrar sesion</a>
                    <span class="nav-indicator"></span>
                </nav>
            </div>
        </header>
        <main>
            <h1>PERSONAS</h1>
            <div class="container">
                <table class="tablaLibros">
                    <thead>
                        <tr>
                            <th scope="col" class="tablaLibros__columna">#</th>
                            <th scope="col" class="tablaLibros__columna">Nombre</th>
                            <th scope="col" class="tablaLibros__columna">Apellido</th>
                            <th scope="col" class="tablaLibros__columna">Direccion</th>
                            <th scope="col" class="tablaLibros__columna">Telefono</th>
                            <th scope="col" class="tablaLibros__columna">Correo</th>
                            <th scope="col" class="tablaLibros__columna">Eliminar</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<HashMap> resultado = (ArrayList<HashMap>) request.getAttribute("personas");
                            if(resultado == null || resultado.isEmpty()) {
                                out.print("<tr><td colspan='8'>...</td></tr>");
                            } else {
                                for (HashMap resultados : resultado) {
                                    out.print("<tr>");
                                    out.print("<td scope='row'>"+resultados.get("id")+"</td>");
                                    out.print("<td>"+resultados.get("nombre")+"</td>");           
                                    out.print("<td>"+resultados.get("apellido")+"</td>");
                                    out.print("<td>"+resultados.get("direccion")+"</td>");
                                    out.print("<td>"+resultados.get("telefono")+"</td>");
                                    out.print("<td>"+resultados.get("correo")+"</td>");
                                        out.print("<td>"
                                                + "<form action='' method='POST' class='formReservar'>"
                                                + "<input title='Devolver' class='imagenReservar' alt='Devolver' src='img/devolver.png' type='image'/>"
                                                + "</form>"
                                                + "</td>");
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