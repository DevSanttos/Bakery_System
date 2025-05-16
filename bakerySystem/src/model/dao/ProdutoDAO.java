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
    private static final String SQL_DELETE_PRODUTO = "DELETE FROM produto WHERE produto.id_produto = ?";

    public Produto create(Produto produto) {
        PreparedStatement stmt = null;
        Connection connection = ConnectionFactory.getConnection();
        ResultSet generatedKeys = null;

        try {
            stmt = connection.prepareStatement(SQL_INSERT_PRODUTO, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setString(3, produto.getTipo());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    produto.setIdProduto(generatedKeys.getLong(1));
                    JOptionPane.showMessageDialog(null, "Produto criado com sucesso! ID: " + produto.getIdProduto());
                } else {
                    JOptionPane.showMessageDialog(null, "Falha ao obter o ID!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao criar o produto! Nenhuma linha foi afetada!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar o Produto!" + ex);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, generatedKeys);
        }
        return produto;
    }

    public List<Produto> read() {
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

    public boolean update(Produto produto) {
        PreparedStatement stmt = null;
        Connection connection = ConnectionFactory.getConnection();

        if (produto == null || produto.getIdProduto() == null || produto.getIdProduto() <= 0L) {
            throw new IllegalArgumentException("Produto para atualização é nulo ou não tem um ID válido!");
        }

        try {
            stmt = connection.prepareStatement(SQL_UPDATE_PRODUTO);

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setString(3, produto.getTipo());
            stmt.setLong(4, produto.getIdProduto());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum produto foi encontrado ou os dados são os mesmos!");
                return false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar o Produto!" + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
        return false;
    }

    public boolean delete(Long id) {
        PreparedStatement stmt = null;
        Connection connection = ConnectionFactory.getConnection();

        if (id <= 0) {
            throw new IllegalArgumentException("O id precisa ser maior que 0");   
//            JOptionPane.showMessageDialog(null, "O ID precisa ser maior que 0!");
        }

        try {
            stmt = connection.prepareStatement(SQL_DELETE_PRODUTO);
            stmt.setLong(1, id);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Produto excluido com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum produto com o ID correspondente foi encontrado!");
                return false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir o produto!" + ex);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        } 
        return false;
    }
}
