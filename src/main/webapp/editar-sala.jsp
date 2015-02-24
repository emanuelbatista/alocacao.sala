<%-- 
    Document   : editar-sala
    Created on : 23/02/2015, 20:35:45
    Author     : Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/cadastro.css" rel="stylesheet" type="text/css">
        <link href="css/menu.css" rel="stylesheet" type="text/css">
        <title>Editar Sala</title>
    </head>
    <body>
        <%@include file="menu.html"%>
        <div class="formulario">
            <h3>Cadastro de Sala</h3>
            <form action="salvar-sala" method="post">
                <div class="form-group">
                    <label for="identificacao">Identificação</label>
                    <input type="text" value="${sala.identificacao}" name="identificacao" required class="form-control" id="identificacao" placeholder="Digite a Identificação da sala">
                </div>
                <div class="form-group">
                    <label for="apelido">Apelido</label>
                    <input type="text" value="${sala.apelido}" name="apelido" class="form-control" id="apelido" placeholder="Digite o Apelido da sala">
                </div>
                <div class="form-group">
                    <label for="capacidade">Capacidade de Pessoas na Sala</label>
                    <input type="number" value="${sala.capacidade}" name="capacidade" required class="form-control" id="capacidade" placeholder="Digite capacidade da sala">
                </div>
                <div class="form-group">
                    <label for="tipo">Tipo de Sala</label>
                    <select class="form-control" name="tipo" id="tipo">
                        <option <c:if test="${sala.tipo=='Aula Normal'}">selected</c:if> value="Aula Normal">Aula Normal</option>
                        <option <c:if test="${sala.tipo=='Aula Inteligente'}">selected</c:if>  value="Aula Inteligente">Aula Inteligente</option>
                        <option <c:if test="${sala.tipo=='Laboratório'}">selected</c:if> value="Laboratório">Laboratório</option>
                        <option <c:if test="${sala.tipo=='Conferência'}">selected</c:if>  value="Conferência">Conferência</option>
                        <option <c:if test="${sala.tipo=='Video Conferência'}">selected</c:if> value="Video Conferência">Video Conferência</option>
                        </select>
                    </div>
                    <input type="hidden" value="${sala.id}" name="id">
                <div class="form-group submit">
                    <input type="submit" class="btn btn-success" value="Salvar">
                </div>
            </form>
        </div>
        <!--[if lt IE 9]>
            <script src="js/html5.js" type="text/javascript"></script>
        <![endif]-->
    </body>
</html>
