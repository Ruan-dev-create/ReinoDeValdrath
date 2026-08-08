package com.valdrath.api.Model;

public enum ClasseInimigo {
    Rato("rato", 1, "comum", 50, 5),
    Lobo_Sombrio("lobo sombrio", 2, "comum", 15, 4);


    private String name;
    private int level;
    private String rank;
    private int vida;
    private int dano;

    ClasseInimigo(String name, int level, String rank,  int vida, int dano) {
        this.name = name;
        this.level = level;
        this.rank = rank;
        this.vida = vida;
        this.dano = dano;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }
}
