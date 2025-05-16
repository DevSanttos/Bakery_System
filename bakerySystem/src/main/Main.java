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
        Produto produto5 = new Produto("Bulacha", 10, "Doces");
        
        Produto produto2 = new Produto("Pão de queijo Mineiro", 100, "Salgados");
        
        dao.create(produto5);
        
        System.out.println(dao.read());
//       System.out.println(produto1.getNome());
//        
////        dao.create(produto2);
//        produto1.setNome("Bolo de pão");
//        System.out.println(produto1.getIdProduto());
//        dao.update(produto1);
//        System.out.println(dao.read());
        
        
        
    }
}
