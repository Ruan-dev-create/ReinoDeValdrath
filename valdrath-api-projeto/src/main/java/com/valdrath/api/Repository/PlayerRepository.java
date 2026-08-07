package com.valdrath.api.Repository;

import com.valdrath.api.Model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByEmail(String email);


}
