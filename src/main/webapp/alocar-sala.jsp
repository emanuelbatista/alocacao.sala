<%-- 
    Document   : alocar-sala
    Created on : 06/02/2015, 10:12:04
    Author     : Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/alocar-sala.css" rel="stylesheet" type="text/css">
        <link href="css/menu.css" rel="stylesheet" type="text/css">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="menu.html"/>
        <div  class="caixa-container">
            <ul class="salas">
                <c:forEach var="i" items="${salasDisponiveis}">
                    <li>
                        <a href="alocar?id=${i.id}">
                        Identificação:
                        ${i.identificacao}
                        <br>
                        <c:if test="${i.apelido!=null}">
                            Apelido: ${i.apelido}
                        </c:if>
                        <br>
                        Capacidade: ${i.capacidade}
                        <br>
                        Tipo: ${i.tipo}
                        </a>
                        
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="rodape">
            <a href="salvar-eventos" class="btn btn-warning botao-cancelar">Cancelar Alocação e Salvar Evento</a>
        </div>
    </body>
</html>
