/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.bean.Produto;
import model.dao.ProdutoDAO;

import java.util.List;

/**
 *
 * @author jonat
 */

public class ProdutoService {
    private final ProdutoDAO produtoDAO;

    public ProdutoService(ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }
    
    public Produto createProduto(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("O produto não pode ser nulo.");
        }

        if(produto.getNome() == null || produto.getNome().trim().isEmpty()){
            throw new RuntimeException("O nome do produto não pode ser nulo.");
        }

        if(produto.getQuantidade() < 0){
            throw new RuntimeException("A quantidade do produto não pode ser negativa.");
        }

        if(produto.getPreco() < 0){
            throw new RuntimeException("O preço do produto não pode ser negativo.");
        }

        if(produto.getTipo() == null || produto.getTipo().trim().isEmpty()){
            throw new RuntimeException("O tipo do produto não pode ser nulo e nem vazio");
        }

        if(produto.getPontosNecessarios() < 0){
            throw new RuntimeException("Os pontos necessários para a troca de um produto não podem ser negativos.");
        }

        if(produto.getStatusResgate() == null){
            throw new RuntimeException("O status de resgate de um produto não pode ser nulo");
        }

        try {
            return produtoDAO.create(produto);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Erro ao cadastrar produto ", ex);
        }
    }

    public List<Produto> readProduto(){
        return produtoDAO.read();
    }

    public boolean updateProduto(Produto produto) {
        if (produto == null) {
            throw new RuntimeException("O produto não pode ser nulo");
        }

        try {
            return produtoDAO.update(produto);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Erro ao atualizar produto ", ex);
        }
    }

    public Produto findById(Long id) {
        if(id == null || id <= 0){
            throw new RuntimeException("Não há como encontrar produto com ID nulo.");
        }

        try{
            return produtoDAO.findById(id);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Erro ao encontrar produto ", ex);
        }
    }

    public boolean deleteProduto(Long id) {
        if (id == null || id <= 0) {
            throw new RuntimeException("O ID do produto não pode ser negativo ou nulo.");
        }

        try{
            return produtoDAO.delete(id);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Erro ao excluir produto ", ex);
        }
    }
    
}
