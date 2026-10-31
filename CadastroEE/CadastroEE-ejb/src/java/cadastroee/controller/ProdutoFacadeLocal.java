package cadastroee.controller;

import cadastroee.model.Produto;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ProdutoFacadeLocal {
    void create(Produto produto);
    void edit(Produto produto);
    void remove(Produto produto);
    Produto find(Object id);
    List<Produto> findAll();
    int count();
}