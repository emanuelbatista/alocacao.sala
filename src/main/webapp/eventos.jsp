<%-- 
    Document   : eventos
    Created on : 22/02/2015, 07:30:30
    Author     : Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="bibliotecas" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/menu.css" rel="stylesheet" type="text/css">
        <link href="css/eventos.css" rel="stylesheet" type="text/css">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="menu.html"/>
        <div class="caixa-container">
            <article>
                <c:if test="${possuiEventosRealizados}">
                    <section>
                        <div class="notificacao">
                            Existe eventos realizados confirme os mesmos
                            <br>
                            Clique aqui: <a href="">Eventos Realizados</a>
                        </div>
                    </section>
                </c:if>
                <section>
                    <ul class="eventos">
                        <c:forEach var="evento" items="${eventos}">
                            <li 
                                class="
                                <c:if test="${evento.status=='Alocado'}">bg-success</c:if>
                                <c:if test="${evento.status=='Pendente de Local'}">bg-warning</c:if>

                                    ">
                                    <div class="info-evento">
                                        <b>Nome: </b>${evento.nome}
                                    <br>
                                    <b>Descrição: </b><br>
                                    ${evento.descricao}
                                    <br>
                                    <b>Data de Inicio: </b>${f:formatarData(evento.dataInicio)}
                                    <br>
                                    <b>Data Final: </b>${f:formatarData(evento.dataFinal)}
                                    <br>
                                    <b>Responsável: </b>${evento.responsavel}        
                                    <br>
                                    <b>Total de Parcipantes: </b>${evento.totalParticipantes}
                                    <br>
                                    <b>Status: </b>${evento.status}
                                </div>

                                <div class="opcaoes"> 
                                    <c:if test="${evento.status=='Alocado'}"><a href="" class="btn btn-warning">Desalocar</a></c:if>
                                    <c:if test="${evento.status=='Pendente de Local'}"><a href="alocar?id=${evento.id}" class="btn btn-success">Alocar</a></c:if>

                                    </div>
                                </li>
                        </c:forEach>
                    </ul>
                </section>
            </article>
        </div>
    </body>
</html>
