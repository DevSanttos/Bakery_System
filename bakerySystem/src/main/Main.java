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
        
        Produto presunto = dao.findById(2L);
        
        presunto.setNome("Presunto");
        
        System.out.print(dao.update(presunto));
        
        
        

        
        
    }  
}
