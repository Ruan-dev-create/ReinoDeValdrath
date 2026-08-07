package com.valdrath.api.Model;

public enum Classe {
    GUERREIRO("Tank resistente",
            100.0,
            10.0),

    MAGO("Mestre arcano",
            90.0,
            15.0),

    ARQUEIRO("Especialista em precisão",
            100.0,
            16.0),

    ASSASINO("Especialista em ataques furtivos",
            85.0,
            20),

    NECROMANTE("Manipulador das almas dos mortos",
            75.0,
            22.0);

    private String descricao;
    private double vida;
    private double dano;

    Classe(String descricao, double vida, double dano) {
        this.descricao = descricao;
        this.vida = vida;
        this.dano = dano;
    }


    public double getVida() {
        return vida;
    }
    public double getDano() {
        return dano;
    }
    public String getDescricao() {
        return descricao;
    }


    public static Classe escolherClasse(String opcao){
        switch (opcao){
            case "1":
                return GUERREIRO;

            case "2":
                return MAGO;

            case "3":
                return ARQUEIRO;

            case "4":
                return ASSASINO;

            case "5":
                return NECROMANTE;

            default:
                return null;

        }
    }

}
