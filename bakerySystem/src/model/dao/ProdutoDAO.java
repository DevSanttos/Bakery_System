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
    
    private static final String SQL_INSERT_PRODUTO = "INSERT INTO produto (nome, preco, tipo) VALUES (?, ?, ?)";
    private static final String SQL_SELECT_ALL_PRODUTOS = "SELECT id_produto, nome, preco, tipo FROM produto";
    private static final String SQL_UPDATE_PRODUTO = "UPDATE produto SET nome = ?, preco = ?, tipo = ? WHERE produto.id_produto = ?";
    private static final String SQL_DELETE_PRODUTO = "DELETE FROM produto WHERE produto.id_Produto = ?";
    private static final String SQL_SELECT_PRODUTO_BY_ID = "SELECT id, nome, preco, quantidade FROM produtos WHERE produto.id_produto = ?";
    
    public void create(Produto produto) {
        PreparedStatement stmt = null;
        Connection connection = ConnectionFactory.getConnection();
        
        try {    
            stmt = connection.prepareStatement(SQL_INSERT_PRODUTO);
            
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setString(3, produto.getTipo());
            
            stmt.executeUpdate();
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    produto.setIdProduto(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Falha ao obter o ID do produto, nenhum ID obtido.");
                }
            } catch(SQLException ex){
                System.out.print(ex.getMessage());
            }
            
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
            stmt = connection.prepareStatement(SQL_SELECT_ALL_PRODUTOS);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Produto produto = new Produto();
                
                produto.setIdProduto(rs.getLong("id_produto"));
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
        
        if (produto.getIdProduto() <= 0 || produto.getIdProduto() == null) {
            System.err.println("O produto para atualização precisa ter um ID válido!");
            return;
        }
        
        try {    
            stmt = connection.prepareStatement(SQL_UPDATE_PRODUTO);
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
    
    public void delete(int id) {
        PreparedStatement stmt = null;
        Connection connection = ConnectionFactory.getConnection();
        
        try {    
            stmt = connection.prepareStatement(SQL_DELETE_PRODUTO);
            stmt.setLong(1, id);
                
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir o produto!" + ex);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        } 
    }
}
