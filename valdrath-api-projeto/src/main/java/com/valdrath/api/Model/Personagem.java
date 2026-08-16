package com.valdrath.api.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "personagem")
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @Enumerated(EnumType.STRING)
    private Classe classe;
    private double vida;
    private double dano;
    private int pocoes = 3;
    private double moedas;
    private int level = 1;

    private boolean viuAberturaCutsene;
    public Personagem() {

    }

    public void mostrarInfo(){
        System.out.println("Nome: " + player.getNome());
        System.out.println("Classe: " + classe);
        System.out.println("Vida: " + vida + "❤️");
        System.out.println("Dano: " + dano + "⚔️");
        System.out.println("Level: " + level);
        System.out.println("Dinheiro: " + moedas + "🪙");
        System.out.println("Poções: " + pocoes + "🧪");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public double getMoedas() {
        return moedas;
    }

    public void setMoedas(double moedas) {
        this.moedas = moedas;
    }

    public int getPocoes() {
        return pocoes;
    }

    public void setPocoes(int pocoes) {
        this.pocoes = pocoes;
    }
}
