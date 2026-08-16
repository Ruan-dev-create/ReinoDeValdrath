package com.valdrath.api.Service;

import com.valdrath.api.Model.ClasseInimigo;
import com.valdrath.api.Model.Inventario;
import com.valdrath.api.Model.Loot;
import com.valdrath.api.Model.Personagem;
import com.valdrath.api.Repository.InventarioRepository;
import com.valdrath.api.Repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LootService {

    @Autowired
    private InventarioRepository inventarioRepository;

    public void verificarSorteio(Personagem personagem, ClasseInimigo inimigo) {

        verificaItensComuns(personagem, inimigo);

        if ("rato".equals(inimigo.getName())) {
            Loot faca_Quebrada = inimigo.getLoots().get(2);

            if(faca_Quebrada.getChanceDeDrop() > Math.random()){
                System.out.println("⚔️ Você ganhou a Faca De Ferro!!!");
                int quantidade = 1;

                adicionarAoInventario(personagem, faca_Quebrada, quantidade);

            }
        }

        if ("Lobo Sombrio".equals(inimigo.getName())) {
            Loot Espada_Do_Cacador = inimigo.getLoots().get(2);

            if(Espada_Do_Cacador.getChanceDeDrop() > Math.random()){
                System.out.println("⚔️ Você ganhou a Espada Do Cacador!!!");

                int quantidade = 1;

                adicionarAoInventario(personagem, Espada_Do_Cacador, quantidade);
            }
        }
    }

    public void verificaItensComuns(Personagem personagem, ClasseInimigo inimigo) {

        Loot moedas = inimigo.getLoots().get(0);
        Loot pocao = inimigo.getLoots().get(1);

        // MOEDAS
        if (moedas.getChanceDeDrop() > Math.random()) {

            System.out.println("🪙 Você ganhou moedas!");
            System.out.println("Quantidade: 5");

            int quantidade = 5;

            adicionarAoInventario(personagem, moedas, quantidade);

        }

        if (pocao.getChanceDeDrop() > Math.random()) {

            System.out.println("🧪 Você ganhou uma poção!");
            System.out.println("Quantidade: 1");

            int quantidade = 1;

            adicionarAoInventario(personagem, pocao, quantidade);
        }
    }

    public void adicionarAoInventario(Personagem personagem, Loot loot, int quantidade) {

        Inventario inventario = new Inventario();

        inventario.setPersonagem(personagem);
        inventario.setDrop(loot.getNome());
        inventario.setQuantidade(quantidade);

        inventarioRepository.save(inventario);
    }

}