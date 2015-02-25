
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/cadastro.css" rel="stylesheet" type="text/css">
        <link href="css/menu.css" rel="stylesheet" type="text/css">
        <title>Cadastro Sala</title>
    </head>
    <body>
        <%@include file="menu.html"%>
        <div class="formulario">
            <c:if test="${mensagemErro!=null}">
                <div class="notificacao">
                    ${mensagemErro}
                </div>
            </c:if>
            <h3>Cadastro de Sala</h3>
            <form action="cadastro-sala" method="post">
                <div class="form-group">
                    <label for="identificacao">Identificação</label>
                    <input type="text" name="identificacao" required class="form-control" id="identificacao" placeholder="Digite a Identificação da sala">
                </div>
                <div class="form-group">
                    <label for="apelido">Apelido</label>
                    <input type="text" name="apelido" class="form-control" id="apelido" placeholder="Digite o Apelido da sala">
                </div>
                <div class="form-group">
                    <label for="capacidade">Capacidade de Pessoas na Sala</label>
                    <input type="number" min="1" name="capacidade" required class="form-control" id="capacidade" placeholder="Digite capacidade da sala">
                </div>
                <div class="form-group">
                    <label for="tipo">Tipo de Sala</label>
                    <select class="form-control" name="tipo" id="tipo">
                        <option value="Aula Normal">Aula Normal</option>
                        <option value="Aula Inteligente">Aula Inteligente</option>
                        <option value="Laboratório">Laboratorio</option>
                        <option value="Conferência">Conferência</option>
                        <option value="Video Conferência">Video Conferência</option>
                    </select>
                </div>
                <div class="form-group submit">
                    <input type="submit" class="btn btn-success" value="Cadastrar">

                </div>
            </form>
        </div>
        <!--[if lt IE 9]>
            <script src="js/html5.js" type="text/javascript"></script>
        <![endif]-->
    </body>
</html>
