/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao.impl;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Produto;
import model.dao.ProdutoDAO;

/**
 *
 * @author jonathan
 */
public class ProdutoDAOImpl implements ProdutoDAO {

    private static final String SQL_INSERT_PRODUTO = "INSERT INTO produto (nome, preco, tipo, quantidade) VALUES (?, ?, ?, ?)";
    private static final String SQL_SELECT_ALL_PRODUTOS = "SELECT id_produto, nome, preco, tipo, quantidade FROM produto";
    private static final String SQL_UPDATE_PRODUTO = "UPDATE produto SET nome = ?, preco = ?, tipo = ?, quantidade = ?, disponivel_para_troca = ?, pontos_necessarios = ?, status_resgate = ? WHERE produto.id_produto = ?";
    private static final String SQL_DELETE_PRODUTO = "DELETE FROM produto WHERE produto.id_produto = ?";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM produto WHERE produto.id_produto = ?";

    @Override
    public Produto create(Produto produto) {
        PreparedStatement stmt = null;
        Connection connection = ConnectionFactory.getConnection();
        ResultSet generatedKeys = null;

        try {
            stmt = connection.prepareStatement(SQL_INSERT_PRODUTO, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setString(3, produto.getTipo());
            stmt.setInt(4, produto.getQuantidade());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    produto.setIdProduto(generatedKeys.getLong(1));
                } else {
                    throw new RuntimeException("Falha ao obter o ID para a criação do produto");
                }
            } else {
                throw new RuntimeException("Falha ao criar o produto! Nenhuma linha foi afetada");
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao cadastrar o produto", ex);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, generatedKeys);
        }
        return produto;
    }

    @Override
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
            throw new RuntimeException("Erro ao realizar a query dos produtos!" + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
        }
        return produtos;
    }

    @Override
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
            stmt.setInt(4, produto.getQuantidade());
            stmt.setBoolean(5, produto.isDisponivelParaTroca());
            stmt.setInt(6, produto.getPontosNecessarios());
            stmt.setString(7, String.valueOf(produto.getStatusResgate()));
            stmt.setLong(8, produto.getIdProduto());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                return true;
            } else {
                throw new RuntimeException("Nenhum produto foi encontrado ou os dados são os mesmos!");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar o Produto!" + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        PreparedStatement stmt = null;
        Connection connection = ConnectionFactory.getConnection();

        if (id <= 0) {
            throw new IllegalArgumentException("O id precisa ser maior que 0");
        }

        try {
            stmt = connection.prepareStatement(SQL_DELETE_PRODUTO);
            stmt.setLong(1, id);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                return true;
            } else {
                throw new RuntimeException("Nenhum produto com o ID correspondente foi encontrado!");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao excluir produto: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
        return false;
    }

    @Override
    public Produto findById(Long idProduto) {

        PreparedStatement stmt = null;
        Connection connection = ConnectionFactory.getConnection();
        ResultSet rs = null;
        Produto produto = new Produto();

        try {
            stmt = connection.prepareStatement(SQL_FIND_BY_ID);
            stmt.setLong(1, idProduto);
            rs = stmt.executeQuery();

            if (rs.next()) {
                produto.setIdProduto(rs.getLong("id_produto"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setTipo(rs.getString("tipo"));
                produto.setQuantidade(rs.getInt("quantidade"));
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao tentar realizar a query dos produtos!" + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
        }
        return produto;
    }
}
