<%-- 
    Document   : buscar-eventos
    Created on : 08/02/2015, 20:59:52
    Author     : Emanuel Batista da Silva Filho <emanuelbatista2011@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="f" uri="bibliotecas"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/buscar-eventos.css" rel="stylesheet" type="text/css">
        <link href="css/menu.css" rel="stylesheet" type="text/css">
        <title>Buscar Eventos</title>
    </head>
    <body>
        <%@include  file="menu.html"%>
        <div class="container-page">
            <form action="buscar-evento" method="post" class="form-inline campos">
                <div class="form-group">
                    <label for="nome">Nome: </label>
                    <input type="text" name="nome" <c:if test="${param.nome!=null}">value="${param.nome}"</c:if> id="nome" class="form-control" placeholder="Digite o nome do Evento">
                    </div>
                    <div class="form-group">
                        <label for="descricao">Descrição: </label>
                        <input type="text" name="descricao" <c:if test="${param.descricao!=null}">value="${param.descricao}"</c:if>  id="descricao" class="form-control" placeholder="Digite a Descrição do Evento">
                    </div>
                    <div class="form-group">
                        <label for="data">Data: </label>
                        <input type="text" name="data" id="data" <c:if test="${param.data!=null}">value="${param.data}"</c:if> class="form-control" placeholder="Digite a Data do Evento">
                    </div>
                    <div class="form-group">
                        <label for="responsavel">Responsável: </label>
                        <input type="text" name="responsavel" <c:if test="${param.responsavel!=null}">value="${param.responsavel}"</c:if> id="responsavel" class="form-control" placeholder="Digite o Nome do Responsavel do Evento">
                    </div>
                    <div class="form-group">
                        <label>Filtro de Status: </label>         
                        <br>
                        <input type="checkbox" name="status" id="alocado" value="Alocado">
                        <label for="alocado">Alocado</label>
                        <input type="checkbox" name="status" id="cancelado" value="Cancelado">
                        <label for="cancelado">Cancelado</label>
                        <input type="checkbox" name="status" id="pendenteLocal" value="Pendente de Local">
                        <label for="pendenteLocal">Pendente de Local</label>
                        <input type="checkbox" name="status" id="realizado" value="Realizado">
                        <label for="realizado">Realizado</label>
                    </div>
                <br>
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
                        <c:forEach var="i" items="${eventosPesquisados}">
                            <c:if test="${i.status=='Pendente de Local'}">
                                <tr class="warning">
                                </c:if>
                                <c:if test="${i.status=='Cancelado'}">
                                <tr class="danger">
                                </c:if>
                                <c:if test="${i.status=='Realizado'}">
                                <tr class="info">
                                </c:if>
                                <c:if test="${i.status=='Alocado'}">
                                <tr class="success">
                                </c:if>
                                <td>${i.id}</td>
                                <td>${i.nome}</td>
                                <td>${i.descricao}</td>
                                <td>${i.responsavel}</td>
                                <td>${f:formatarData(i.dataInicio)}</td>
                                <td>${f:formatarData(i.dataFinal)}</td>
                                <td>${i.status}</td>
                            </tr>


                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
