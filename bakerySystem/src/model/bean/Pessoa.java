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
    this.nome = nome;
    this.CPF = CPF;
    this.telefone = telefone;
    }
    
    public Pessoa(){
    }
    
    public String toString(){
        return "Nome: " + this.nome + " CPF: " + this.CPF + " Telefone: " + this.telefone;
    }
}
