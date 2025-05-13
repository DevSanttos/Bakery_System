/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Produto;

/**
 *
 * @author jonathan
 */
public class ProdutoDAO {
    
    public void create(Produto produto) {
        PreparedStatement stmt = null;
        Connection connection = ConnectionFactory.getConnection();
        
        try {    
            stmt = connection.prepareStatement("INSERT INTO produto(nome,preco,tipo)VALUES(?,?,?)");
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setString(3, produto.getTipo());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar o Produto!" + ex);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        } 
    }
    
    public List<Produto> read(){
        List<Produto> produtos = new ArrayList<>();
        
        PreparedStatement stmt = null;
        Connection connection = ConnectionFactory.getConnection();
        ResultSet rs = null;
        
        try {
            stmt = connection.prepareStatement("SELECT * FROM produto");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Produto produto = new Produto();
                
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setTipo(rs.getString("tipo"));
                
                produtos.add(produto);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar realizar a query dos produtos!" + ex);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
        }
        return produtos;   
    }
    
    public void update(Produto produto) {
        PreparedStatement stmt = null;
        Connection connection = ConnectionFactory.getConnection();
        
        try {    
            stmt = connection.prepareStatement("UPDATE produto SET nome = ?, preco = ?, tipo = ? WHERE idProduto = ?");
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setString(3, produto.getTipo());
            stmt.setLong(4, produto.getIdProduto());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar o Produto!" + ex);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        } 
    }
    
    public void delete(Produto produto) {
        PreparedStatement stmt = null;
        Connection connection = ConnectionFactory.getConnection();
        
        try {    
            stmt = connection.prepareStatement("DELETE FROM produto WHERE idProduto = ?");
            stmt.setLong(1, produto.getIdProduto());
                
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir o produto!" + ex);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        } 
    }
}
