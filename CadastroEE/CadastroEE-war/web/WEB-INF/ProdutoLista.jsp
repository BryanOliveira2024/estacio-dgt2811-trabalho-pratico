<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Produtos</title>
    </head>
    <body>
        <div style="margin: 30px auto; width: 80%; font-family: Arial, sans-serif;">
            <h1>Gestão de Produtos - Loja</h1>
            <hr/>
            <p>
                <a href="ServletProduto?acao=novo">Adicionar Novo Produto</a>
            </p>
            
            <c:if test="${not empty erro}">
                <p style="color: red; font-weight: bold;">${erro}</p>
            </c:if>

            <table border="1" cellpadding="10" cellspacing="0" style="width: 100%; border-collapse: collapse;">
                <thead>
                    <tr style="background-color: #f2f2f2;">
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Quantidade</th>
                        <th>Preço de Venda</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty produtos}">
                            <c:forEach var="p" items="${produtos}">
                                <tr>
                                    <td>${p.idproduto}</td>
                                    <td>${p.nome}</td>
                                    <td>${p.quantidade}</td>
                                    <td>R$ ${p.precoVenda}</td>
                                    <td>
                                        <a href="ServletProduto?acao=editar&id=${p.idproduto}">Editar</a> | 
                                        <a href="ServletProduto?acao=remover&id=${p.idproduto}" onclick="return confirm('Tem certeza que deseja apagar este produto?');">Excluir</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="5" style="text-align: center;">Nenhum produto registado.</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>
    </body>
</html> 