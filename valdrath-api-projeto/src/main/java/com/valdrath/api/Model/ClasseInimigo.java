package com.valdrath.api.Model;

public enum ClasseInimigo {
    Rato("rato", 1, "comum", 50, 5),
    Lobo_Sombrio("lobo sombrio", 5, "comum", 15, 4);

    private final String name;
    private final int level;
    private final String rank;
    private final int vida;
    private final int dano;

    ClasseInimigo(String name, int level, String rank, int vida, int dano) {
        this.name = name;
        this.level = level;
        this.rank = rank;
        this.vida = vida;
        this.dano = dano;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public String getRank() {
        return rank;
    }

    public int getVida() {
        return vida;
    }

    public int getDano() {
        return dano;
    }

    public static ClasseInimigo porLevel(int level) {
        return switch (level) {
            case 1 -> Rato;
            case 5 -> Lobo_Sombrio;
            default -> throw new IllegalArgumentException("Nenhum inimigo cadastrado para o level " + level);
        };
    }
}