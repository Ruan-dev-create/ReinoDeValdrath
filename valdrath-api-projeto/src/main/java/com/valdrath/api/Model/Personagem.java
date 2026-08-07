package com.valdrath.api.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "personagem")
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @Enumerated(EnumType.STRING)
    private Classe classe;
    private double vida;
    private double dano;
    private int level;

    private boolean viuAberturaCutsene;

    public Personagem() {

    }

    Personagem(int id, Player player, Classe classe, double vida, double dano, int level) {
        this.id = id;
        this.player = player;
        this.classe = classe;
        this.vida = vida;
        this.dano = dano;
        this.level = level;
    }

    public void mostrarInfo(){
        System.out.println("Nome: " + player.getNome());
        System.out.println("Classe: " + classe);
        System.out.println("Vida: " + vida);
        System.out.println("Dano: " + dano);
        System.out.println("Level: " + level);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
        this.vida = classe.getVida();
        this.dano = classe.getDano();
    }

    public double getVida() {
        return vida;
    }

    public void setVida(double vida) {
        this.vida = vida;
    }

    public double getDano() {
        return dano;
    }

    public void setDano(double dano) {
        this.dano = dano;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isViuAberturaCutsene() {
        return viuAberturaCutsene;
    }

    public void setViuAberturaCutsene(boolean viuAberturaCutsene) {
        this.viuAberturaCutsene = viuAberturaCutsene;
    }
}
