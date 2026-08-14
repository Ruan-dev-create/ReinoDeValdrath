package com.valdrath.api.Combate;

import com.valdrath.api.Model.ClasseInimigo;
import com.valdrath.api.Model.Personagem;

public interface Combate {
    void batalha(Personagem player, ClasseInimigo monstro);
}
