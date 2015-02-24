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
                <section>
                    <h4>Eventos por Status</h4>
                    <ul class="opcoes-eventos">
                        <li><a href="eventos-status?status=Alocado" class="btn btn-success">Alocados</a></li>
                        <li><a href="eventos-status?status=Cancelado" class="btn btn-danger">Cancelados</a></li>
                        <li><a href="eventos-status?status=Realizado" class="btn btn-info">Realizados</a></li>
                        <li><a href="eventos-status?status=Pendente%20de%20Local" class="btn btn-warning">Pendente de Local</a></li>
                    </ul>
                </section>
                <c:if test="${possuiEventosRealizados}">
                    <section>
                        <div class="notificacao">
                            Existe eventos realizados confirme os mesmos
                            <br>
                            Clique aqui: <a href="eventos-realizados">Eventos Realizados</a>
                        </div>
                    </section>
                </c:if>
                <section>
                    <h3>Eventos</h3>
                    <ul class="eventos">
                        <c:forEach var="evento" items="${eventos}">
                            <li
                                <c:if test="${evento.status=='Alocado'}">class="bg-success"</c:if>
                                <c:if test="${evento.status=='Pendente de Local'}">class="bg-warning"</c:if>
                                <c:if test="${evento.status=='Realizado'}">class="bg-info"</c:if>
                                <c:if test="${evento.status=='Cancelado'}">class="bg-danger"</c:if>
                                    >
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
                                    <c:if test="${evento.sala!=null}">
                                        <br>
                                        <b>Sala Alocada: </b>${evento.sala.identificacao}
                                    </c:if>
                                </div>

                                <div class="opcoes"> 
                                    <c:if test="${evento.status=='Alocado'}"><a href="desalocar?id=${evento.id}" class="btn btn-warning">Desalocar</a></c:if>
                                    <c:if test="${evento.status=='Pendente de Local'}"><a href="evento-alocacao?id=${evento.id}" class="btn btn-success">Alocar</a></c:if>
                                    <c:if test="${evento.status!='Cancelado'}"><a href="cancelar-evento?id=${evento.id}" class="btn btn-danger">Cancelar</a></c:if>
                                    </div>
                                </li>
                        </c:forEach>
                    </ul>
                </section>
            </article>
        </div>
    </body>
</html>
