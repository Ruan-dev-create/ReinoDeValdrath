package com.valdrath.api.Service;

import com.valdrath.api.Combate.Combate;
import com.valdrath.api.Model.ClasseInimigo;
import com.valdrath.api.Model.Personagem;
import org.springframework.stereotype.Service;

import java.util.Scanner;

import static com.valdrath.api.Principal.Principal.delay;

@Service
public class CombateService implements Combate {

    private Scanner l = new Scanner(System.in);

    @Override
    public void batalha(Personagem personagem, ClasseInimigo inimigo) {

        double vidaMonstro = inimigo.getVida();
        int pocoes = 3;

        System.out.println("""
                =============================
                  ⚔️ BATALHA INICIADA ⚔️
                =============================
                """);

        System.out.println("Um " + inimigo.getName() + " apareceu!");

        delay(1500);

        while (personagem.getVida() > 0 && vidaMonstro > 0) {
            System.out.println("""
                    
                    =============================
                    Sua vida: %s ❤️
                    Vida inimigo: %s 👹
                    Poções: %s 🧪
                    
                    [1] Atacar
                    [2] Defender
                    [3] Usar poção
                    [4] Fugir
                    
                    Escolha:
                    =============================
                    """.formatted(
                    personagem.getVida(),
                    Math.max(0, vidaMonstro),
                    pocoes
            ));
            String escolha = l.nextLine();
            boolean defendeu = false;
            switch (escolha) {
                case "1":

                    double dano = personagem.getDano();

                    // 20% chance crítico
                    if(Math.random() <= 0.20){
                        dano *= 2;
                        System.out.println("💥 CRÍTICO!");
                    }

                    vidaMonstro -= dano;

                    System.out.println(
                            personagem.getPlayer().getNome()
                                    + " causou "
                                    + dano
                                    + " de dano!"
                    );
                    break;

                case "2":
                    defendeu = true;
                    System.out.println("🛡 Você se preparou para o ataque!");
                    break;
                case "3":
                    if(pocoes > 0){
                        pocoes--;
                        personagem.setVida(
                                personagem.getVida() + 30
                        );
                        System.out.println("🧪 Você recuperou 30 de vida!");
                    }else{
                        System.out.println("Você não tem mais poções!");
                    }
                    break;
                case "4":
                    if(Math.random() <= 0.50){
                        System.out.println("🏃 Você conseguiu fugir!");
                        return;
                    }else{
                        System.out.println("❌ Você falhou ao fugir!");
                    }
                    break;
                default:
                    System.out.println("Opção inválida!");
                    continue;
            }

            if(vidaMonstro <= 0){
                break;
            }

            // ATAQUE DO INIMIGO

            double danoInimigo = inimigo.getDano();
            if(defendeu){

                danoInimigo /= 2;
                System.out.println("🛡 Defesa reduziu o dano!");

            }

            personagem.setVida(
                    personagem.getVida() - danoInimigo
            );

            System.out.println(
                    "👹 " + inimigo.getName() + " causou "
                            + danoInimigo
                            + " de dano!"
            );
            delay(800);
        }
        if(personagem.getVida() <= 0){

            System.out.println("☠️ Você morreu!");

        }else{

            System.out.println(
                    "🏆 Você derrotou "+ inimigo.getName()
            );
        }
    }
}