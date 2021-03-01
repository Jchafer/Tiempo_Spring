<%@page import="project.model.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%@page import="org.springframework.web.util.HtmlUtils"%>
<%@page import="bda.dao.BussinessMessage"%>
<%@page import="project.model.Cotanieve"%>
<%@page import="project.controller.FormOperation"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    FormOperation formOperation = (FormOperation) request.getAttribute("formOperation");
    String labelButton = null;
    String urlAction;
    switch (formOperation) {
        case Insert:
            labelButton = "Insertar";
            urlAction = request.getContextPath() + "/cotanieve/insert.html";
            break;
        case Update:
            labelButton = "Actualizar";
            urlAction = request.getContextPath() + "/cotanieve/update.html";
            break;
        case Delete:
            labelButton = "Borrar";
            urlAction = request.getContextPath() + "/cotanieve/delete.html";
            break;
        default:
            throw new RuntimeException("El valor de 'formOperation' no es válido" + formOperation);
    }
    Usuario usuario = (Usuario) request.getAttribute("usuPass");
%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            *{box-sizing:border-box;}

            h3,
            form{
                width:300px;
                padding:16px;
                border-radius:10px;
                margin:auto;
                background-color:#ccc;
            }
            h3{
                text-align: center;
            }
            header{
                margin-top: 100px;
            }
            form label{
                width:auto;
                padding-right:50px;
                font-weight:bold;
                display:inline-block;
            }

            form input[type="text"],
            form input[type="email"]{
                width:180px;
                padding:3px 10px;
                border:1px solid #f6f6f6;
                border-radius:3px;
                background-color:#f6f6f6;
                margin:8px 0;
                display:inline-block;
            }

            form input[type="submit"]{
                width:100%;
                padding:8px 16px;
                margin-top:32px;
                border:1px solid #000;
                border-radius:5px;
                display:block;
                color:#fff;
                background-color:#000;
            } 

            form input[type="submit"]:hover{
                cursor:pointer;
            }
            html {
                min-height: 100%;
                position: relative;
            }
            body {
                margin: 0;
                margin-bottom: 40px;
            }
            footer {
                position: absolute;
                bottom: 0;
                width: 100%;
                height: 10px;
                color: white;
            }
        </style>
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
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">                        
                        <li class="nav-item">
                            <div class="dropdown">
                                <a style="color: white" href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Menú</a>
                                <div class="dropdown-menu text-center">
                                    <a href="<%=request.getContextPath() + "/dia.html"%>?usuPass=<%=usuario.getId()%>" class="dropdown-item">Dia</a>
                                    <div class="dropdown-divider"></div>
                                    <a href="<%=request.getContextPath() + "/cota.html"%>?usuPass=<%=usuario.getId()%>" class="dropdown-item">Cota</a>
                                </div>
                            </div>
                        </li>
                        <li class="nav-item">
                            <div class="dropdown">
                                <a style="color: white" href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Cerrar Sesion</a>
                                <div class="dropdown-menu text-center">
                                    <a><img src="${pageContext.request.contextPath}/img/user.png" height="80" width="80"></a><br>
                                    <a><%=usuario.getNombres()%></a>
                                    <a><%=usuario.getCorreo()%></a>
                                    <div class="dropdown-divider"></div>
                                    <a href="<%=request.getContextPath() + "/login.html"%>" class="dropdown-item">Salir</a>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <header>
            <div class="container">
                <h3>Datos de Cota de nieve</h3>
            </div>
        </header>
        <div class="container">
            <div>
                <div class="offset1 span10 well">
                    <form action="<%=urlAction%>" method="post" >
                        <fieldset>
                            <div class="form-group">
                                <label class="control-label" for="idCotaNieve">Id:</label>
                                <input class="input-xlarge" id="idCotaNieve" name="idCotaNieve" type="text" placeholder="Auto-Increment" value="${cotanieve.idCotaNieve}" readonly="readonly">
                            </div>
                            <div class="form-group">
                                <label class="control-label" for="periodo">Periodo:</label>
                                <input class="input-xlarge" id="periodo" type="text" name="periodo" value="${cotanieve.periodo}" >
                            </div>
                            <div class="form-group">
                                <label class="control-label" for="dia">Id de Día:</label>
                                <input class="input-xlarge" id="dia" type="text" name="dia" readonly="true" value="${idPass}" >
                                <input type="hidden" name="usuPass" value="<%=usuario.getId()%>">
                            </div>
                        </fieldset>
                        <%
                            if (request.getAttribute("bussinessMessages") != null) {
                        %>
                        <div class="alert alert-error alert-block">
                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                            <ul>
                                <%
                                    for (BussinessMessage bussinessMessage : (Set<BussinessMessage>) request.getAttribute("bussinessMessages")) {
                                %>
                                <li>
                                    <%
                                        if (bussinessMessage.getFieldName() != null) {
                                            out.print("<strong>" + HtmlUtils.htmlEscape(bussinessMessage.getFieldName()) + "</strong>");
                                        }
                                    %>
                                    <%=HtmlUtils.htmlEscape(bussinessMessage.getMessage())%>
                                </li>
                                <%
                                    }
                                %>
                            </ul>
                        </div>
                        <%
                            }
                        %>
                        <div class="form-actions">
                            <button id="aceptarBtn" class="btn btn-primary" type="submit"><%=labelButton%></button>
                            <a class="btn" href="<%=request.getContextPath()%>/cota.html?usuPass=<%=usuario.getId()%>" >Cancelar</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <footer>
            <div class="card-footer text-muted">
                <small id="autor" class="ml-4 ml-sm-5 mb-2">Autor: Jesús Cháfer</small><br>
                <small class="ml-4 ml-sm-5 mb-2">Copyright &copy; 2021. All rights reserved.</small>
            </div> 
        </footer>
    </body>    
</html>