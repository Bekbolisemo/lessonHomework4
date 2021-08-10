package com.company;

import java.util.Random;

public class Main {
    public static int random = (int) (Math.random() * 35);
    public static int bossHealth = 70000;
    public static int bossDamage = 30;
    public static String bossDefence = "";
    public static int[] heroesHealth = {250, 250, 250,};
    public static int[] heroesDamage = {35, 37, 23,};
    public static String[] heroesAttackType = {
            "Physical", "Magical", "Kinetic"};
    public static int medicHeal;
    public static int medicHealth = 30000;


    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
        System.out.println(random);
    }

    public static void round() {
        if (bossHealth > 0) {
            chooseBossDefence();
            bossHits();
            bossHitsMedic();

        }
        heroesHit();
        medicHeal();
        printStatistics();

    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
        bossDefence = heroesAttackType[randomIndex];
        System.out.println("Boss choose: " + bossDefence);
    }

    public static int randomHeal() {
        int min = 1;
        int max = 35;
        int diff = max - min;
        Random random = new Random();
        int i = random.nextInt(diff + 1);
        i += min;

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
                      //  heroesHealth[i] = heroesHealth[i] + shooseMedicHeal();
                    }
                }
               /* if (heroesHealth[i] <100 && heroesHealth[i] >0 && medicHealth >0){
                    for (int j = 0; j < heroesHealth.length; j++) {
                        heroesHealth[i] = (int) (Math.random() * 35);
                        heroesHealth[i] = heroesHealth[i] + random;
                    }
                } */
                //     healMedic();
            }

          /*  for (int iP = 0; iP < heroesHealth.length; iP++) {
                if (heroesHealth[iP] < 100 && heroesHealth[iP] > 0 && medicHealth > 0) {
                  if (heroesHealth[iP] > 0) {
                        heroesHealth[iP] = shooseMedicHeal() + heroesHealth[iP];
                    }
                }                               // хил всех

        }*/

    }
    /*   public static void healMedic() {
            for (int d : heroesHealth ) {
                if (heroesHealth[d] <100 && heroesHealth[d] >0 && medicHealth >0){
                    if (heroesHealth[d] > 0){
                        heroesHealth[d] = shooseMedicHeal() + heroesHealth[d];

                    }                     // попытка правельного хила
                }
            }
        }*/
}
public static void medicHeal(){
    for (int i = 0; i < heroesHealth.length; i++) {

        if (heroesHealth[i] <100 && heroesHealth[i] >0 && medicHealth >0){
            for (int j = 0; j < heroesHealth.length; j++) {
                heroesHealth[i] = (int) (Math.random() * 35);
                //heroesHealth[i] = heroesHealth[i] + random;
                heroesHealth[i]= heroesHealth[i] + randomHeal();
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
    } // рабочий метод

    public static void bossHitsMedic() {

        if (medicHealth > 0) {
            if (medicHealth - bossDamage < 0) {
                medicHealth = 0;
            } else {
                medicHealth = medicHealth - bossDamage;
            }
        }
    } // рабочий метод

    public static void printStatistics() {
        System.out.println("++++++++++++++");
        System.out.println("Boss health: " + bossHealth + " ["
                + bossDamage + "]");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i]
                    + " health: " + heroesHealth[i] + " ["
                    + heroesDamage[i] + "]");
        }
        System.out.println("medicHealth: " + medicHealth + " -medicHeal [" + random + "]");
        System.out.println("++++++++++++++");
    }
}
