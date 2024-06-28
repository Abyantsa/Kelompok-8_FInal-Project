/**
 * -----------------------------------------------------
 * ES234211 - Programming Fundamental
 * Genap - 2023/2024
 * Group Capstone Project: Snake and Ladder Game
 * -----------------------------------------------------
 * Class    : D
 * Group    : 8
 * Members  :
 * 1. 5026231019 - Nathaniel Lado Hadi Winata
 * 2. 5026231163 - Muhammad Abyan Tsabit Amani
 * 3. 5026231108 - Muhammad Raihan Hassan
 * ------------------------------------------------------
 */
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.print("Please enter the board size: ");
        int boardSize = read.nextInt();
        read.nextLine(); // Consume the newline character

        SoundEffects soundEffects = new SoundEffects();
        SnL g1 = new SnL(boardSize, soundEffects);

        System.out.println("Please choose the game difficulty level: ");
        System.out.println("Easy Game : Type 1");
        System.out.println("Medium Game : Type 2");
        System.out.println("Hard Game : Type 3");
        int difficulty = read.nextInt();
        g1.setDifficultyLevel(difficulty);

        g1.play();
    }
}
