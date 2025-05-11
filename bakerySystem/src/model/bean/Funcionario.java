/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

/**
 *
 * @author joseo
 */
public class Funcionario extends Pessoa {
    
    protected String cargo;
    protected String login;
    protected String senha;
    
    public Funcionario(String nome, String CPF, String telefone, String cargo, String login, String senha){
        super(nome,CPF,telefone);
        this.setCargo(cargo);
        this.setLogin(login);
        this.setSenha(senha);
    }

    public String getCargo() {
        return this.cargo;
    }

    public boolean setCargo(String cargo) {
        if(!cargo.isBlank()){
            this.cargo = cargo;
            return true;
        }
        return false;
    }

    public String getLogin() {
        return this.login;
    }

    public boolean setLogin(String login) {
        if(!login.isBlank()){
            this.login = login;
            return true;
        } else
            return false;
    }

    public String getSenha() {
        return this.senha;
    }

    public boolean setSenha(String senha) {
        if(!senha.isBlank()){
            this.senha = senha;
            return true;
        } else
            return false;
    }
    
    @Override
    public String toString(){
        return "Cargo: "+ this.cargo + " Login: " + this.login + " Senha: " + this.senha;
    }
}
