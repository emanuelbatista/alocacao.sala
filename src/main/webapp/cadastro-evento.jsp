<!DOCTYPE html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Cadastro de Evento</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/cadastro.css" rel="stylesheet" type="text/css">
        <link href="css/menu.css" rel="stylesheet" type="text/css">
        <link href="JQuery/jquery.datetimepicker.css" type="text/css" rel="stylesheet">
        <script src="JQuery/jquery.js" type="text/javascript"></script>
        <script src="js/html5.js" type="text/javascript"></script>
        <script src="js/modernizr.js" type="text/javascript"></script>
        <script src="JQuery/jquery.datetimepicker.js" type="text/javascript"></script>

        <script>
            $(function () {
                $('#dataInicio').datetimepicker({
                    lang: "pt-BR",
                    format: 'd/m/Y H:m'
                });
                $('#dataFinal').datetimepicker({
                    lang: "pt-BR",
                    format: 'd/m/Y H:m'
                });
            });
        </script>
    </head>
    <body>
        <jsp:include page="menu.html"/>
        <div class="formulario">
            <h3>Cadastro de Evento</h3>
            <form action="cadastro-evento" method="post">
                <div class="form-group">
                    <label for="nome">Nome</label>
                    <input type="text" name="nome" required class="form-control" id="nome" placeholder="Digite o Nome do Evento">
                </div>
                <div class="form-group">
                    <label for="descricao">Descrição</label>
                    <input type="text" name="descricao" class="form-control" id="descricao" placeholder="Digite a Descrição do Evento">
                </div>
                <div class="form-group">
                    <label for="responsavel">Responsável</label>
                    <input type="text" name="responsavel" class="form-control" required id="responsavel" placeholder="Digite o Nome do Responsavel do Evento">
                </div>
                <div class="form-group">
                    <label for="dataInicio">Data de Inicio</label>
                    <input type="text" name="dataInicio" maxlength="2" required class="form-control" placeholder="Selecione a Data Inicial do Evento" id="dataInicio">
                </div>
                <div class="form-group">
                    <label for="dataFinal">Data do Fim</label>
                    <input type="text" name="dataFinal"  required class="form-control" placeholder="Selecione a Data Final do Evento" id="dataFinal">
                </div>
                <div class="form-group">
                    <label for="totalParticipantes">Total de Participantes</label>
                    <input type="number" name="totalParticipantes" required class="form-control" id="totalParticipantes" placeholder="Digite o total de Partcipantes desse Evento">
                </div>
                <div class="form-group">
                    <label for="totalRepeticao">Repetições desse Evento</label>
                    <input type="number" name="totalRepeticao" required class="form-control" id="totalRepeticao" placeholder="Digite o total de Repetições desse Evento">
                </div>
                <div class="form-group submit">
                    <input type="submit" name="submit" class="btn btn-success" value="Cadastrar">
                    <input type="submit" name="submit" class="btn-info btn" value="Alocar Sala">
                </div>
            </form>

        </div>
    </body>
</html>
