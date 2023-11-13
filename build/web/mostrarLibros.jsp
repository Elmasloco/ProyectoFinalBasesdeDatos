<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="Modelo.*" %>
<%@ page import="Controlador.PersonaDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<jsp:include page="/Controlador?accion=libros"/>
<%
    String rol = (String) request.getSession().getAttribute("rol");
    Persona persona = (Persona) request.getSession().getAttribute("userLogin");
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
                    <% if ("admin".equals(rol)) { %>
                    <a href="signupAdmin.jsp" class="nav-item is-active" active-color="blue">Sign Up</a>
                    <a href="signupAdmin.jsp" class="nav-item is-active" active-color="black">Ver usuarios</a>
                    <% } %>
                    <a href="index.jsp" class="nav-item" active-color="red">Cerrar sesion</a>
                    <span class="nav-indicator"></span>
                </nav>
            </div>
        </header>
        <h1>CATALOGO DE LIBROS</h1>
        <main>
            <div class="contenedor__busqueda_filtro flex">
                <form action="Controlador" method="post" class="filtroBusquedaLibro flex">
                    <div class="campo">
                        <select name="filtro" id="filtro" class="campo__select" required>
                            <option value="todos">Todos</option>
                            <option value="genero">Filtrar por Género</option>
                            <option value="autor">Buscar por Autor</option>
                            <option value="nombre">Buscar por Titulo</option>
                        </select>
                        <input name="buscador" placeholder="Ingrese un valor" />
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
                            <% if (!"admin".equals(rol)){ %>
                            <th class="tablaLibros__columna" class="thOpcion">Reservar</th>
                            <% } %>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                              PersonaDAO pdao = new PersonaDAO();
                              ArrayList<HashMap> resultado = (ArrayList<HashMap>) request.getAttribute("resultado");
                              System.out.println("RESULTADO FILTRO: "+resultado);
                              if(resultado == null) {
                                resultado = pdao.mostrarLibros();
                                System.out.println("RESULTADO TODOS: "+resultado);
                              }
                              if(resultado == null) {
                                  out.print("<tr><td colspan='8'>No se encontraron resultados</td></tr>");
                              } else {
                                for (HashMap resultados : resultado) {
                                    out.print("<tr>");
                                    out.print("<td scope='row'>"+resultados.get("id")+"</td>");
                                    out.print("<td>"+resultados.get("titulo")+"</td>");           
                                    out.print("<td>"+resultados.get("autor")+"</td>");
                                    out.print("<td>"+resultados.get("genero")+"</td>");
                                    out.print("<td>"+resultados.get("isbn")+"</td>");
                                    out.print("<td>"+resultados.get("fecha_publicacion")+"</td>");
                                    out.print("<td>"+resultados.get("ejemplares")+"</td>");
                                    if (!"admin".equals(rol)) {
                                    out.print("<td>"
                                        + "<form action='Controlador' method='POST' class='formReservar'>"
                                        + "<input hidden name='libroId' value='"+resultados.get("id")+"'/>"
                                        + "<input hidden name='personaId' value='"+persona.getId()+"'/>"
                                        + "<input hidden name='correo' value='"+persona.getCorreo()+"'/>"
                                        + "<input hidden name='rol' value='"+rol+"'/>"
                                        + "<button class='btn-reservar' name='accion' value='reservar'><img alt='Reservar' src='img/alquilarLibro.png' height='30px' width='30px'/></button>"
                                        + "</form>"
                                    + "</td>");
                                    }
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
