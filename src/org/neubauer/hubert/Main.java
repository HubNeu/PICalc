package org.neubauer.hubert;

import java.util.Scanner;

/**
 * TODO: convert to big decimal to have more than 9 digits
 */

public class Main {

    static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("How many digits of PI do you want? (Max safe is 9)");
        int digits = keyboard.nextInt();
        //parameters
        Double mass1 = 1d;
        Double mass2 = Math.pow(100, digits - 1);
        Double velocity1 = 0d;
        Double velocity2 = 1d;
        Double energy1 = 0.5d * mass1 * velocity1 * velocity1;
        Double energy2 = 0.5d * mass2 * velocity2 * velocity2;
        final Double constEnergy = energy1 + energy2;
        Double momentum1 = mass1 * velocity1;
        Double momentum2 = mass2 * velocity2;
        final Double constMomentum = momentum1 + momentum2;
        long hitCount = 0;

        do {
            if (velocity1 > velocity2) {
                //compute change of direction, but no hit
                velocity1 = -velocity1;
                hitCount++;
            }
            //compute hit
            Double[] vels = computeHit(velocity1, velocity2, mass1, mass2);
            velocity1 = vels[0];
            velocity2 = vels[1];
            hitCount++;
        } while (!(velocity1 > velocity2 & velocity1 <= 0 & velocity2 <= 0));
        System.out.print("PI with desired precision: ");
        boolean flag = true;
        for (char ch: Long.toString(hitCount).toCharArray()){
            if (flag) {
                System.out.print("3.");
                flag = false;
            } else {
                System.out.print(ch);
            }
        }
    }

    private static Double[] computeHit(Double vel1, Double vel2, Double mas1, Double mas2) {
        Double newVel1 = (mas1 - mas2) / (mas1 + mas2) * vel1 + (2 * mas2) / (mas1 + mas2) * vel2;
        Double newVel2 = (2 * mas1) / (mas1 + mas2) * vel1 + (mas2 - mas1) / (mas1 + mas2) * vel2;
        return new Double[]{newVel1, newVel2};
    }


}
