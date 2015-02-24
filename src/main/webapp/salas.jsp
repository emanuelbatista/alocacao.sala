<%-- 
    Document   : salas
    Created on : 23/02/2015, 19:58:11
    Author     : Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/menu.css" rel="stylesheet" type="text/css">
        <link href="css/salas.css" rel="stylesheet" type="text/css">
        <title>Salas</title>
    </head>
    <body>
        <jsp:include page="menu.html"/>
        <div class="caixa-container">
            <article>
                <section>
                    <h3>Salas</h3>
                    <ul class="salas">
                        <c:forEach var="sala" items="${salas}">
                            <li>
                                <div class="info-sala">
                                    <b>Identificação: </b>${sala.identificacao}
                                    <br> 
                                    <b>Apelido: </b>${sala.apelido}
                                    <br>
                                    <b>Capacidade: </b>${sala.capacidade}
                                    <br>
                                    <b>Tipo de Sala: </b>${sala.tipo}
                                </div>
                                <div class="opcoes">
                                    <a href="editar-sala?id=${sala.id}" class="btn btn-warning">Editar</a>
                                    <a href="remover-sala?id=${sala.id}" class="btn btn-danger">Remover</a>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </section>
            </article>
        </div>
    </body>
</html>
