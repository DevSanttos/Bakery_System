/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;


import connection.ConnectionFactory;
import java.sql.Connection;
import model.bean.Produto;
import model.dao.ProdutoDAO;
/**
 *
 * @author GUILHERME
 */
public class Main {
    
    public static void main(String[] args){
        Connection conexao = null;
        System.out.println(conexao = (Connection) ConnectionFactory.getConnection());
        
        ProdutoDAO dao = new ProdutoDAO();
        
        Produto produto1 = new Produto("Bolo", 10, "Doces");
        Produto produto2 = new Produto("Pão de queijo Mineiro", 100, "Salgados");
        Produto produto3 = new Produto("Bulacha", 10, "Doces");
        Produto produto5 = new Produto("Sonho", 10, "Doce");
        
       
//        produto5 = dao.create(produto5);
//        System.out.println(dao.read());
//        
//        produto5.setNome("Sonho choc");
//        dao.update(produto5);
//        System.out.println(dao.read());
        
        produto5.setNome("Sonho de Valsa");
        dao.update(produto5);
        

        System.out.println(produto5.getIdProduto());
    }
}
