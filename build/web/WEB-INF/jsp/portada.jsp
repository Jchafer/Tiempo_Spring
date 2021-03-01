<%@page import="project.model.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="project.model.Cotanieve"%>
<%@page import="org.springframework.web.util.HtmlUtils"%>
<%@page import="project.model.Dia"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <STYLE type="text/css">            
            th,tr { text-align: center }
            #textos{
                display: flex;
                justify-content: space-between;
                justify-content: space-around;
            }
            #texto{
                margin-top: 200px;
                float: left;
                border-top: 5px solid gray;
                border-right: 5px solid gray;
                border-bottom: 5px solid gray;
                border-left: 5px solid gray;
            }
            #carouselExampleControls{
                display: flex;
                margin: 0 auto;
                margin-top: 120px;
                width: 1200px;
                height: 800px;                
            }
            #imagen{
                width: 1200px;
            }
            #btnNuevo { 
                margin-top: 10px;
                padding: 10px;
                float: left;
            }
            body {
                background-image: url("https://image.freepik.com/vector-gratis/fondo-borroso-colores-claros_1034-245.jpg");                
                background-repeat: no-repeat;
                background-size: cover;
                min-height: 100%;
            }
            footer {
                margin-bottom: 60px;
                position: absolute;
                bottom: 0;
                width: 100%;
                height: 10px;
                color: white;
            }
        </STYLE>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>El Tiempo</title>
        <!--<link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet">
        <link href="<%=request.getContextPath()%>/css/bootstrap-responsive.css" rel="stylesheet">
        <script type="text/javascript"  src="<%=request.getContextPath()%>/js/jquery-1.9.0.js"></script>
        <script type="text/javascript"  src="<%=request.getContextPath()%>/js/bootstrap.js" ></script>-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
            <div class="container">
                <a class="navbar-brand" href="<%=request.getContextPath() + "/index.html"%>">El Tiempo</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <ul class="navbar-nav ml-auto">                        
                    <li class="nav-item">
                        <div class="dropdown">
                            <a class="navbar-brand" href="<%=request.getContextPath() + "/login.html"%>">Login</a>
                            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                                <span class="navbar-toggler-icon"></span>
                            </button> 
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
        <div id="carouselExampleControls" class="carousel slide" data-ride="carousel" >
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img class="d-block w-100" src="${pageContext.request.contextPath}/img/Imagen_fondo1" alt="First slide">
                </div>
                <div class="carousel-item">
                    <img class="d-block w-100" src="${pageContext.request.contextPath}/img/Imagen_fondo2" alt="Second slide">
                </div>
                <div class="carousel-item">
                    <img class="d-block w-100" src="${pageContext.request.contextPath}/img/Imagen_fondo3" alt="Third slide" height="675px">
                </div>
            </div>
            <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
        <footer class="fixed-bottom">
            <div class="card-footer text-muted" >
                <small id="autor" class="ml-4 ml-sm-5 mb-2">Autor: Jesús Cháfer</small><br>
                <small class="ml-4 ml-sm-5 mb-2">Copyright &copy; 2021. All rights reserved.</small>
            </div> 
        </footer>
    </body>    
</html>