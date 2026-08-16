package com.valdrath.api.Service;

import com.valdrath.api.Cutscene.CutsceneView;
import com.valdrath.api.Model.Personagem;
import com.valdrath.api.Model.Player;
import com.valdrath.api.Repository.PersonagemRepository;
import com.valdrath.api.Repository.PlayerRepository;
import com.valdrath.api.Exception.ValdrathException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

import static com.valdrath.api.Principal.Principal.delay;
import static com.valdrath.api.Principal.Principal.pulaLinhas;

@Service
public class CadastroPlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PersonagemRepository personagemRepository;

    @Autowired
    private CadastroPersonagemService cadastroPersonagem;

    private Scanner l = new Scanner(System.in);

    @Autowired
    private CutsceneView cutscineInicial;

    public Personagem verificaCadastroLogin() {
        System.out.print("""
                \n
                =====================================================================
                - Você já esteve aqui antes?
                [ sim ] Login
                [ não ] Cadastro
                >>>>>""");

        String opcao = l.nextLine().trim().toLowerCase();

        while (!opcao.contains("sim") && !opcao.contains("nao") && !opcao.contains("não")) {
            System.out.print("Digite a opção corretamente: ");
            opcao = l.nextLine().trim().toLowerCase();
        }

        try {

            Player playerLogado;

            if (opcao.equals("sim")) {
                playerLogado = LogarPlayer();
            } else {
                playerLogado = CadastroPlayer();
            }

            if (playerLogado != null) {

                verificarCutscene(playerLogado);

                return personagemRepository.findByPlayer(playerLogado);
            }

            return null;

        } catch (ValdrathException e) {
            System.out.println("Erro no login/cadastro: " + e.getMessage());
            return null;
        }

    }


    public void verificarCutscene(Player player) {
        Personagem personagem = personagemRepository.findByPlayer(player);

        if (personagem != null && !personagem.isViuAberturaCutsene()) {
            cutscineInicial.iniciarCutsceneAbertura(l);

            personagem.setViuAberturaCutsene(true);
            personagemRepository.save(personagem);
        }
    }

    public Player CadastroPlayer() {

        pulaLinhas(50);
        try {
            Player playerLogado = new Player();

            System.out.println("<< =============== Cadastro Player =============== >>");

            System.out.print("Digite o nome do jogador: ");
            String nome = l.nextLine().trim();
            playerLogado.setNome(nome);

            int idade = lerIdadeValida();
            playerLogado.setIdade(idade);

            System.out.print("Digite o email da sua conta: ");
            String email = l.nextLine().trim();


            email = playerLogado.verificarEmail(email, l);
            playerLogado.setEmail(email);

            System.out.print("Digite a senha: ");
            String senha = l.nextLine().trim();
            playerLogado.setSenha_conta(senha);

            System.out.printf("""
                    || << Dados do Jogador >> ||
                    |Nome: %s
                    |Idade: %d
                    |Email: %s
                    |Senha: *
                    """, playerLogado.getNome(), playerLogado.getIdade(), playerLogado.getEmail());

            delay(4000);
            playerRepository.save(playerLogado);

            cadastroPersonagem.cadastroPersonagem(playerLogado);

            return playerLogado;

        } catch (ValdrathException e) {
            System.out.println("Erro no cadastro do jogador: " + e.getMessage());
            return null;
        }
    }

    private int lerIdadeValida() {
        while (true) {

            System.out.print("Digite sua idade: ");

            try {

                int idade = Integer.parseInt(l.nextLine().trim());

                if (idade <= 0) {
                    System.out.println("Idade deve ser maior que zero.");
                    continue;
                }

                return idade;
            } catch (NumberFormatException e) {
                System.out.println("Isso não é um número válido, tente novamente.");

            }
        }
    }

    public Player LogarPlayer() {

        pulaLinhas(37);
        try {
            System.out.println("<< =============== Logando Player =============== >>");

            System.out.print("Digite o email da sua conta: ");
            String email = l.nextLine().trim();

            System.out.print("Digite a senha: ");
            String senha = l.nextLine().trim();

            Optional<Player> player = playerRepository.findByEmail(email);

            if (player.isEmpty()) {
                System.out.println("Email/Senha incorretos. ");
                return null;
            }

            if (!player.get().getSenha_conta().equals(senha)) {
                System.out.println("Senha incorreta.");
                return null;
            }

            System.out.println("Login realizado com sucesso!");

            System.out.printf("""
                    || << Dados do Jogador >> ||
                    |Nome: %s
                    |Idade: %d
                    |Email: %s
                    |Senha: *
                    
                    """, player.get().getNome(), player.get().getIdade(), player.get().getEmail());

            return player.get();



        } catch (ValdrathException e) {
            System.out.println("Erro ao fazer login: " + e.getMessage());
            return null;
        }
    }
}