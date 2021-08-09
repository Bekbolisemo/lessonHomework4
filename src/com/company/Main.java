package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefence = "";
    public static int[] heroesHealth = {260, 250, 270,};
    public static int[] heroesDamage = {15, 20, 10,};
    public static String[] heroesAttackType = {
            "Physical", "Magical", "Kinetic"};
    public static int medicHeal;
    public static int medicHealth = 50;


    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
        System.out.println(shooseMedicHeal());
    }

    public static void round() {
        if (bossHealth > 0) {
            chooseBossDefence();
            bossHits();
        }
        heroesHit();
        printStatistics();

    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
        bossDefence = heroesAttackType[randomIndex];
        System.out.println("Boss choose: " + bossDefence);
    }

    public static int shooseMedicHeal(){
        int min = 1;
        int max = 35;
        int diff = max - min;
        Random random = new Random();
        int i = random.nextInt(diff + 1);
        i += min;

        medicHeal = i;
        return i;
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 &&
                heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
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

    public static void heroesHit() {


        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossDefence == heroesAttackType[i]) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2; // 2,3,4,5,6,7,8,9,10
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println(
                            "Critical Damage: " + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
                if (heroesHealth[i] > 0 ){
                    medicHeal =medicHeal + heroesHealth[i] ;
            }

            }
        }
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }

        }
    }

    public static void printStatistics() {
        System.out.println("++++++++++++++");
        System.out.println("Boss health: " + bossHealth + " ["
                + bossDamage + "]");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i]
                    + " health: " + heroesHealth[i] + " ["
                    + heroesDamage[i] + "]");
        }
        System.out.println("medicHealth: " + medicHealth + " -medicHeal [" + medicHeal + "]");
        System.out.println("++++++++++++++");
    }
}
