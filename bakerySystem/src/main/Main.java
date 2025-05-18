/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;


import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.bean.*;
import model.dao.*;

/**
 *
 * @author GUILHERME
 */
public class Main {
    
    public static void main(String[] args) throws Exception {
        Connection conexao = null;
        conexao = (Connection) ConnectionFactory.getConnection();

          Cliente cliente = new Cliente("Jonathan", "88888888888", "999999999", 0);
//        ClienteDAO clienteDAO = new ClienteDAO();
//
//        clienteDAO.create(cliente);
//        System.out.println("Lendo a tabela cliente: " + clienteDAO.read());

          Venda venda = new Venda();
        VendaDAO vendaDAO = new VendaDAO();

        System.out.println(vendaDAO.read());




//        try {
//            vendaDAO.create(venda);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            System.out.println("Lendo a tabela venda: " + vendaDAO.read(1L, conexao));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//
//        Produto produto = new Produto("Arroz", 20, "Branco");
//        ProdutoDAO produtoDAO = new ProdutoDAO();
//        produtoDAO.create(produto);
//        System.out.println("Lendo a tabela Produto: " + produtoDAO.read());
//
//        List<ItemVenda> itens = new ArrayList<ItemVenda>();
//
//        ItemVenda itv = new ItemVenda();
//        itv.setProduto(produto);
//        itv.setQuantidade(1);
//        itv.setPrecoUnitario(produto.getPreco());
//        itv.setVenda(venda);
//        itens.add(itv);
//        ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
//        itemVendaDAO.create(itv, conexao);






    }
}
