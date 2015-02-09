<%-- 
    Document   : buscar-eventos
    Created on : 08/02/2015, 20:59:52
    Author     : Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/buscar-eventos.css" rel="stylesheet" type="text/css">
        <link href="css/menu.css" rel="stylesheet" type="text/css">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include  file="menu.html"%>
        <div class="container-page">
            <form action="buscar-evento" method="post" class="form-inline campos">
                <div class="form-group">
                    <label for="nome">Nome: </label>
                    <input type="text" name="nome" id="nome" class="form-control" placeholder="Digite o nome do Evento">
                </div>
                <div class="form-group">
                    <label for="descricao">Descrição: </label>
                    <input type="text" name="descricao" id="descricao" class="form-control" placeholder="Digite a Descrição do Evento">
                </div>
                <div class="form-group">
                    <label for="data">Data: </label>
                    <input type="text" name="data" id="data" class="form-control" placeholder="Digite a Data do Evento">
                </div>
                <div class="form-group">
                    <label for="responsavel">Responsável: </label>
                    <input type="text" name="responsavel" id="responsavel" class="form-control" placeholder="Digite o Nome do Responsavel do Evento">
                </div>
                <div class="form-group">
                    <label for="status">Status: </label>         
                    <select name="status" id="status" class="form-control">
                        <option value="Cancelado">Cancelado</option>
                    </select>
                </div>
                <input type="submit" class="btn btn-success" value="Buscar">
            </form>
            <div class="tabela">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Nome</th>
                            <th>Descrição</th>
                            <th>Responsável</th>
                            <th>Data Inicio</th>
                            <th>Data Final</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <c:forEach var="i" items="${eventosPesquisados}">
                                <c:if test="${i.status=='Cancelado'}">
                                    <td>i.id</td>
                                </c:if>
                            </c:forEach>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
