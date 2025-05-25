/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao.impl;

import connection.ConnectionFactory;
import model.bean.Caixa;
import model.bean.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import model.bean.Gerente;
import model.dao.CaixaDAO;

/**
 *
 * @author jonat
 */
public class CaixaDAOImpl implements CaixaDAO {

    private static final String SQL_INSERT_CAIXA = "INSERT INTO caixa (nome, CPF, telefone, cargo, login, senha) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_ALL_CAIXA = "SELECT * FROM caixa";
    private static final String SQL_UPDATE_CAIXA = "UPDATE caixa SET nome = ?, CPF = ?, telefone = ?, cargo = ?, login = ?, senha = ? WHERE caixa.id_caixa = ?";
    private static final String SQL_DELETE_CAIXA = "DELETE FROM caixa WHERE caixa.id_caixa = ?";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM caixa WHERE caixa.id_caixa = ?";
    private static final String SQL_FIND_BY_LOGIN_AND_PASSWORD = "SELECT id_caixa, login, senha FROM caixa WHERE caixa.login = ? AND caixa.senha = ?";

    @Override
    public Caixa create(Caixa caixa) {
        PreparedStatement stmt = null;
        Connection connection = ConnectionFactory.getConnection();
        ResultSet generatedKeys = null;

        try {
            stmt = connection.prepareStatement(SQL_INSERT_CAIXA, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setString(1, caixa.getNome());
            stmt.setString(2, caixa.getCPF());
            stmt.setString(3, caixa.getTelefone());
            stmt.setString(4, caixa.getCargo());
            stmt.setString(5, caixa.getLogin());
            stmt.setString(6, caixa.getSenha());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    caixa.setId(generatedKeys.getLong(1));
                    javax.swing.JOptionPane.showMessageDialog(null, "Caixa criado com sucesso! ID: " + caixa.getId());
                } else {
                    System.out.println("Falha ao obter o ID!");
                }
            } else {
                throw new SQLException("Falha ao obter o ID!");
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao cadastrar o caixa!", ex);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, generatedKeys);
        }
        return caixa;
    }

    @Override
    public List<Caixa> read() {
        List<Caixa> caixas = new ArrayList<>();

        PreparedStatement stmt = null;
        Connection connection = ConnectionFactory.getConnection();
        ResultSet rs = null;

        try {
            stmt = connection.prepareStatement(SQL_SELECT_ALL_CAIXA);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Caixa caixa = new Caixa();

                caixa.setId(rs.getLong("id_caixa"));
                caixa.setNome(rs.getString("nome"));
                caixa.setCPF(rs.getString("CPF"));
                caixa.setTelefone(rs.getString("telefone"));
                caixa.setCargo(rs.getString("cargo"));
                caixa.setLogin(rs.getString("login"));
                caixa.setSenha(rs.getString("senha"));

                caixas.add(caixa);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao obter os dados do caixa", ex);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
        }
        return caixas;
    }

    @Override   
    public boolean update(Caixa caixa) {
        PreparedStatement stmt = null;
        Connection connection = ConnectionFactory.getConnection();

        if (caixa == null || caixa.getId() == null || caixa.getId() <= 0L) {
            throw new IllegalArgumentException("Caixa é nulo ou não tem um ID válido!");
        }

        try {
            stmt = connection.prepareStatement(SQL_UPDATE_CAIXA);

            stmt.setString(1, caixa.getNome());
            stmt.setString(2, caixa.getCPF());
            stmt.setString(3, caixa.getTelefone());
            stmt.setString(4, caixa.getCargo());
            stmt.setString(5, caixa.getLogin());
            stmt.setString(6, caixa.getSenha());
            stmt.setLong(7, caixa.getId());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Caixa atualizado com sucesso!");
                return true;
            } else {
                throw new RuntimeException("Nenhum caixa foi encontrado ou os dados são os mesmos.");
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar o caixa!", ex);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    @Override
    public boolean delete(Long id) {
        PreparedStatement stmt = null;
        Connection connection = ConnectionFactory.getConnection();

        if (id <= 0) {
            throw new IllegalArgumentException("O id precisa ser maior que 0");
        }

        try {
            stmt = connection.prepareStatement(SQL_DELETE_CAIXA);
            stmt.setLong(1, id);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Caixa deletado com sucesso!");
                return true;
            } else {
                throw new IllegalArgumentException("Nenhum caixa com ID correspondente foi encontrado!");
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar o caixa!", ex);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }
    
    @Override
    public Caixa findById(Long id) {
        PreparedStatement stmt = null;
        Connection connection = ConnectionFactory.getConnection();
        ResultSet rs = null;
        Caixa caixa = new Caixa();

        try {
            stmt = connection.prepareStatement(SQL_FIND_BY_ID);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                caixa.setId(rs.getLong("id_caixa"));
                caixa.setNome(rs.getString("nome"));
                caixa.setCPF(rs.getString("CPF"));
                caixa.setTelefone(rs.getString("telefone"));
                caixa.setCargo(rs.getString("cargo"));
                caixa.setLogin(rs.getString("login"));
                caixa.setSenha(rs.getString("senha"));
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao obter o caixa!", ex);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
        }
        return caixa;
    }

    @Override
    public Caixa findByLoginAndPassword(String login, String senha) {
        PreparedStatement stmt = null;
        Connection connection = ConnectionFactory.getConnection();
        ResultSet rs = null;
        Caixa caixa = new Caixa();

        try {
            stmt = connection.prepareStatement(SQL_FIND_BY_LOGIN_AND_PASSWORD);
            stmt.setString(1, login);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();

            if (rs.next()) {
                caixa.setId(rs.getLong("id_caixa"));
                caixa.setLogin(rs.getString("login"));
                caixa.setSenha(rs.getString("senha"));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao tentar realizar a consulta do respectivo caixa!" + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
        }
        return caixa;
    }
}