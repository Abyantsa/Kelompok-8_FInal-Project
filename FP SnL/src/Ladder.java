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


public class Ladder{
    private int topPosition;
    private int bottomPosition;

    public Ladder(int t, int b){
        this.topPosition = t;
        this.bottomPosition = b;
    }

    public void setTopPosition(int t){
        this.topPosition = t;
    }

    public void setBottomPosition(int b){
        this.bottomPosition = b;
    }

    public int getTopPosition() {
        return this.topPosition ;
    }

    public int getBottomPosition() {
        return this.bottomPosition ;
    }
}
