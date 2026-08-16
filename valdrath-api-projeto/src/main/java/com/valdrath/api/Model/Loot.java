package com.valdrath.api.Model;

public class Loot {
    private String nome;
    private double chanceDeDrop;

    public Loot(String nome, double chanceDeDrop){
        this.nome = nome;
        this.chanceDeDrop = chanceDeDrop;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getChanceDeDrop() {
        return chanceDeDrop;
    }

    public void setChanceDeDrop(double chanceDeDrop) {
        this.chanceDeDrop = chanceDeDrop;
    }

}
