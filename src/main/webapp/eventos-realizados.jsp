<%-- 
    Document   : eventos-realizados
    Created on : 22/02/2015, 17:04:03
    Author     : Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f"  uri="bibliotecas"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/menu.css" rel="stylesheet" type="text/css">
        <link href="css/eventos-realizados.css" rel="stylesheet" type="text/css">
        <title>Eventos Levantamento</title>
    </head>
    <body>
        <jsp:include page="menu.html"/>
        <div class="caixa-container">
            <article>
                <section>
                    <ul class="eventos">
                        <c:forEach var="evento" items="${eventosRealizados}">
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

                                <div class="opcoes"> 
                                    <a href="levantamento-eventos?id=${evento.id}&confirmacao=Realizado&status=${evento.status}" class="btn btn-success">Realizado</a>
                                    <a href="levantamento-eventos?id=${evento.id}&confirmacao=Cancelado&status=${evento.status}" class="btn btn-warning">Cancelado</a>

                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </section>
            </article>
        </div>
    </body>
</html>
