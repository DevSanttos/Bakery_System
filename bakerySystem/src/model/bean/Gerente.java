/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

/**
 *
 * @author joseo
 */
public class Gerente extends Funcionario {
    
    public Gerente(String nome, String CPF, String telefone, String cargo, String login, String senha){
        super(nome,CPF,telefone,cargo,login,senha);
    }
    
   
    @Override
    public String toString() {
        return super.toString();
    }
    
}
