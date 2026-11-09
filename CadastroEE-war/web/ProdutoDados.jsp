<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="cadastroee.model.Produto"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body class="container">
        <h1>Dados do Produto</h1>
        <%
            Produto produto = (Produto) request.getAttribute("produto");
            String acao = (produto == null) ? "incluir" : "alterar";
        %>
        <form class="form" action="ServletProdutoFC" method="post">
            <input type="hidden" name="acao" value="<%= acao %>">
            <% if (produto != null) { %>
            <input type="hidden" name="id" value="<%= produto.getId() %>">
            <% } %>

            <div class="mb-3">
                <label class="form-label" for="nome">Nome:</label>
                <input class="form-control" type="text" id="nome" name="nome"
                       value="<%= produto != null ? produto.getNome() : "" %>" required>
            </div>
            <div class="mb-3">
                <label class="form-label" for="quantidade">Quantidade:</label>
                <input class="form-control" type="number" id="quantidade" name="quantidade"
                       value="<%= produto != null ? produto.getQuantidade() : "" %>" required>
            </div>
            <div class="mb-3">
                <label class="form-label" for="precoVenda">Preco de Venda:</label>
                <input class="form-control" type="text" id="precoVenda" name="precoVenda"
                       value="<%= produto != null ? produto.getPrecoVenda() : "" %>" required>
            </div>

            <input class="btn btn-primary" type="submit"
                   value="<%= produto == null ? "Adicionar Produto" : "Alterar Produto" %>">
        </form>
    </body>
</html>