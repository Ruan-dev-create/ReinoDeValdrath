package com.valdrath.api.Service;

import com.valdrath.api.Combate.Combate;
import com.valdrath.api.Model.ClasseInimigo;
import com.valdrath.api.Model.Personagem;
import com.valdrath.api.Repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

import static com.valdrath.api.Principal.Principal.delay;
import static com.valdrath.api.Principal.Principal.esperaENTER;

@Service
public class CombateService implements Combate {

    @Autowired
    private PersonagemRepository personagemRepository;

    private Scanner l = new Scanner(System.in);

    @Override
    public void batalha(Personagem personagem, ClasseInimigo inimigo) {

        double vidaMonstro = inimigo.getVida();
        int pocoes = 3;

        delay(2000);

        System.out.println("""
                =============================
                  ⚔️ BATALHA INICIADA ⚔️
                =============================
                """);

        delay(2000);
        esperaENTER(l);

        System.out.println("Um " + inimigo.getName() + " apareceu!");

        delay(1500);

        while (personagem.getVida() > 0 && vidaMonstro > 0) {
            try {
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
                        if (Math.random() <= 0.20) {
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
                        if (pocoes > 0) {
                            pocoes--;
                            personagem.setVida(
                                    personagem.getVida() + 30
                            );
                            System.out.println("🧪 Você recuperou 30 de vida!");
                        } else {
                            System.out.println("Você não tem mais poções!");
                        }
                        break;
                    case "4":
                        if (Math.random() <= 0.50) {
                            System.out.println("🏃 Você conseguiu fugir!");
                            return;
                        } else {
                            System.out.println("❌ Você falhou ao fugir!");
                        }
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        continue;
                }

                if (vidaMonstro <= 0) {
                    break;
                }

                // ATAQUE DO INIMIGO

                double danoInimigo = inimigo.getDano();
                if (defendeu) {

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
                }catch(NullPointerException e){
                System.out.println("");
            }
        }
                if (personagem.getVida() <= 0) {

                    System.out.println("☠️ Você morreu!");

                } else {

                    System.out.println(
                            "🏆 Você derrotou " + inimigo.getName()
                    );

                    GanhaLevel(personagem, inimigo);
            }
        }

        public void verificarLevelDeJogadorParaBatalha (Personagem personagem){

            if (personagem.getLevel() < 3) {
                ClasseInimigo inimigo = ClasseInimigo.porLevel(personagem.getLevel());
                batalha(personagem, inimigo);
            }
        }


        public void GanhaLevel (Personagem personagem, ClasseInimigo inimigo){
            if (inimigo.getLevel() < 8) {
                personagem.setLevel(personagem.getLevel() + 2);
                System.out.println("E ganhou 2 levels!!");

                personagemRepository.save(personagem);

            } else if (inimigo.getLevel() >= 8 || inimigo.getLevel() <= 20) {
                personagem.setLevel(personagem.getLevel() + 5);
                System.out.println("E ganhou 5 levels!!");

                personagemRepository.save(personagem);
            }
        }
    }