/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.bean.Cliente;
import model.bean.Gerente;
import model.dao.ClienteDAO;
import model.dao.GerenteDAO;

import java.util.List;

/**
 *
 * @author jonat
 */
public class ClienteService {
    private final ClienteDAO clienteDAO;

    public ClienteService(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public Cliente createCliente(Cliente cliente){
        if (cliente == null) {
            throw new IllegalArgumentException("O cliente não pode ser nulo.");
        }

        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do cliente é necessário para sua criação.");
        }

        if (cliente.getCPF() == null || cliente.getCPF().length() > 14 || cliente.getCPF().length() < 11 || cliente.getCPF().trim().isEmpty()) {
            throw new IllegalArgumentException("O CPF do cliente é necessário para sua criação.");
        }

        if (cliente.getTelefone() == null || cliente.getTelefone().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do cliente é necessário para sua criação.");
        }

        try {
            return clienteDAO.create(cliente);
        } catch (RuntimeException ex) {
            throw new IllegalArgumentException("Erro ao criar o cliente." + ex.getMessage());
        }
    }

    public List<Cliente> readCliente() {
        return clienteDAO.read();
    }

    public boolean updateCliente(Cliente cliente){
        if (cliente == null) {
            throw new IllegalArgumentException("O cliente não pode ser nulo.");
        }

        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do cliente é necessário para sua atualização.");
        }

        if (cliente.getCPF() == null || cliente.getCPF().length() > 14 || cliente.getCPF().length() < 11 || cliente.getCPF().trim().isEmpty()) {
            throw new IllegalArgumentException("O CPF do cliente é necessário para sua atualização.");
        }

        if (cliente.getTelefone() == null || cliente.getTelefone().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do cliente é necessário para sua atualização.");
        }

        try {
            return clienteDAO.update(cliente);
        } catch (RuntimeException ex) {
            throw new IllegalArgumentException("Erro ao atualizar o cliente." + ex.getMessage());
        }
    }
    
    public boolean deleteCliente(Long id){
        if(id == null || id <= 0) {
            throw new RuntimeException("Não há clientes com ID negativo ou nulo.");
        } try {
            return clienteDAO.delete(id);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Erro ao excluir o cliente!" + ex.getMessage());
        }
    }

    public Cliente findById(Long id){
        if(id == null || id <= 0) {
            throw new RuntimeException("Não há clientes com ID negativo ou nulo.");
        } try {
            return clienteDAO.findById(id);
        } catch (RuntimeException ex){
            throw new RuntimeException("Erro ao encontrar o cliente pelo ID!" + ex.getMessage());
        }
    }
}
