    package com.valdrath.api.Model;

    import java.util.List;

    public enum ClasseInimigo {
        Rato(
                "rato",
                1,
                "comum",
                50,
                5,
                List.of(
                        new Loot("Moedas", 0.50),
                        new Loot("Poção de cura", 0.15),
                        new Loot("Faca Quebrada", 0.05)
                )
        ),

        Lobo_Sombrio("Lobo Sombrio",
                5,
                "comum",
                100,
                10,
                List.of(
                        new Loot("Moedas", 0.50),
                        new Loot("Poção de cura", 0.15),
                        new Loot("Espada de caçador", 0.10)
                )
        );


        private final String name;
        private final int level;
        private final String rank;
        private final int vida;
        private final int dano;
        private final List<Loot> loots;

        ClasseInimigo(String name, int level, String rank, int vida, int dano,  List<Loot> loots) {
            this.name = name;
            this.level = level;
            this.rank = rank;
            this.vida = vida;
            this.dano = dano;
            this.loots = loots;
        }

        public void mostrarInfoMonstro(){
            System.out.println("Nome: " + name + "👹");
            System.out.println("Level: " + level);
            System.out.println("Rank: " + rank);
            System.out.println("Vida: " + vida + "❤️");
            System.out.println("Dano: " + dano + "⚔️");
        }

        public String getName() {
            return name;
        }

        public int getLevel() {
            return level;
        }

        public String getRank() {
            return rank;
        }

        public int getVida() {
            return vida;
        }

        public int getDano() {
            return dano;
        }

        public static ClasseInimigo porLevel(int level) {
            if (level >= Lobo_Sombrio.level) {
                return Lobo_Sombrio;
            } else if (level >= Rato.level) {
                return Rato;
            } else if (level > Lobo_Sombrio.getLevel()) {
                return Lobo_Sombrio;
            }else {
                throw new IllegalArgumentException("Nenhum inimigo cadastrado para o level " + level);
            }
        }

        public List<Loot> getLoots() {
            return loots;
        }
    }