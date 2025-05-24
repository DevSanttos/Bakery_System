/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.bean.*;
import model.dao.ClienteDAO;
import model.dao.ProdutoDAO;
import model.dao.VendaDAO;
import model.dao.impl.ClienteDAOImpl;
import model.dao.impl.ProdutoDAOImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Formattable;
import java.util.List;



public class VendaService {

    private final VendaDAO vendaDAO;
    private final ClienteDAO clienteDAO;
    private final ProdutoDAO produtoDAO;

    public VendaService(VendaDAO vendaDAO, ClienteDAO clienteDAO, ProdutoDAO produtoDAO) {
        this.vendaDAO = vendaDAO;
        this.clienteDAO = clienteDAO;
        this.produtoDAO = produtoDAO;
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

    public void addItensAoCarrinho(Long idInformado) {
        List<Produto> produtos = new ArrayList<>();
        if (idInformado <= 0) {
            throw new IllegalArgumentException("O ID precisa ser válido.");
        }

        ProdutoService produtoService = new ProdutoService(produtoDAO);
        Produto novoProduto = produtoService.findById(idInformado);
        produtos.add(novoProduto);
    }


    //Estamos com um problema para adicionar a lista de produtos a esse preset da venda(Basicamente o que precisa ser presetado antes de realizar a venda)
    public Venda setarVenda(Long idCliente, List<Produto> produtos) {
        //se id null, realizar a venda para a Pessoa que não é cliente
        if (idCliente == null || idCliente <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }

        Cliente cliente = clienteDAO.findById(idCliente);
        // retirar do estoque
        // add quant pontos à cliente

        Venda venda = new Venda(LocalDate.now(), cliente);

        List<ItemVenda> itemVendas = new ArrayList<>();

        for (int i = 0; i < produtos.size(); i++) {
            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setProduto(produtos.get(i));
            itemVendas.add(itemVenda);
        }
        venda.setItens(itemVendas);
        if (cliente == null) {
            createVenda(venda);
        }
        return venda;
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
