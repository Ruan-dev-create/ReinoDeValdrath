package com.valdrath.api.Cutscene;

import com.valdrath.api.Principal.Principal;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.valdrath.api.Principal.Principal.esperaENTER;
import static com.valdrath.api.Principal.Principal.pulaLinhas;

@Component
public class CutsceneView {


    public void iniciarCutsceneAbertura(Scanner l) {

        pulaLinhas(50);

        System.out.println("""
                Há muito tempo, dragões e homens viviam em equilíbrio...
                """);

        Principal.delay(2000);

        System.out.println("""
                Mas o equilíbrio nunca dura para sempre.
                """);

        Principal.delay(2500);

        System.out.println("""
                O céu sobre o Reino de Valdrath sempre foi calmo. Até aquela noite.
                """);

        Principal.delay(2500);

        System.out.println("""
                Sem aviso, sem trovões, apenas um silêncio pesado que engoliu o vento.
                """);
        Principal.delay(2500);

        System.out.println("""
                Então vieram as asas.
                """);

        Principal.delay(1500);

        System.out.println("""
                Enormes. Negras como breu. Cobrindo as estrelas como uma sombra viva.
                """);

        Principal.delay(2500);

        System.out.println("""
                FÚRIA DA NOITE havia despertado.
                """);

        Principal.delay(3000);

        System.out.println("""
        Um dragão de categoria altíssima, adormecido há gerações nas montanhas ao norte, agora pairava sobre as torres do castelo.
        """);

        Principal.delay(2500);

        System.out.println("""
                As chamas consumiram os campos. Os sinos da guarda tocaram tarde demais.
                """);

        Principal.delay(2500);

        System.out.println("""
        Em meio ao caos, gritos, fogo e destruição... alguém sobreviveu.
        """);

        Principal.delay(1800);

        System.out.println("\nVocê.");
        Principal.delay(2000);

        esperaENTER(l);
    }
}
