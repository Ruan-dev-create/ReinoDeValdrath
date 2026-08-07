package com.valdrath.api.Combate;

public interface Combate {
    public void batalha(String monstro);
    int getHp();
    void setHp(int hp);
    int getAtaque();
    String getNome();

    default boolean estaVivo() {
        return getHp() > 0;
    }

    default void receberDano(int dano) {
        setHp(Math.max(0, getHp() - dano));
    }

}
