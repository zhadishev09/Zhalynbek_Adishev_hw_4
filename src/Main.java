//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefence;
    public static String[] heroesAttackType = new String[]{"Physical", "Magical", "Mental", "Lucky", "Berserk", "Golem", "Thor"};
    public static int[] heroesHealth = new int[]{270, 260, 250, 230, 240, 700, 350};
    public static int[] heroesDamage = new int[]{10, 15, 20, 7, 25, 9, 18};
    public static int roundNumber = 0;
    public static int medicHealth = 200;
    public static int medicHealingPower = 50;
    public static String medicName = "Врач";
    public static int medicDamage = 0;

    public Main() {
    }

    public static void main(String[] args) {
        printStatistics();

        while(!isGameOver()) {
            playRound();
        }

    }

    public static void playRound() {
        ++roundNumber;
        chooseBossDefence();
        bossHits();
        heroesHit();
        luckySkill();
        golemHit();
        berserkHit();
        ThorHit();
        if (medicHealth > 0) {
            medicHeal();
        }

        printStatistics();
    }

    private static void ThorHit() {
        Random random = new Random();
        boolean stan = random.nextBoolean();
        int thorIndex = 0;

        for(int i = 0; i < heroesAttackType.length; ++i) {
            if (heroesAttackType[i].equals("Berserk")) {
                thorIndex = i;
                break;
            }
        }

        if (heroesHealth[thorIndex] > 0 && stan) {
            bossDamage = 0;
            System.out.println(" Boss ston  ");
        }

        if (heroesHealth[thorIndex] > 0 && !stan) {
            bossDamage = 50;
        }

    }

    private static void berserkHit() {
        int berserkIndex = 0;

        for(int i = 0; i < heroesAttackType.length; ++i) {
            if (heroesAttackType[i].equals("Berserk")) {
                berserkIndex = i;
                break;
            }
        }

        if (heroesHealth[berserkIndex] > 0) {
            int[] var10000 = heroesHealth;
            var10000[berserkIndex] += bossDamage / 5;
            bossHealth -= bossDamage / 5;
        }

        System.out.println("Berserk block " + bossDamage / 5);
    }

    private static void golemHit() {
        int golemIndex = 0;

        int i;
        for(i = 0; i < heroesAttackType.length; ++i) {
            if (heroesAttackType[i].equals("Golem")) {
                golemIndex = i;
                break;
            }
        }

        for(i = 0; i < heroesHealth.length; ++i) {
            if (heroesHealth[golemIndex] > 0 && heroesHealth[i] > 0 && heroesHealth[i] != heroesHealth[golemIndex]) {
                int[] var10000 = heroesHealth;
                var10000[i] += bossDamage / 5;
                var10000 = heroesHealth;
                var10000[golemIndex] -= bossDamage / 5;
            }
        }

    }

    private static void luckySkill() {
        Random random = new Random();
        boolean luckyChance = random.nextBoolean();
        if (heroesHealth[3] > 0 && luckyChance) {
            int[] var10000 = heroesHealth;
            var10000[3] += bossDamage;
            System.out.println("Lucky увернулся ");
        }

    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        bossDefence = heroesAttackType[randomIndex];
    }

    public static void medicHeal() {
        for(int i = 0; i < heroesHealth.length; ++i) {
            if (heroesHealth[i] < 100 && heroesHealth[i] > 0 && heroesHealth[i] != medicHealth) {
                int[] var10000 = heroesHealth;
                var10000[i] += medicHealingPower;
                System.out.println(medicName + " вылечил " + heroesAttackType[i] + " на " + medicHealingPower + " здоровье");
                break;
            }
        }

    }

    public static void heroesHit() {
        for(int i = 0; i < heroesDamage.length; ++i) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                int damage = heroesDamage[i];
                if (heroesAttackType[i] == bossDefence) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2;
                    damage = heroesDamage[i] * coeff;
                    System.out.println("Critical damage: " + damage);
                }

                if (bossHealth - damage < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth -= damage;
                }
            }
        }

    }

    public static void bossHits() {
        for(int i = 0; i < heroesHealth.length; ++i) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] -= bossDamage;
                }
            }

            if (i == heroesHealth.length - 1) {
                medicDamage = bossDamage;
                if (medicHealth - medicDamage < 0) {
                    medicHealth = 0;
                } else {
                    medicHealth -= medicDamage;
                }
            }
        }

    }

    public static boolean isGameOver() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        } else {
            boolean allHeroesDead = true;

            for(int i = 0; i < heroesHealth.length; ++i) {
                if (heroesHealth[i] > 0) {
                    allHeroesDead = false;
                    break;
                }
            }

            if (allHeroesDead) {
                System.out.println("Boss won!!!");
            }

            return allHeroesDead;
        }
    }

    public static void printStatistics() {
        int var10001 = roundNumber;
        System.out.println("ROUND " + var10001 + " --------------------");
        System.out.println("Boss health: " + bossHealth + " damage: " + bossDamage + " defence: " + (bossDefence == null ? "No Defence" : bossDefence));

        for(int i = 0; i < heroesHealth.length; ++i) {
            String var1 = heroesAttackType[i];
            System.out.println(var1 + " health: " + heroesHealth[i] + " damage: " + heroesDamage[i]);
        }

        System.out.println(medicName + " health: " + medicHealth + " лечения " + medicHealingPower);
    }
}
