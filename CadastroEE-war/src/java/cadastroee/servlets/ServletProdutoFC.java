package cadastroee.servlets;

import cadastroee.controller.ProdutoFacadeLocal;
import cadastroee.model.Produto;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletProdutoFC extends HttpServlet {

    @EJB
    ProdutoFacadeLocal facade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String acao = request.getParameter("acao");
        if (acao == null || acao.isEmpty()) {
            acao = "listar";
        }

        String destino = "ProdutoLista.jsp";

        switch (acao) {
            case "formIncluir":
                destino = "ProdutoDados.jsp";
                break;

            case "formAlterar": {
                destino = "ProdutoDados.jsp";
                Integer id = Integer.valueOf(request.getParameter("id"));
                Produto produto = facade.find(id);
                request.setAttribute("produto", produto);
                break;
            }

            case "excluir": {
                Integer id = Integer.valueOf(request.getParameter("id"));
                Produto produto = facade.find(id);
                if (produto != null) {
                    facade.remove(produto);
                }
                request.setAttribute("produtos", facade.findAll());
                break;
            }

            case "alterar": {
                Integer id = Integer.valueOf(request.getParameter("id"));
                Produto produto = facade.find(id);
                preencher(produto, request);
                facade.edit(produto);
                request.setAttribute("produtos", facade.findAll());
                break;
            }

            case "incluir": {
                Produto produto = new Produto();
                preencher(produto, request);
                facade.create(produto);
                request.setAttribute("produtos", facade.findAll());
                break;
            }

            case "listar":
            default:
                request.setAttribute("produtos", facade.findAll());
                break;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destino);
        dispatcher.forward(request, response);
    }

    private void preencher(Produto produto, HttpServletRequest request) {
        produto.setNome(request.getParameter("nome"));
        produto.setQuantidade(Integer.valueOf(request.getParameter("quantidade")));
        String preco = request.getParameter("precoVenda").replace(",", ".");
        produto.setPrecoVenda(Float.valueOf(preco));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}