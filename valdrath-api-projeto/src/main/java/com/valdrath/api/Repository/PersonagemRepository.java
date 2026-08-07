package com.valdrath.api.Repository;

import com.valdrath.api.Model.Personagem;
import com.valdrath.api.Model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {

    Personagem findByPlayer(Player player);

}
