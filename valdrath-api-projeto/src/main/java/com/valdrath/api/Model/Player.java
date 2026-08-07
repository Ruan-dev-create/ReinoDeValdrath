package com.valdrath.api.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.regex.Pattern;

@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String nome;

    private int idade;

    @Column(unique = true)
    private String email;

    private String senha_conta;

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    public Player(){

    }

    public Player(String nome, int idade, String email, String senha_conta) {
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.senha_conta = senha_conta;
    }

    public String verificarEmail(String email) {
        Scanner l = new Scanner(System.in);

        Pattern pattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

        while (!pattern.matcher(email).matches()) {
            System.out.println("Email inválido!");
            System.out.print("Digite seu email novamente: ");
            email = l.nextLine();
        }
        return email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha_conta() {
        return senha_conta;
    }

    public void setSenha_conta(String senha_conta) {
        this.senha_conta = senha_conta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
