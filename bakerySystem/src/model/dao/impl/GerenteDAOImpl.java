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

import model.bean.Caixa;
import model.bean.Gerente;
import model.bean.Produto;
import model.dao.GerenteDAO;

/**
 *
 * @author jonat
 */
public class GerenteDAOImpl implements GerenteDAO{

    private static final String SQL_INSERT_GERENTE = "INSERT INTO gerente (nome, CPF, telefone, cargo, login, senha) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_ALL_GERENTE = "SELECT id_gerente, nome, CPF, telefone, cargo, login, senha FROM gerente";
    private static final String SQL_UPDATE_GERENTE = "UPDATE gerente SET nome = ?, CPF = ?, telefone = ?, cargo = ?, login = ?, senha = ? WHERE gerente.id_gerente = ?";
    private static final String SQL_DELETE_GERENTE = "DELETE FROM gerente WHERE gerente.id_gerente = ?";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM gerente WHERE gerente.id_gerente = ?";

    @Override
    public Gerente create(Gerente gerente) {
        PreparedStatement stmt = null;
        Connection connection = ConnectionFactory.getConnection();
        ResultSet generatedKeys = null;

        try {
            stmt = connection.prepareStatement(SQL_INSERT_GERENTE, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setString(1, gerente.getNome());
            stmt.setString(2, gerente.getCPF());
            stmt.setString(3, gerente.getTelefone());
            stmt.setString(4, gerente.getCargo());
            stmt.setString(5, gerente.getLogin());
            stmt.setString(6, gerente.getSenha());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    gerente.setId(generatedKeys.getLong(1));
                    javax.swing.JOptionPane.showMessageDialog(null, "Gerente criado com sucesso! ID: " + gerente.getId());
                } else {
                    throw new RuntimeException("Falha ao obter o ID do gerente!");
                }
            } else {
                throw new RuntimeException("Falha ao criar o gerente! Nenhuma linha foi afetada!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar o gerente!" + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, generatedKeys);
        }
        return gerente;
    }

    @Override
    public List<Gerente> read() {
        List<Gerente> gerentes = new ArrayList<>();

        PreparedStatement stmt = null;
        Connection connection = ConnectionFactory.getConnection();
        ResultSet rs = null;

        try {
            stmt = connection.prepareStatement(SQL_SELECT_ALL_GERENTE);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Gerente gerente = new Gerente();

                gerente.setId(rs.getLong("id_gerente"));
                gerente.setNome(rs.getString("nome"));
                gerente.setCPF(rs.getString("CPF"));
                gerente.setTelefone(rs.getString("telefone"));
                gerente.setCargo(rs.getString("cargo"));
                gerente.setLogin(rs.getString("login"));
                gerente.setSenha(rs.getString("senha"));

                gerentes.add(gerente);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Falha ao consultar gerentes!" + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
        }
        return gerentes;
    }

    @Override
    public boolean update(Gerente gerente) {
        PreparedStatement stmt = null;
        Connection connection = ConnectionFactory.getConnection();

        if (gerente == null || gerente.getId() == null || gerente.getId() <= 0L) {
            throw new IllegalArgumentException("Gerente é nulo ou não tem um ID válido!");
        }

        try {
            stmt = connection.prepareStatement(SQL_UPDATE_GERENTE);

            stmt.setString(1, gerente.getNome());
            stmt.setString(2, gerente.getCPF());
            stmt.setString(3, gerente.getTelefone());
            stmt.setString(4, gerente.getCargo());
            stmt.setString(5, gerente.getLogin());
            stmt.setString(6, gerente.getSenha());
            stmt.setLong(7, gerente.getId());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                return true;
            } else {
                //JOptionPane.showMessageDialog(null, "Nenhum gerente foi encontrado ou os dados são os mesmos!");
                throw new RuntimeException("Nenhum gerente encontrado, ou os dados são os mesmos!");
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar o gerente!" + ex.getMessage());
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
            stmt = connection.prepareStatement(SQL_DELETE_GERENTE);
            stmt.setLong(1, id);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Gerente excluido com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum gerente com o ID correspondente foi encontrado!");
                return false;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Falha ao excluir o gerente!" + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    @Override
    public Gerente findById(Long id) {
        PreparedStatement stmt = null;
        Connection connection = ConnectionFactory.getConnection();
        ResultSet rs = null;
        Gerente gerente = new Gerente();

        try {
            stmt = connection.prepareStatement(SQL_FIND_BY_ID);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                gerente.setId(rs.getLong("id_gerente"));
                gerente.setNome(rs.getString("nome"));
                gerente.setCPF(rs.getString("CPF"));
                gerente.setTelefone(rs.getString("telefone"));
                gerente.setCargo(rs.getString("cargo"));
                gerente.setLogin(rs.getString("login"));
                gerente.setSenha(rs.getString("senha"));
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao tentar realizar a query dos gerentes!" + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
        }
        return gerente;
    }
}
