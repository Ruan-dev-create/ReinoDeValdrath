package com.valdrath.api.Principal;

import com.valdrath.api.Combate.Combate;
import com.valdrath.api.Exception.ValdrathException;
import com.valdrath.api.Repository.PlayerRepository;
import com.valdrath.api.Service.CadastroPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Principal {

    private Combate combate;

    private static Scanner l = new Scanner(System.in);

    @Autowired
    private CadastroPlayerService cadastro;

    private PlayerRepository playerRepository;

    public void rodandoJogo() {
        telaInicial();
        telaPrincipal();

    }

    public static void delay(int tempo) {

        try {

            Thread.sleep(tempo);

        } catch (InterruptedException e) {

            e.getMessage();

        }
    }

    public static void telaInicial() {
        pulaLinhas(40);
        delay(4000);
    }

    public static void mostrarLogo() {
        System.out.println("""
                ====================================================================
                ██╗   ██╗ █████╗ ██╗     ██████╗ ██████╗  █████╗ ████████╗██╗  ██╗
                ██║   ██║██╔══██╗██║     ██╔══██╗██╔══██╗██╔══██╗╚══██╔══╝██║  ██║
                ██║   ██║███████║██║     ██║  ██║██████╔╝███████║   ██║   ███████║
                ╚██╗ ██╔╝██╔══██║██║     ██║  ██║██╔══██╗██╔══██║   ██║   ██╔══██║
                 ╚████╔╝ ██║  ██║███████╗██████╔╝██║  ██║██║  ██║   ██║   ██║  ██║
                  ╚═══╝  ╚═╝  ╚═╝╚══════╝╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝   ╚═╝  ╚═╝   
                ====================================================================""");
    }


    public void telaPrincipal() {
        int opPrincipal = 0;
        boolean valido = false;

        mostrarLogo();
        delay(500);

        while(!valido){
            System.out.print("""
                    ====================================================================
                    - Escolha uma opção

                    [ 1 ] Jogar
                    [ 2 ] Configurações
                    [ 3 ] Sair

                    >>>""");

            try {
                opPrincipal = Integer.parseInt(l.nextLine().trim());

                if(opPrincipal == 1 || opPrincipal == 2 || opPrincipal == 3){
                    valido = true;

                    if(opPrincipal == 1){

                        cadastro.verificaCadastroLogin();

                    } else if (opPrincipal == 2) {

                        System.out.println("Está em desenvolvimento...");
                        esperaENTER(l);
                        telaPrincipal();

                    }else if (opPrincipal == 3) {

                        System.exit(0);

                    }

                }else{
                    System.out.println("Digite uma opção valida, porfavor: ");
                }

        }catch(NumberFormatException e){
            System.out.println("Isso não é um número, tenta de novo.");
        }

        }


    }

    public static void pulaLinhas(int linhas){
        for(int i = 0; i < linhas; i++){
            System.out.println("");
        }
    }

    public static void esperaENTER(Scanner l){
        System.out.println("\nAperte ENTER para continuar.");
        l.nextLine();
    }


}
