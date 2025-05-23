/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.bean.Gerente;
import model.dao.GerenteDAO;
import model.dao.impl.GerenteDAOImpl;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jonat
 */
public class GerenteService {
    private final GerenteDAO gerenteDAO;

    public GerenteService(GerenteDAO gerenteDAO) {
        this.gerenteDAO = gerenteDAO;
    }
    
    public Gerente createGerente(Gerente gerente){
        if (gerente == null) {
            throw new IllegalArgumentException("O gerente não pode ser nulo.");
        }
        
        if (gerente.getNome() == null || gerente.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do gerente é necessário para sua criação.");
        }
        
        if (gerente.getCPF() == null || gerente.getCPF().length() > 14 || gerente.getCPF().length() < 11 || gerente.getCPF().trim().isEmpty()) {
            throw new IllegalArgumentException("O CPF do gerente é necessário para sua criação.");
        }
        
        if (gerente.getTelefone() == null || gerente.getTelefone().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do gerente é necessário para sua criação.");
        }
        
        if (gerente.getCargo() == null || gerente.getCargo().trim().isEmpty()) {
            throw new IllegalArgumentException("O cargo do gerente é necessário para sua criação.");
        }

        if (gerente.getLogin() == null || gerente.getLogin().trim().isEmpty()) {
            throw new IllegalArgumentException("O login do gerente é necessário para sua criação.");
        }

        if (gerente.getSenha() == null || gerente.getSenha().trim().isEmpty()) {
            throw new IllegalArgumentException("A senha do gerente é necessária para sua criação.");
        }
        
        try {
            return gerenteDAO.create(gerente);
        } catch (RuntimeException ex) {
            throw new IllegalArgumentException("Erro ao criar o gerente." + ex.getMessage());
        }
    }
    
    public List<Gerente> readGerente() {
        return gerenteDAO.read();
    }
    
    public boolean updateGerente(Gerente gerente){
        if (gerente == null) {
            throw new IllegalArgumentException("O gerente não pode ser nulo.");
        }

        if (gerente.getNome() == null || gerente.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do gerente é necessário para sua atualização.");
        }

        if (gerente.getCPF() == null || gerente.getCPF().length() > 14 || gerente.getCPF().length() < 11 || gerente.getCPF().trim().isEmpty()) {
            throw new IllegalArgumentException("O CPF do gerente é necessário para sua atualização.");
        }

        if (gerente.getTelefone() == null || gerente.getTelefone().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do gerente é necessário para sua atualização.");
        }

        if (gerente.getCargo() == null || gerente.getCargo().trim().isEmpty()) {
            throw new IllegalArgumentException("O cargo do gerente é necessário para sua atualização.");
        }

        if (gerente.getLogin() == null || gerente.getLogin().trim().isEmpty()) {
            throw new IllegalArgumentException("O login do gerente é necessário para sua atualização.");
        }

        if (gerente.getSenha() == null || gerente.getSenha().trim().isEmpty()) {
            throw new IllegalArgumentException("A senha do gerente é necessária para sua atualização.");
        }

        try {
            return gerenteDAO.update(gerente);
        } catch (RuntimeException ex) {
            throw new IllegalArgumentException("Erro ao atualizar o gerente." + ex.getMessage());
        }
    }
    
    public boolean deleteGerente(Long id){
        if(id == null || id <= 0) {
            throw new RuntimeException("Não há gerentes com ID negativo ou nulo.");
        } try {
            return gerenteDAO.delete(id);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Erro ao excluir o gerente!" + ex.getMessage());
        }
    }

    public Gerente findById(Long id){
        if(id == null || id <= 0) {
            throw new RuntimeException("Não há gerentes com ID negativo ou nulo.");
        } try {
            return gerenteDAO.findById(id);
        } catch (RuntimeException ex){
            throw new RuntimeException("Erro ao encontrar o gerente pelo ID!" + ex.getMessage());
        }
    }
}
