/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;


import connection.ConnectionFactory;
import java.sql.Connection;

import model.bean.Caixa;
import model.bean.Cliente;
import model.bean.Produto;
import model.dao.CaixaDAO;
import model.dao.ClienteDAO;
import model.dao.ProdutoDAO;
/**
 *
 * @author GUILHERME
 */
public class Main {
    
    public static void main(String[] args){
        Connection conexao = null;
        System.out.println(conexao = (Connection) ConnectionFactory.getConnection());
        
        CaixaDAO dao = new CaixaDAO();
        ProdutoDAO daoProduto = new ProdutoDAO();
        
        Caixa caixa = new Caixa("Santos", "14375229632", "999999999", "caixa01", "joantahn", "123");
        
        Caixa novoCaixa = dao.findById(5L);
        System.out.println(dao.update(novoCaixa));
        System.out.println(novoCaixa.getNome());
        System.out.println(novoCaixa.getId());
        System.out.println(novoCaixa.getTelefone());

      
        
        
    }  
}
