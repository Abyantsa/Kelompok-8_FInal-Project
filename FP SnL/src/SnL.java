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
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class SnL {
    private ArrayList<Player> players;
    private ArrayList<Snake> snakes;
    private ArrayList<Ladder> ladders;
    private int boardSize;
    private int gameStatus;
    private int nowPlaying;
    private ArrayList<String> surpriseCards;
    private int difficultyLevel;
    private int turnCount;
    private boolean isDayMode;
    private SoundEffects soundEffects;

    public SnL(int s, SoundEffects soundEffects) {
        this.boardSize = s;
        this.players = new ArrayList<>();
        this.snakes = new ArrayList<>();
        this.ladders = new ArrayList<>();
        this.gameStatus = 0;
        this.turnCount = 0;
        this.surpriseCards = new ArrayList<>();
        this.soundEffects = soundEffects;
        initializeSurpriseCards();
    }

    private void initializeSurpriseCards() {
        surpriseCards.add("Move forward 3 spaces!");
        surpriseCards.add("Roll the dice again!");
        surpriseCards.add("Move back 2 spaces!");
        surpriseCards.add("Skip your next turn!");
    }

    private String drawSurpriseCard() {
        Random rand = new Random();
        int cardIndex = rand.nextInt(surpriseCards.size());
        return surpriseCards.get(cardIndex);
    }

    public void setDifficultyLevel(int level) {
        this.difficultyLevel = level;
    }

    public void setBoardSize(int s) {
        this.boardSize = s;
    }

    public void setGameStatus(int s) {
        this.gameStatus = s;
    }

    public int getGameStatus() {
        return this.gameStatus;
    }

    public void play() {
        Player playerInTurn;
        Scanner read = new Scanner(System.in);
        System.out.println("Please enter Player 1: ");
        String player1 = read.nextLine();
        System.out.println("Please enter Player 2: ");
        String player2 = read.nextLine();

        Player p1 = new Player(player1);
        Player p2 = new Player(player2);
        initiateGame();
        addPlayer(p1);
        addPlayer(p2);

        do {
            playerInTurn = getWhoseTurn();
            System.out.println("Now Playing " + playerInTurn.getName());
            System.out.println(playerInTurn.getName() + " please press enter to roll the dice");
            String enter = read.nextLine();
            int x = 0;
            if (enter.isEmpty()) {
                x = playerInTurn.rollDice();
                soundEffects.playRollDiceSound();
            }
            System.out.println("Dice Number : " + x);
            movePlayerAround(playerInTurn, x);
            System.out.println("New Position:  " + playerInTurn.getPosition());

            if (playerInTurn.getPosition() % 5 == 0) {
                String card = drawSurpriseCard();
                System.out.println("Surprise Card: " + card);
                soundEffects.playSurpriseCardSound();
                applySurpriseCardEffect(card, playerInTurn);
            }

            System.out.println("==============================================");

            turnCount++;
            if (turnCount % 10 == 0) {
                switchDayNightMode();
            }

        } while (getGameStatus() != 2);

        soundEffects.playWinnerSound();
        System.out.println("The winner is: " + playerInTurn.getName());
    }

    public void addPlayer(Player s) {
        this.players.add(s);
    }

    public ArrayList<Player> getPlayers(Player s) {
        return this.players;
    }

    public void addSnake(Snake s) {
        this.snakes.add(s);
    }

    public void addSnakes(int[][] s) {
        for (int r = 0; r < s.length; r++) {
            Snake snake = new Snake(s[r][0], s[r][1]);
            this.snakes.add(snake);
        }
    }

    public void addLadder(Ladder l) {
        this.ladders.add(l);
    }

    public void addLadders(int[][] l) {
        for (int r = 0; r < l.length; r++) {
            Ladder ladder = new Ladder(l[r][1], l[r][0]);
            this.ladders.add(ladder);
        }
    }

    public int getBoardSize() {
        return this.boardSize;
    }

    public ArrayList<Snake> getSnakes() {
        return this.snakes;
    }

    public ArrayList<Ladder> getLadders() {
        return this.ladders;
    }

    public void initiateGame() {
        int[][] l = {
                {2, 23},
                {8, 34},
                {20, 77},
                {32, 68},
                {41, 79},
                {74, 88},
                {82, 100},
                {85, 95}
        };
        addLadders(l);

        int[][] s = {
                {5, 47},
                {9, 29},
                {15, 38},
                {25, 97},
                {33, 53},
                {37, 62},
                {54, 86},
                {70, 92}
        };
        addSnakes(s);

        if (difficultyLevel == 2) { // Medium
            int[][] extraSnakes = {
                    {12, 45},
                    {28, 55},
                    {39, 70},
                    {46, 80},
                    {59, 85},
                    {68, 90}
            };
            int[][] extraLadders = {
                    {60, 95}
            };
            addSnakes(extraSnakes);
            addLadders(extraLadders);
        } else if (difficultyLevel == 3) { // Hard
            int[][] extraSnakes = {
                    {12, 45},
                    {28, 55},
                    {39, 70},
                    {46, 80},
                    {59, 85},
                    {68, 90},
                    {78, 95},
                    {84, 99}
            };
            int[][] extraLadders = {
                    {60, 95},
                    {75, 100}
            };
            addSnakes(extraSnakes);
            addLadders(extraLadders);
        }
    }

    public void movePlayerAround(Player p, int x) {
        p.moveAround(x, this.boardSize);

        for (Ladder l : this.ladders) {
            if (p.getPosition() == l.getBottomPosition()) {
                System.out.println(p.getName() + " you got a ladder from: " + l.getBottomPosition() + " to: " + l.getTopPosition());
                p.setPosition(l.getTopPosition());
                soundEffects.playLadderSound();
            }
        }
        for (Snake s : this.snakes) {
            if (p.getPosition() == s.getHeadPosition()) {
                p.setPosition(s.getTailPosition());
                System.out.println(p.getName() + " you got a snake head from " + s.getHeadPosition() + " slide down to " + s.getTailPosition());
                soundEffects.playSnakeSound();
            }
        }
        if (p.getPosition() == this.boardSize) {
            this.gameStatus = 2;
        }
    }

    private void applySurpriseCardEffect(String card, Player player) {
        switch (card) {
            case "Move forward 3 spaces!":
                player.moveAround(3, this.boardSize);
                System.out.println(player.getName() + " moves forward 3 spaces!");
                break;
            case "Roll the dice again!":
                int x = player.rollDice();
                movePlayerAround(player, x);
                System.out.println(player.getName() + " rolls the dice again and gets " + x + "!");
                break;
            case "Move back 2 spaces!":
                player.moveAround(-2, this.boardSize);
                System.out.println(player.getName() + " moves back 2 spaces!");
                break;
            case "Skip your next turn!":
                System.out.println(player.getName() + " skips the next turn!");
                if (this.nowPlaying == 0) {
                    this.nowPlaying = 1;
                } else {
                    this.nowPlaying = 0;
                }
                break;
        }
    }

    public Player getWhoseTurn() {
        if (this.gameStatus == 0) {
            this.gameStatus = 1;
            double r = Math.random();
            if (r <= 0.5) {
                this.nowPlaying = 0;
                return this.players.get(0);
            } else {
                this.nowPlaying = 1;
                return this.players.get(1);
            }
        } else {
            if (this.nowPlaying == 0) {
                this.nowPlaying = 1;
                return this.players.get(1);
            } else {
                this.nowPlaying = 0;
                return this.players.get(0);
            }
        }
    }

    private void switchDayNightMode() {
        if (isDayMode) {
            isDayMode = false;
            System.out.println("Switching to night mode...");
        } else {
            isDayMode = true;
            System.out.println("Switching to day mode...");
        }
    }
}
