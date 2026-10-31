<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${empty produto ? 'Registar Produto' : 'Editar Produto'}</title>
    </head>
    <body>
        <div style="margin: 30px auto; width: 50%; font-family: Arial, sans-serif;">
            <h2>${empty produto ? 'Registar Novo Produto' : 'Editar Produto'}</h2>
            <hr/>
            <form action="ServletProduto" method="POST">
                <!-- Define se a ação é inserir ou atualizar e passa o ID oculto se for edição -->
                <input type="hidden" name="acao" value="${empty produto ? 'inserir' : 'atualizar'}"/>
                <c:if test="${not empty produto}">
                    <input type="hidden" name="id" value="${produto.idproduto}"/>
                </c:if>
                
                <p>
                    <label>Nome do Produto:</label><br/>
                    <input type="text" name="nome" value="${produto.nome}" required style="width: 100%; padding: 8px; margin-top: 5px;" />
                </p>
                <p>
                    <label>Quantidade:</label><br/>
                    <input type="number" name="quantidade" value="${produto.quantidade}" required style="width: 100%; padding: 8px; margin-top: 5px;" />
                </p>
                <p>
                    <label>Preço de Venda (R$):</label><br/>
                    <input type="text" name="preco" value="${produto.precoVenda}" required style="width: 100%; padding: 8px; margin-top: 5px;" />
                </p>
                <p style="margin-top: 20px;">
                    <input type="submit" value="${empty produto ? 'Salvar' : 'Atualizar'}" style="padding: 10px 20px; background-color: #4CAF50; color: white; border: none; cursor: pointer;" />
                    <a href="ServletProduto?acao=listar" style="margin-left: 15px; text-decoration: none; color: #333;">Cancelar</a>
                </p>
            </form>
        </div>
    </body>
</html>