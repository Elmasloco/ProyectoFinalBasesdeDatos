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
    <body class="bodyLogin">
        <header class="headerLogin">
            <div class="wrapper2">
                <nav class="nav">
                    <a href="index.jsp" class="nav-item " active-color="orange">Home</a>
                    <a href="login.jsp" class="nav-item is-active" active-color="green">Login</a>
                    <a href="signup.jsp" class="nav-item" active-color="blue">Sign Up</a>
                    <span class="nav-indicator"></span>
                </nav>
            </div>
        </header>
        <main class="mainLogin">
            <section class="seccionFormulario">
                <form action="Controlador" method="POST" class="formularioLogin">
                    <fieldset class="formularioLogin__fieldset">
                        <legend class="formularioLogin__legend">
                            <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-user-circle" width="44" height="44" viewBox="0 0 24 24" stroke-width="1.5" stroke="#2c3e50" fill="none" stroke-linecap="round" stroke-linejoin="round">
                            <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
                            <path d="M12 12m-9 0a9 9 0 1 0 18 0a9 9 0 1 0 -18 0" />
                            <path d="M12 10m-3 0a3 3 0 1 0 6 0a3 3 0 1 0 -6 0" />
                            <path d="M6.168 18.849a4 4 0 0 1 3.832 -2.849h4a4 4 0 0 1 3.834 2.855" />
                            </svg>
                            INICIO DE SESIÓN
                        </legend>
                        <div class="contenedor-campos">
                            <div class="campo">
                                <label class="campo__label" for="rol">Rol</label>
                                <select name="rol" id="rol" class="campo__field">
                                    <option value="seleccioneRol">Selecciones su rol</option>
                                    <option value="admin">Administrador</option>
                                    <option value="user">Usuario</option>
                                </select>
                            </div>
                            <div class="campo">
                                <label class="campo__label" for="correo">Correo</label>
                                <input class="campo__field" type="mail" placeholder="alguien@algo.com" id="correo" name="correo">
                            </div>

                            <div class="campo" id="campo-contraseña">
                                <label class="campo__label" for="contraseña">Contraseña</label>
                                <input class="campo__field" type="password" placeholder="Tu contraseña de admin." id="contraseña" name="contra">
                            </div> 
                        </div>
                        <div class="flex alinear-centro">
                            <button class="boton enviar w-sm-100" name="accion" type="submit" value="ingresar">Ingresar</button>
                        </div>
                    </fieldset>
                </form>
            </section>
        </main>
        <script src="menu.js"></script> 
        <script src="login.js"></script>
    </body>
</html>