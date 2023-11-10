<%-- 
    Document   : index
    Created on : Sep 23, 2023, 2:10:38 PM
    Author     : samue
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="ConexionSQL.ConexionSQL" %>
<!DOCTYPE html>
<html lang="en">
<% 
ConexionSQL c = new ConexionSQL();
System.out.println(c.abrirConexion());
%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="normalize.css">
    <link rel="preload" href="normalize.css" as="style">
    <link rel="stylesheet" href="estiloPrin.css">
    <link rel="preload" href="estiloPrin.css" as="style">
    <title>BIBLIOTECA</title>
</head>

<body>
    <header class="headerIndex">
        <div class="titulo">
            <h1>BIBLIOTECA</h1>
        </div>
        <div class="wrapper">
            <nav class="nav">
                <a href="index.jsp" class="nav-item is-active" active-color="orange">Home</a>
                <a href="login.jsp" class="nav-item" active-color="green">Login</a>
                <a href="signup.jsp" class="nav-item" active-color="blue">Sign Up</a>
                <span class="nav-indicator"></span>
              </nav>
        </div>
    </header>
    <main>
        <section class="hero">
            <div class="contenedor_hero">
                <h2>BIENVENIDOS A LA BIBLIOTECA DIGITAL</h2>
                <p>
                    Aquí podras encontrar los libros de tu gusto y entrar en un 
                    mundo diferente!
                </p>
            </div>
        </section>
        <section class="contenedor_info">
            <div class="info">
                <p>
                    Aquí puedes alquilar los libros que desees y te permitan aprender más o 
                    ingresar a un mundo totalmente lleno de fantasia, historias, acción.
                </p>
            </div>
            <div class="info">
                <p>
                    Se colocara una fecha limite y que sea comoda con tus tiempos para poder 
                    leer el libro en su totalidad y devolverlo para que alguien mas pueda 
                    disfrutar de las enseñanzas, historias y universos de ese libro.
                </p>
            </div>
            <div class="info">
                <p>
                    El alquiler de libros es muy sencillo, con tan solo que te registres 
                    ya podras ingresar a la plataforma a buscar el libro que desees, apartarlo 
                    y listo! eso es todo. 
                </p>
            </div>
        </section>
    </main>
    <footer>
        <h2>SISTEMA DE GESTIÓN DE BIBLIOTECA</h2>
        <p>👾2023</p>
    </footer>

    <script src="menu.js"></script>
</body>

</html>