package zl2;

import java.util.Scanner;

public class BmiCalculator {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        System.out.println("Please enter your weight in kg");
        double weightInKg = sn.nextDouble();
        System.out.println("Please enter your height in cm");
        double heightInCm = sn.nextDouble();
        double heightInMeters = heightInCm * 0.01;
        double bmi = weightInKg / (heightInMeters * heightInMeters);
        System.out.println("Your bmi is ");
        System.out.printf("%.2f%n", bmi);

        if (bmi <= 0) {
            System.out.println("BMI can't be less or equals 0!");
        } else if (bmi > 0 && bmi < 18.5) {
            System.out.println("Underweight");
        } else if (bmi >= 18.5 && bmi < 25.0) {
            System.out.println("Normal weight");
        } else if (bmi >= 25.0 && bmi < 30.0) {
            System.out.println("Overweight");
        } else if (bmi >= 30.0) {
            System.out.println("Obesity");
        } else {
            System.out.println("Something is wrong with your inputs, try again");
        }
        if (heightInCm < 30 || heightInCm > 250) {
            System.out.println("Warring! You are not human?");
        }
        sn.close();
    }
}