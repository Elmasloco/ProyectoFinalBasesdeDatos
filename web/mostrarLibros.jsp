<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="Modelo.User" %>
<%@ page import="Modelo.Admin" %>
<%@ page import="Controlador.PersonaDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<jsp:include page="/Controlador?accion=resultado"/>
<%
    String rol = (String) request.getSession().getAttribute("rol");
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
        <link rel="stylesheet" href="estiloTablas.css">
        <title>Lista de Libros</title>
    </head>
    <body class="bodyMostrarLibros">
        <header class="headerIndex">
            <div class="titulo">
                <h1>BIBLIOTECA</h1>
            </div>
            <div class="wrapper">
                <nav class="nav">
                    <a href="home.jsp" class="nav-item " active-color="orange">Home</a>
                    <a href="mostrarLibros.jsp" class="nav-item is-active" active-color="green">Catalogo de libros</a>
                    <a href="index.jsp" class="nav-item" active-color="red">Cerrar sesion</a>
                    <span class="nav-indicator"></span>
                </nav>
            </div>
        </header>
        <h1>CATALOGO DE LIBROS</h1>
        <main>
            <div class="contenedor__busqueda_filtro flex">
                <form action="Controlador" method="get" class="filtroBusquedaLibro flex">
                    <div class="campo">
                        <select name="filtro" id="filtro" class="campo__select">
                            <option value="todos">Todos</option>
                            <option value="genero">Filtrar por Género</option>
                            <option value="autor">Buscar por Autor</option>
                            <option value="nombre">Buscar por Nombre</option>
                        </select>
                    </div>
                    <div class="cont__buscar">
                        <div class="campo campo__contenedorBusqueda">
                            <input type="text" required name="info" class="campo__busqueda">
                        </div>
                    </div>
                    <div class="contBoton flex">
                        <button type="submit" class="btnbuscar" name="accion" value="buscarLibro">
                            <img src="img/buscar.png" alt="Buscar">
                        </button>
                    </div>
                </form>
            </div>
            <div class="container">
                <table class="tablaLibros">
                    <thead>
                        <tr>
                            <th scope="col" class="tablaLibros__columna">#</th>
                            <th scope="col" class="tablaLibros__columna">titulo</th>
                            <th scope="col" class="tablaLibros__columna">autor</th>
                            <th scope="col" class="tablaLibros__columna">genero</th>
                            <th scope="col" class="tablaLibros__columna">ISBN</th>
                            <th scope="col" class="tablaLibros__columna">fecha de publicacion</th>
                            <th scope="col" class="tablaLibros__columna">ejemplares</th>
                            <th class="tablaLibros__columna" class="thOpcion">Reservar</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                              PersonaDAO pdao = new PersonaDAO();
                              ArrayList<HashMap> resultado = (ArrayList<HashMap>) request.getAttribute("resultado");
                              resultado = pdao.mostrarLibros();
                              if(resultado == null) {
                                  out.print("<tr><td colspan='8'>No se encontraron resultados</td></tr>");
                              } else {
                                for (HashMap resultados : resultado) {
                                    out.print("<tr>");
                                    out.print("<td scope='row'>"+resultados.get("id")+"</td>");
                                    out.print("<td>"+resultados.get("titulo")+"</td>");           
                                    out.print("<td>"+resultados.get("autor")+"</td>");
                                    out.print("<td>"+resultados.get("isbn")+"</td>");
                                    out.print("<td>"+resultados.get("genero")+"</td>");
                                    out.print("<td>"+resultados.get("fecha_publicacion")+"</td>");
                                    out.print("<td>"+resultados.get("ejemplares")+"</td>");
                                    out.print("<td>"
                                    + "<form action='' method='POST' class='formReservar'>"
                                    + "<input title='Reservar' class='imagenReservar' alt='Completar' src='img/alquilarLibro.png' type='image'/>"
                                    +        "</form>"+
                                             "</td>");
                                    out.print("</tr>");
                                }
                             }
                        %>
                    </tbody>
                </table>
            </div>
            <div class="contBotonRegresar flex">
                <a href="home.jsp" class="botonRegresar ">Regresar</a>
            </div>
        </main>
        <script src="menu.js"></script>
    </body>
</html>
