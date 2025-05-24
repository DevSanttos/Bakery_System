/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import model.dao.*;
import model.dao.impl.*;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.bean.*;
import service.*;

public class Main {

    public static void main(String[] args) {
        Connection conexao = null;

        //testando a implementação da classe VendaService
        VendaDAO vendaDAO = new VendaDAOImpl();
        VendaService vendaService =  new VendaService(vendaDAO);

        ProdutoDAO produtoDao = new ProdutoDAOImpl();
        ProdutoService produtoService =  new ProdutoService(produtoDao);

        Produto produto = new Produto("Bolo", 5.0, "Quitanda");
        Produto produto1 = new Produto("Quindim", 15.0, "Quitanda");

//        produtoService.createProduto(produto);
//        produtoService.createProduto(produto1);
//
////        Cliente cliente = new Cliente("Jonathan", "11111111111", "888888888", 10);
//        Venda venda = new Venda(LocalDate.now(),null);
//
//
//        List<ItemVenda> itensVenda = new ArrayList<>();
//
//        ItemVenda itemVenda1 = new ItemVenda();
//        itemVenda1.setProduto(produto);
//        ItemVenda itemVenda2 = new ItemVenda();
//        itemVenda2.setProduto(produto1);
//
//        itensVenda.add(itemVenda1);
//        itensVenda.add(itemVenda2);
//
//        venda.setItens(itensVenda);
//
//        vendaService.createVenda(venda);
//        System.out.println(vendaService.loadItensForVenda(vendaService.findById(1L)));
//        Venda venda1 = vendaService.findById(1L);
//        Produto produto2 = produtoService.findById(8L);
//        produto2.setNome("TV LG 52'");
//        produto2.setTipo("Televizaum");
//        produtoService.updateProduto(produto2);
//        System.out.println(vendaService.updateVenda(venda1));
//        System.out.println(vendaService.readVenda());

        System.out.println(vendaService.deleteVenda(1L));
    }
}

