<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="htmlLogin">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="normalize.css">
        <link rel="preload" href="normalize.css" as="style">
        <link rel="stylesheet" href="estiloPrin.css">
        <link rel="preload" href="estiloPrin.css" as="style">
        <title>BIBLIOTECA</title>
    </head>
<body class="bodySignupAdmin">
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
                            REGISTRO ADMINISTRADOR
                        </legend>
                    <div class="contenedor-camposSignup">
                        
                        <div class="campo">
                            <label class="campo__label" for="nombre">Nombre</label>
                            <input class="campo__field" type="text" placeholder="Tu hermoso nombre" id="nombre" name="nombre">
                        </div>

                        <div class="campo">
                            <label class="campo__label" for="apellidos">Apellidos</label>
                            <input class="campo__field" type="text" placeholder="Tus hermosos apellidos" id="apellidos" name="apellidos">
                        </div> 
                        <div class="campo">
                            <label class="campo__label" for="correo">Correo</label>
                            <input class="campo__field" type="email" placeholder="alguien@algo.com" id="correo" name="correo">
                        </div>                         
                        <div class="campo">
                            <label class="campo__label" for="contraseña">Contraseña</label>
                            <input class="campo__field" type="password" placeholder="Tu futura contraseña" id="contraseña" name="contra">
                        </div> 
                    </div>
                    <div class="flex alinear-centro">
                        <button class="boton enviar w-sm-100" name="accion" type="submit" value="agregarAdmin">Registrarme</button>
                    </div>
                </fieldset>
            </form>
        </section>
    </main>
    <script src="menu.js"></script> 
    <script src="login.js"></script> 
</body>
</html>