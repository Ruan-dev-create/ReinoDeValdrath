package com.valdrath.api.Service;

import com.valdrath.api.Exception.ValdrathException;
import com.valdrath.api.Model.Personagem;
import com.valdrath.api.Model.Player;
import com.valdrath.api.Repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

import static com.valdrath.api.Model.Classe.escolherClasse;
import static com.valdrath.api.Principal.Principal.delay;
import static com.valdrath.api.Principal.Principal.pulaLinhas;

@Service
public class CadastroPersonagemService {

    private Scanner l = new Scanner(System.in);

    @Autowired
    PersonagemRepository personagemRepository;

    private Personagem personagem;

    public void cadastroPersonagem(Player player) {

        try {
            personagem = new Personagem();
            personagem.setPlayer(player);

            pulaLinhas(37);
            System.out.println("<< ==================== Cadastro Personagem ==================== >>");

            delay(500);

            System.out.println("""
                    - O que deseja ser? 
                    
                    [ 1 ] Guerreiro
                    [ 2 ] Mago
                    [ 3 ] Arqueiro
                    [ 4 ] Assasino 
                    [ 5 ] Necromante
                    
                    ====================
                    >>>>""");

            String op = l.nextLine();
            personagem.setClasse(escolherClasse(op));
            personagem.mostrarInfo();

            personagemRepository.save(personagem);
        }catch (ValdrathException e) {
            e.getMessage();
        }
    }
}
