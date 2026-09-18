package zl2;

import java.util.Scanner;

public class BmiCalculator {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        System.out.println("Please enter your weight in kg");
        double weight = sn.nextDouble();
        System.out.println("Please enter your height in cm");
        double height = sn.nextDouble();
        double heightInMeters = height*0.01;
        double BMI = weight/(heightInMeters*heightInMeters);
        System.out.println("Your BMI is ");
        System.out.printf("%.2f%n", BMI);

        if(BMI>0 && BMI<18.5){
            System.out.println("Underweight");
        } else if (BMI >= 18.5 && BMI <= 24.9) {
            System.out.println("Normal weight");
        } else if (BMI >= 25.0 && BMI <= 29.9) {
            System.out.println("Overweight");
        } else if (BMI >= 30.0) {
            System.out.println("Obesity");
        } else {
            System.out.println("Something is wrong with your inputs, try again");
        }
        if (height <30 || height >250) {
            System.out.println("Warring! You are not human?");
        }
    }
}
