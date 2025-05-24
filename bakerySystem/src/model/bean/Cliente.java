/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joseo
 */
public class Cliente extends Pessoa {

    private int totalPontosAcumulados;
    
    public Cliente(String nome, String CPF, String telefone){
        super(nome,CPF,telefone);
        this.setTotalPontosAcumulados(0);
    }

    public Cliente(){

    }

    public int getTotalPontosAcumulados() {
        return this.totalPontosAcumulados;
    }

    public void setTotalPontosAcumulados(int totalPontosAcumulados) {
        this.totalPontosAcumulados = totalPontosAcumulados;
    }

    @Override
    public String toString(){
        return super.toString() + " Total de pontos acumulados: "+ this.getTotalPontosAcumulados();
    }
}
