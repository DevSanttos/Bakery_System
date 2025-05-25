/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import controller.CaixaController;
import model.dao.*;
import model.dao.impl.*;
import connection.ConnectionFactory;
import controller.GerenteController;
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

        GerenteDAO gerenteDAO = new GerenteDAOImpl();
        GerenteService gerenteService = new GerenteService(gerenteDAO);
        GerenteController gerenteController = new GerenteController(gerenteService);

        CaixaDAO caixaDAO = new CaixaDAOImpl();
        CaixaService caixaService =  new CaixaService(caixaDAO);
        CaixaController caixaController = new CaixaController(caixaService);

        ProdutoDAO produtoDao = new ProdutoDAOImpl();
        ProdutoService produtoService = new ProdutoService(produtoDao);
        
        System.out.println(produtoService.readProduto());

    }
}

