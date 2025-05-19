/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao.impl;

import connection.ConnectionFactory;
import model.bean.Cliente;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dao.ClienteDAO;

/**
 *
 * @author jonat
 */
public class ClienteDAOImpl implements ClienteDAO{

    private static final String SQL_INSERT_CLIENTE = "INSERT INTO cliente (nome, CPF, telefone, totPontosAcumulados) VALUES (?, ?, ?, ?)";
    private static final String SQL_SELECT_ALL_CLIENTES = "SELECT * FROM cliente";
    private static final String SQL_UPDATE_CLIENTE = "UPDATE cliente SET nome = ?, CPF = ?, telefone = ?, totPontosAcumulados = ? WHERE cliente.id_cliente = ?";
    private static final String SQL_DELETE_CLIENTE = "DELETE FROM cliente WHERE cliente.id_cliente = ?";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM cliente WHERE cliente.id_cliente = ?";
    private static final String SQL_FIND_BY_NAME = "SELECT * FROM cliente WHERE nome = ?";

    @Override
    public Cliente create(Cliente cliente) {
        PreparedStatement stmt = null;
        Connection connection = ConnectionFactory.getConnection();
        ResultSet generatedKeys = null;

        try {
            stmt = connection.prepareStatement(SQL_INSERT_CLIENTE, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCPF());
            stmt.setString(3, cliente.getTelefone());
            stmt.setInt(4, cliente.getTotalPontosAcumulados());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    cliente.setId(generatedKeys.getLong(1));
                    JOptionPane.showMessageDialog(null, "Cliente criado com sucesso! ID: " + cliente.getId());
                } else {
                    JOptionPane.showMessageDialog(null, "Falha ao obter o ID!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao criar o cliente! Nenhuma linha foi afetada!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar o cliente!" + ex);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, generatedKeys);
        }
        return cliente;
    }

    @Override
    public List<Cliente> read() {
        List<Cliente> clientes = new ArrayList<>();

        PreparedStatement stmt = null;
        Connection connection = ConnectionFactory.getConnection();
        ResultSet rs = null;

        try {
            stmt = connection.prepareStatement(SQL_SELECT_ALL_CLIENTES);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();

                cliente.setId(rs.getLong("id_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCPF(rs.getString("CPF"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setTotalPontosAcumulados(rs.getInt("totPontosAcumulados"));

                clientes.add(cliente);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar realizar a query dos clientes!" + ex);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
        }
        return clientes;
    }

    @Override
    public boolean update(Cliente cliente) {
        PreparedStatement stmt = null;
        Connection connection = ConnectionFactory.getConnection();

        if (cliente == null || cliente.getId() == null || cliente.getId() <= 0L) {
            throw new IllegalArgumentException("Cliente para atualização é nulo ou não tem um ID válido!");
        }

        try {
            stmt = connection.prepareStatement(SQL_UPDATE_CLIENTE);

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCPF());
            stmt.setString(3, cliente.getTelefone());
            stmt.setInt(4, cliente.getTotalPontosAcumulados());
            stmt.setLong(5, cliente.getId());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum cliente foi encontrado ou os dados são os mesmos!");
                return false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar o cliente!" + ex.getMessage());
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
//            JOptionPane.showMessageDialog(null, "O ID precisa ser maior que 0!");
        }

        try {
            stmt = connection.prepareStatement(SQL_DELETE_CLIENTE);
            stmt.setLong(1, id);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum cliente com o ID correspondente foi encontrado!");
                return false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir o cliente!" + ex);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
        return false;
    }

    @Override
    public Cliente findById(Long idCliente) {

        PreparedStatement stmt = null;
        Connection connection = ConnectionFactory.getConnection();
        ResultSet rs = null;
        Cliente cliente = new Cliente();

        try {
            stmt = connection.prepareStatement(SQL_FIND_BY_ID);
            stmt.setLong(1, idCliente);
            rs = stmt.executeQuery();

            while (rs.next()) {
                cliente.setId(rs.getLong("id_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCPF(rs.getString("CPF"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setTotalPontosAcumulados(rs.getInt("totPontosAcumulados"));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar realizar a query dos produtos!" + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
        }
        return cliente;
    }

    @Override
    public Cliente findByName(String nomeCliente) {
        PreparedStatement stmt = null;
        Connection connection = ConnectionFactory.getConnection();
        ResultSet rs = null;
        Cliente cliente = new Cliente();

        try {
            stmt = connection.prepareStatement(SQL_FIND_BY_NAME);
            stmt.setString(1, nomeCliente);
            rs = stmt.executeQuery();

            while (rs.next()) {
                cliente.setId(rs.getLong("id_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCPF(rs.getString("CPF"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setTotalPontosAcumulados(rs.getInt("totPontosAcumulados"));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar realizar a query do cliente!" + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
        }
        return cliente;
    }
}
