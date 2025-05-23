/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.bean.*;
import model.dao.VendaDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;



public class VendaService {

    private final VendaDAO vendaDAO;

    public VendaService(VendaDAO vendaDAO) {
        this.vendaDAO = vendaDAO;
    }

    public Venda createVenda(Venda venda) {
        if (venda == null) {
            throw new IllegalArgumentException("O caixa não pode ser nulo.");
        }

        if (venda.getItens() == null || venda.getItens().isEmpty()) {
            throw new IllegalArgumentException("É precisa ter um item.");
        }

        if (venda.getDataVenda() == null) {
            throw new IllegalArgumentException("É preciso ter uma data válida");
        }

        if (venda.getDataVendaSQL() == null) {
            throw new IllegalArgumentException("É preciso ter uma data válida");
        }

        try {
            return vendaDAO.create(venda);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Erro ao realizar a venda");
        }
    }

    public List<ItemVenda> loadItensForVenda(Venda venda) {
        if (venda == null) {
            throw new RuntimeException("A venda não pode ser nula.");
        }

        try {
            return vendaDAO.loadItensForVenda(venda);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao retornar os itens da venda.",ex);
        }
    }

    public List<Venda> readVenda(){
        try{
            return vendaDAO.read();
        } catch (RuntimeException ex){
            throw new RuntimeException("Erro ao retornar as vendas.",ex);
        }
    }

    public Venda findById(Long id){
        if(id == null || id <= 0){
            throw new RuntimeException("Não é possível encontrar vendas com ID nulo ou menor do que zero.");
        }
        
        try{
            return vendaDAO.findById(id);
        } catch (RuntimeException ex){
            throw new RuntimeException("Erro ao retornar os vendas.",ex);
        }
    }
    
    public boolean updateVenda(Venda venda) {
        if (venda == null) {
            throw new RuntimeException("Não é possível atualizar uma venda nula");
        }
        
        try{
            return vendaDAO.update(venda);
        } catch (RuntimeException ex){
            throw new RuntimeException("Erro ao atualizar a venda.",ex);
        }
    }

    public boolean deleteVenda(Long id){
        if(id == null || id <= 0){
            throw new RuntimeException("Não é possível deletar uma venda com ID nulo.");
        }

        try{
            return vendaDAO.delete(id);
        } catch (RuntimeException ex){
            throw new RuntimeException("Erro ao deletar a venda.",ex);
        }
    }

    public boolean realizaVenda(Pessoa pessoa, Venda venda, Caixa caixa) {
        return true;
    }
    
    public boolean atualizaEstoqueProdutos(){
        return true;
    }
    
    public int calculaPontosCompra(){
        return 1;
    }
    
    public boolean realizarTrocaPontos(){
        return true;
    }
}
