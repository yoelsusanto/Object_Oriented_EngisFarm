package src;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    public static void main(String[] Args){
        int inputInt;
        String input;
        Game gameObj;

        System.out.println();
        System.out.println(" _______ .__   __.   _______  __   __     _______.    _______    ___      .______      .___  ___.");
        System.out.println("|   ____||  \\ |  |  /  _____||  | (_ )   /       |   |   ____|  /   \\     |   _  \\     |   \\/   |");
        System.out.println("|  |__   |   \\|  | |  |  __  |  |  |/   |   (----`   |  |__    /  ^  \\    |  |_)  |    |  \\  /  |");
        System.out.println("|   __|  |  . `  | |  | |_ | |  |        \\   \\       |   __|  /  /_\\  \\   |      /     |  |\\/|  |");
        System.out.println("|  |____ |  |\\   | |  |__| | |  |    .----)   |      |  |    /  _____  \\  |  |\\  \\----.|  |  |  |");
        System.out.println("|_______||__| \\__|  \\______| |__|    |_______/       |__|   /__/     \\__\\ | _| `._____||__|  |__|");
        System.out.println("Welcome to ENGI's FARM");
        System.out.println("1. Start");
        System.out.println("2. Exit");
        System.out.println(">> ");

        Scanner scan = new Scanner(System.in);
        System.out.print(">> ");
        inputInt = scan.nextInt();
        if(inputInt == 2){
            System.exit(0);
        }

//        gameObj.renderUI();
        System.out.print("Enter your input : ");
        input = scan.nextLine();

    }
}