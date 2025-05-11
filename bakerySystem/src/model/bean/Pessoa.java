/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model.bean;

/**
 *
 * @author joseo
 */
public abstract class Pessoa {
    protected String nome;
    protected String CPF;
    protected String telefone;
    

    public Pessoa(String nome, String CPF,String telefone){
        this.setNome(nome);
        this.setCPF(CPF);
        this.setTelefone(telefone);
    }
    
    public Pessoa(){
    }

    // INÍCIO GETTERS AND SETTERS

    public String getCPF() {
        return this.CPF;
    }

    public boolean setCPF(String CPF) {
        if(CPF.length() != 11){
            return false;
        } else {
            this.CPF = CPF;
            return true;
        }
    }

    public String getNome() {
        return this.nome;
    }

    public boolean setNome(String nome) {
        if(!nome.isEmpty()){
            this.nome = nome;
            return true;
        } else
            return false;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public boolean setTelefone(String telefone) {
        if(!telefone.isEmpty()){
            this.telefone = telefone;
            return true;
        } else
            return false;
    }

    //FIM GETTERS AND SETTERS

    public String toString(){
        return "Nome: " + this.getNome() + " CPF: " + this.getCPF() + " Telefone: " + this.getTelefone();
    }
}
