package cadastroee.servlet;

import cadastroee.controller.ProdutoFacadeLocal;
import cadastroee.model.Produto;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletProduto", urlPatterns = {"/ServletProduto"})
public class ServletProduto extends HttpServlet {

    @EJB
    private ProdutoFacadeLocal produtoFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String acao = request.getParameter("acao");
        if (acao == null) {
            acao = "listar";
        }

        try {
            if (acao.equals("listar")) {
                List<Produto> lista = produtoFacade.findAll();
                request.setAttribute("produtos", lista);
                request.getRequestDispatcher("ProdutoLista.jsp").forward(request, response);
                
            } else if (acao.equals("novo")) {
                request.getRequestDispatcher("ProdutoDados.jsp").forward(request, response);
                
            } else if (acao.equals("inserir")) {
                String nome = request.getParameter("nome");
                String quantidadeStr = request.getParameter("quantidade");
                String precoStr = request.getParameter("preco");

                if (nome != null && !nome.isEmpty()) {
                    Produto p = new Produto();
                    p.setNome(nome);
                    if (quantidadeStr != null && !quantidadeStr.isEmpty()) {
                        p.setQuantidade(Integer.valueOf(quantidadeStr));
                    }
                    if (precoStr != null && !precoStr.isEmpty()) {
                        p.setPrecoVenda(Float.valueOf(precoStr));
                    }
                    produtoFacade.create(p);
                }
                response.sendRedirect("ServletProduto?acao=listar");
                
            } else if (acao.equals("editar")) {
                String idStr = request.getParameter("id");
                if (idStr != null) {
                    Integer id = Integer.valueOf(idStr);
                    Produto p = produtoFacade.find(id);
                    request.setAttribute("produto", p);
                    request.getRequestDispatcher("ProdutoDados.jsp").forward(request, response);
                }
                
            } else if (acao.equals("atualizar")) {
                String idStr = request.getParameter("id");
                String nome = request.getParameter("nome");
                String quantidadeStr = request.getParameter("quantidade");
                String precoStr = request.getParameter("preco");

                if (idStr != null && !idStr.isEmpty()) {
                    Integer id = Integer.valueOf(idStr);
                    Produto p = produtoFacade.find(id);
                    if (p != null) {
                        p.setNome(nome);
                        if (quantidadeStr != null && !quantidadeStr.isEmpty()) {
                            p.setQuantidade(Integer.valueOf(quantidadeStr));
                        }
                        if (precoStr != null && !precoStr.isEmpty()) {
                            p.setPrecoVenda(Float.valueOf(precoStr));
                        }
                        produtoFacade.edit(p);
                    }
                }
                response.sendRedirect("ServletProduto?acao=listar");
                
            } else if (acao.equals("remover")) {
                String idStr = request.getParameter("id");
                if (idStr != null) {
                    Integer id = Integer.valueOf(idStr);
                    Produto p = produtoFacade.find(id);
                    if (p != null) {
                        produtoFacade.remove(p);
                    }
                }
                response.sendRedirect("ServletProduto?acao=listar");
            }
        } catch (Exception e) {
            request.setAttribute("erro", "Ocorreu um erro na operação: " + e.getMessage());
            request.getRequestDispatcher("ProdutoLista.jsp").forward(request, response);
        }
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

    @Override
    public String getServletInfo() {
        return "Servlet Controller para Gestão de Produtos";
    }
}