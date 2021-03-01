<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>El Tiempo</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->

        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <!-- Include the above in your HEAD tag -->
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
    </head>

    <body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
        <div>
            <a class="navbar-brand" href="<%=request.getContextPath() + "/index.html"%>">El Tiempo</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>                
        </div>
    </nav>
    <div class="main">


        <div class="container">
            <center>
                <div class="middle">
                    <div id="login">

                        <form action="<%=request.getContextPath() + "/usuario.html"%>">

                            <fieldset class="clearfix">

                                <p ><span class="fa fa-user"></span><input type="text" name="txtnom" Placeholder="Nombre" required></p> <!-- JS because of IE support; better: placeholder="Username" -->
                                <p><span class="fa fa-envelope-o fa-fw"></span><input type="text"  name="txtcorreo" Placeholder="Correo" required></p> <!-- JS because of IE support; better: placeholder="Password" -->

                                <div>
                                    
                                    <span style="width:50%; text-align:right;  display: inline-block;"><input type="submit" value="Entrar"></span>
                                </div>

                            </fieldset>
                            <div class="clearfix"></div>
                        </form>

                        <div class="clearfix"></div>

                    </div> <!-- end login -->
                    <div class="logo">
                        <img src="${pageContext.request.contextPath}/img/Logo_clima.png">
                        <div class="clearfix"></div>
                    </div>

                </div>
            </center>
        </div>

    </div>
    <footer class="fixed-bottom">
        <div class="card-footer text-muted" >
            <small id="autor" class="ml-4 ml-sm-5 mb-2">Autor: Jesús Cháfer</small><br>
            <small class="ml-4 ml-sm-5 mb-2">Copyright &copy; 2021. All rights reserved.</small>
        </div> 
    </footer>
</body>
</html>
