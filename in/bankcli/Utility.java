package in.bankcli;

import java.util.Scanner;

public class Utility {

    static int numOfDashes = 8;

    static String greet(String greeting){
        StringBuilder sb = new StringBuilder();
        sb.append("=".repeat(numOfDashes)).append(" ").append(greeting.toUpperCase());
        sb.append(" ").append("=".repeat(numOfDashes)).append("\n");
        System.out.println();
        return sb.toString();
    }

    static int greet(String greeting, Scanner sc, String... options){
        int choice;
        System.out.println(greet(greeting));
        System.out.println("Select your option: \n");
        for (int i = 0; i < options.length; i++){
            System.out.println((i+1)+ ". " +options[i]);
        }
        System.out.print("\nSelect your choice: ");
        choice = sc.nextInt();
        return choice;
    }

    static int greet(Scanner sc, String... options){
        int choice;
        System.out.println("Select your option: \n");
        for (int i = 0; i < options.length; i++){
            System.out.println((i+1)+ ". " +options[i]);
        }
        System.out.print("\nSelect your choice: ");
        choice = sc.nextInt();
        return choice;
    }
}
