package com.valdrath.api.Service;

import com.valdrath.api.Combate.Combate;
import com.valdrath.api.Model.ClasseInimigo;
import com.valdrath.api.Model.Inventario;
import com.valdrath.api.Model.Personagem;
import com.valdrath.api.Repository.InventarioRepository;
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

    @Autowired
    private InventarioRepository inventarioRepository;

    private Scanner l = new Scanner(System.in);

    @Autowired
    private LootService serviceLoot;

    @Override
    public void batalha(Personagem personagem, ClasseInimigo inimigo) {

        double vidaMonstro = inimigo.getVida();
        int pocoes = 3;

        delay(2000);

        System.out.printf("\nUM %s APARECEU!!!\n", inimigo.getName());
        delay(1500);

        System.out.println("============================================");
        System.out.println("Você deseja lutar com ele? seus status é: ");

        System.out.println("============================================");
        delay(2000);
        personagem.mostrarInfo();
        System.out.println("============================================");

        delay(2000);

        System.out.println("============================================");
        System.out.println("Status do monstro: ");;
        System.out.println("============================================");

        delay(2000);
        inimigo.mostrarInfoMonstro();
        System.out.println("====================");

        System.out.print("""
                [ 1 ] sim
                [ 2 ] nao
                >>>>>""");
        int opcao =  l.nextInt();

        l.nextLine();

        if (opcao == 1) {
            System.out.println("""
                    =============================
                      ⚔️ BATALHA INICIADA ⚔️
                    =============================
                    """);

            delay(2000);
            esperaENTER(l);
            System.out.println("=============================");
            inimigo.mostrarInfoMonstro();
            System.out.println("=============================");

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
                            personagem.getPocoes()
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

                                if (personagem.getPocoes() > 0) {
                                    personagem.setPocoes(personagem.getPocoes() - 1);
                                    personagem.setVida(personagem.getVida() + 30);
                                }
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
                } catch (NullPointerException e) {
                    System.out.println("");
                }
            }
            if (personagem.getVida() <= 0) {
                System.out.println("====================");
                System.out.println("☠️ Você morreu!");
                System.out.println("====================");

            } else {

                delay(3000);
                System.out.println("============================");
                System.out.println(
                        "🏆 Você derrotou " + inimigo.getName()
                );
                System.out.println("============================");
                delay(3000);
                GanhaLevel(personagem, inimigo);

                serviceLoot.verificarSorteio(personagem, inimigo);
                delay(2000);


            }
        }
        }
        public void verificarLevelDeJogadorParaBatalha (Personagem personagem){
            ClasseInimigo inimigo = ClasseInimigo.porLevel(personagem.getLevel());
            batalha(personagem, inimigo);

        }


        public void GanhaLevel (Personagem personagem, ClasseInimigo inimigo){
            if (inimigo.getLevel() < 8) {
                personagem.setLevel(personagem.getLevel() + 2);
                System.out.println("E ganhou 2 levels!!");

                personagemRepository.save(personagem);

            } else if (inimigo.getLevel() >= 8 && inimigo.getLevel() <= 20) {
                personagem.setLevel(personagem.getLevel() + 5);
                System.out.println("E ganhou 5 levels!!");

                personagemRepository.save(personagem);
            }
        }
    }