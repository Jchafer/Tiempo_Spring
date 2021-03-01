<%@page import="project.model.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="project.model.Cotanieve"%>
<%@page import="org.springframework.web.util.HtmlUtils"%>
<%@page import="project.model.Dia"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Dia> dias = (List<Dia>) request.getAttribute("dias");
    List<Cotanieve> cotanieves = (List<Cotanieve>) request.getAttribute("cotanieves");
    Usuario usuario = (Usuario) request.getAttribute("usuPass");
%>
<!DOCTYPE html>
<html>
    <head>
        <STYLE type="text/css">            
            th,tr { text-align: center }
            h1 { 
                text-align: center;
                margin-right: 100px;                
            }
            #btnNuevo { 
                margin-top: 70px;
                margin-bottom: 20px;
                padding: 10px;
                float: left;
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
                <h1><a id="btnNuevo" class="btn btn-primary" href="<%=request.getContextPath()%>/dia/newForInsert.html?usuPass=<%=usuario.getId()%>">Nuevo Día</a>Listado de días</h1><br>
            </div>
        </header>
        <div class="container">
            <div class="offset1  span10">
                <div class="row-fluid">
                    <div class="span12">
                        <table class="table table-sm">
                            <thead>
                                <tr class="bg-primary">
                                    <th>ID</th>
                                    <th>Población</th>
                                    <th>Fecha</th>
                                    <th>Temperatura máxima</th>
                                    <th>Temperatura mínima</th>
                                    <th>Eliminar</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int i = 1;
                                    String id = "", href = "";
                                    for (Dia dia : dias) {
                                        ArrayList<Cotanieve> listaCotas = new ArrayList<>();
                                        for (Cotanieve cota : cotanieves) {
                                            if (cota.getDia().getIdDia() == dia.getIdDia()) {
                                                listaCotas.add(cota);
                                            }
                                        }
                                        id = "accordion" + i;
                                        href = "collapse" + i;
                                        i++;
                                %>
                                <tr class="table-active" id="<%=id%>" data-toggle="collapse" data-parent="#<%=id%>" href="#<%=href%>">
                                    <td><a href="<%=request.getContextPath()%>/dia/readForUpdate.html?id=<%=dia.getIdDia()%>&usuPass=<%=usuario.getId()%>" title="Editar" ><%=dia.getIdDia()%></a></td>
                                    <td><%=HtmlUtils.htmlEscape(dia.getPoblacion())%></td>
                                    <td><%=HtmlUtils.htmlEscape(dia.getFechaAString())%></td>
                                    <td><%=HtmlUtils.htmlEscape(dia.getTempMaxima())%></td>
                                    <td><%=HtmlUtils.htmlEscape(dia.getTempMinima())%></td>
                                    <td>
                                        <a href="<%=request.getContextPath()%>/dia/readForDelete.html?id=<%=dia.getIdDia()%>&usuPass=<%=usuario.getId()%>" title="Borrar" ><i class="bi bi-trash"></i></a>
                                    </td>
                                </tr>
                                <tr style="padding:0px;">
                                    <td colspan="3" style="padding:0px;">                                            
                                        <table id="<%=href%>" class="collapse in p-3 table table-bordered table-hover table-condensed">
                                            <thead>
                                                <tr class="bg-primary">
                                                    <th>ID</th>
                                                    <th>Periodo</th>
                                                    <th><a id="btnNuevoCuota" class="btn btn-dark" href="<%=request.getContextPath()%>/cotanieve/newForInsert.html?id=<%=dia.getIdDia()%>&usuPass=<%=usuario.getId()%>">Nueva Cota de nieve</a></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    for (Cotanieve cotaNie : listaCotas) {
                                                %>
                                                <tr class="table-dark">
                                                    <td><a href="<%=request.getContextPath()%>/cotanieve/readForUpdate.html?id=<%=cotaNie.getIdCotaNieve()%>&usuPass=<%=usuario.getId()%>" title="Editar" ><%=cotaNie.getIdCotaNieve()%></a></td>
                                                    <td><%=cotaNie.getPeriodo()%></td>
                                                    <td>
                                                        <a href="<%=request.getContextPath()%>/cotanieve/readForDelete.html?id=<%=cotaNie.getIdCotaNieve()%>&usuPass=<%=usuario.getId()%>" title="Borrar" ><i class="bi bi-trash"></i></a>
                                                    </td>
                                                </tr>
                                                <%}%>
                                        </table>
                                    </td>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
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