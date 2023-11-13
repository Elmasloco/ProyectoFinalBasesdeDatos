<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="htmlLogin">
    <head>
        <style>
        html,body{
            overflow-y: scroll;
        }
        main{
            min-height: 100vh;
        }
        </style>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="normalize.css">
        <link rel="preload" href="normalize.css" as="style">
        <link rel="stylesheet" href="estiloPrin.css">
        <link rel="preload" href="estiloPrin.css" as="style">
        <title>BIBLIOTECA</title>
    </head>
    <body class="bodySignup">
        <header class="headerLogin">
            <div class="wrapper2">
                <nav class="nav">
                    <a href="index.jsp" class="nav-item " active-color="orange">Home</a>
                    <a href="login.jsp" class="nav-item " active-color="green">Login</a>
                    <a href="signup.jsp" class="nav-item is-active" active-color="blue">Sign Up</a>
                    <span class="nav-indicator"></span>
                </nav>
            </div>
        </header>
        <main class="mainSignup">
            <section class="seccionFormulario">
                <form action="Controlador" method="post" class="formularioSignup">
                    <fieldset class="formularioSignup__fieldset">
                        <legend class="formularioSignup__legend">
                            <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-user-circle" width="44" height="44" viewBox="0 0 24 24" stroke-width="1.5" stroke="#2c3e50" fill="none" stroke-linecap="round" stroke-linejoin="round">
                            <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
                            <path d="M12 12m-9 0a9 9 0 1 0 18 0a9 9 0 1 0 -18 0" />
                            <path d="M12 10m-3 0a3 3 0 1 0 6 0a3 3 0 1 0 -6 0" />
                            <path d="M6.168 18.849a4 4 0 0 1 3.832 -2.849h4a4 4 0 0 1 3.834 2.855" />
                            </svg>
                            REGISTRO
                        </legend>
                        <div class="contenedor-camposSignup">
                            <div class="campo">
                                <label class="campo__label" for="titulo">Titulo</label>
                                <input class="campo__field" type="text" placeholder="titulo" id="titulo" name="titulo">
                            </div>
                            <div class="campo">
                                <label class="campo__label" for="autor">Autor</label>
                                <input class="campo__field" type="text" placeholder="autor" id="autor" name="autor">
                            </div> 
                            <div class="campo">
                                <label class="campo__label" for="ISBN">ISBN</label>
                                <input class="campo__field" type="text" placeholder="isbn" id="isbn" name="isbn">
                            </div> 
                            <div class="campo">
                                <label class="campo__label" for="genero">Genero</label>
                                <input class="campo__field" type="text" placeholder="genero" id="genero" name="genero">
                            </div> 
                            <div class="campo">
                                <label class="campo__label" for="fecha_publicacion">Fecha publicacion</label>
                                <input class="campo__field" type="text" placeholder="fecha publicacion" id="fecha_publicacion" name="fecha_publicacion">
                            </div>    
                            <div class="campo">
                                <label class="campo__label" for="seccion">Seccion</label>
                                <input class="campo__field" type="text" placeholder="seccion" id="seccion" name="seccion">
                            </div>    
                            <div class="campo">
                                <label class="campo__label" for="ejemplares">Ejemplares</label>
                                <input class="campo__field" type="text" placeholder="ejemplares" id="ejemplares" name="ejemplares">
                            </div>    
                        </div>
                        <div class="flex alinear-centro">
                            <button class="boton enviar w-sm-100" name="accion" type="submit" value="agregarLibro">Registrarme</button>
                            <a href="home.jsp" class="boton enviar w-sm-100" type="button">Volver</a>
                        </div>
                    </fieldset>
                </form>
            </section>
        </main>
        <script src="menu.js"></script> 
    </body>
</html>