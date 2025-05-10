/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

/**
 *
 * @author joseo
 */
public class Cliente extends Pessoa {
    
    private int totalPontosAcumulados;
    
    public Cliente(String nome, String CPF, String telefone, int totalPontosAcumulados){
        super(nome,CPF,telefone);
        this.totalPontosAcumulados = totalPontosAcumulados;
    }

    public int getTotalPontosAcumulados() {
        return totalPontosAcumulados;
    }

    /*public void setTotalPontosAcumulados(int totalPontosAcumulados) {
        this.totalPontosAcumulados = totalPontosAcumulados;
    }*/
    
    @Override
    public String toString(){
        return "Total de pontos acumulados: "+ totalPontosAcumulados;
    }
    
    
        
}
