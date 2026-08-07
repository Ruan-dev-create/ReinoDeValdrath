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

    public void verificaCadastroLogin(){
        System.out.print("""
                \n
                =====================================================================
                - Você já esteve aqui antes?
                [ sim ] Login
                [ não ] Cadastro
                >>>>>""");

        String opcao = l.nextLine().trim().toLowerCase();

        while(!opcao.equals("sim") && !opcao.equals("nao") && !opcao.equals("não")){
            System.out.print("Digite a opção corretamente: ");
            opcao = l.nextLine().trim().toLowerCase();
        }

        try{

            Player playerLogado;

            if(opcao.equals("sim")){
                playerLogado = LogarPlayer();
            }else{
                playerLogado = CadastroPlayer();
            }

            if(playerLogado != null){
                verificarCutscene(playerLogado);
            }

        }catch(ValdrathException e){
            e.getMessage();
        }

    }

    public void verificarCutscene(Player player){
        Personagem personagem = personagemRepository.findByPlayer(player);

        if(personagem != null && !personagem.isViuAberturaCutsene()){
            cutscineInicial.iniciarCutsceneAbertura(l);

            personagem.setViuAberturaCutsene(true);
            personagemRepository.save(personagem);
        }
    }

    public Player CadastroPlayer(){

        pulaLinhas(37);
        try {
            Player playerLogado = new Player();

            System.out.println("<< =============== Cadastro Player =============== >>");

            System.out.print("Digite o nome do jogador: ");
            String nome = l.nextLine().trim();
            playerLogado.setNome(nome);

            System.out.print("Digite sua idade: ");
            int idade = Integer.parseInt(l.nextLine().trim());
            playerLogado.setIdade(idade);

            System.out.print("Digite o email da sua conta: ");
            String email = l.nextLine().trim();

            email = playerLogado.verificarEmail(email);
            playerLogado.setEmail(email);

            System.out.print("Digite a senha: ");
            String senha = l.nextLine().trim();
            playerLogado.setSenha_conta(senha);

            playerRepository.save(playerLogado);

            cadastroPersonagem.cadastroPersonagem(playerLogado);

            return playerLogado;

        }catch (ValdrathException e) {
            e.getMessage();
            return null;
        }
    }

    public Player LogarPlayer(){

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
            return player.get();

        }catch (ValdrathException e){
            e.getMessage();
            return null;
        }
    }
}
