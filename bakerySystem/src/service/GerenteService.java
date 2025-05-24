/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.bean.Caixa;
import model.bean.Gerente;
import model.bean.Produto;
import model.dao.CaixaDAO;
import model.dao.GerenteDAO;
import model.dao.impl.CaixaDAOImpl;
import model.dao.impl.GerenteDAOImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jonat
 */
public class GerenteService {
    private final GerenteDAO gerenteDAO;

    public GerenteService(GerenteDAO gerenteDAO) {
        this.gerenteDAO = gerenteDAO;
    }

    public Gerente createGerente(Gerente gerente) {
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

    public boolean updateGerente(Gerente gerente) {
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

    public boolean deleteGerente(Long id) {
        if (id == null || id <= 0) {
            throw new RuntimeException("Não há gerentes com ID negativo ou nulo.");
        }
        try {
            return gerenteDAO.delete(id);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Erro ao excluir o gerente!" + ex.getMessage());
        }
    }

    public Gerente findById(Long id) {
        if (id == null || id <= 0) {
            throw new RuntimeException("Não há gerentes com ID negativo ou nulo.");
        }
        try {
            return gerenteDAO.findById(id);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Erro ao encontrar o gerente pelo ID!" + ex.getMessage());
        }
    }

    public boolean findByLoginAndPassword(String login, String senha) {
        if (login == null || login.isEmpty()) {
            throw new IllegalArgumentException("Informe um login válido.");
        }

        if (senha == null || senha.isEmpty()) {
            throw new IllegalArgumentException("Informe uma senha válida.");
        }
        try {
            if (gerenteDAO.findByLoginAndPassword(login, senha) != null) {
                return true;
            }
        } catch (RuntimeException ex) {
            throw new RuntimeException("Erro ao buscar pelo ID do gerente: " + ex.getMessage());
        }
        return false;
    }

    public Caixa createCaixa (String nome, String CPF, String telefone, String cargo, String login, String senha) {
        String [] atr = new String [6];
        for(int i = 0; i <= atr.length; i++ ){
            if(atr[i] == null || atr[i].trim().isEmpty()){
                throw new RuntimeException("Atributo nulo ou vazio não possibilita a criação de um CaixaService.");
            }
        }
        try {
            CaixaDAO caixaDAO = new CaixaDAOImpl();
            CaixaService caixaService = new CaixaService(caixaDAO);
            Caixa caixa = new Caixa(nome, CPF, telefone, cargo, login, senha);
            caixaService.createCaixa(caixa);
            return caixa;
        } catch (RuntimeException ex){
            throw new RuntimeException("Erro ao criar caixa! " + ex.getMessage());
        }
    }

//    public boolean updateCaixa(Caixa caixa){
//
//    }

//    public Produto createProduto(String nome, double preco, String tipo, int quantidade) {
//        if (nome == null || nome.isEmpty()) {
//            throw new IllegalArgumentException("Informe um nome válido.");
//        }
//        if (preco <= 0) {
//            throw new IllegalArgumentException("Informe um preco válido.");
//        }
//        if (tipo == null || tipo.isEmpty()) {
//            throw new IllegalArgumentException("Informe uma senha válida.");
//        }
//        if (quantidade < 0) {
//            throw new IllegalArgumentException("Informe uma quantidade válida.");
//        }
//
////        try {
////
////            }
////        } catch (RuntimeException ex) {
////            throw new RuntimeException("Erro ao buscar pelo ID do gerente: " + ex.getMessage());
////        }
//    }
}
