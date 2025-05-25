package controller;

import model.bean.Produto;
import model.dao.ProdutoDAO;
import service.ProdutoService;

import java.util.List;

public class ProdutoController {
    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    public List<Produto> readProduto(){
        return produtoService.readProduto();
    }

    public Produto findById(Long id) {
        return produtoService.findById(id);
    }


}
