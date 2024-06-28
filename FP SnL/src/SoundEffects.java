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

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundEffects {

    private Clip rollDiceClip;
    private Clip winnerClip;
    private Clip snakeClip;
    private Clip ladderClip;
    private Clip surpriseCardClip;

    public SoundEffects() {
        loadSounds();
    }

    private void loadSounds() {
        try {
            rollDiceClip = AudioSystem.getClip();
            rollDiceClip.open(AudioSystem.getAudioInputStream(new File("sounds/roll_dice.wav")));

            winnerClip = AudioSystem.getClip();
            winnerClip.open(AudioSystem.getAudioInputStream(new File("sounds/winner.wav")));

            snakeClip = AudioSystem.getClip();
            snakeClip.open(AudioSystem.getAudioInputStream(new File("sounds/snake.wav")));

            ladderClip = AudioSystem.getClip();
            ladderClip.open(AudioSystem.getAudioInputStream(new File("sounds/ladder.wav")));

            surpriseCardClip = AudioSystem.getClip();
            surpriseCardClip.open(AudioSystem.getAudioInputStream(new File("sounds/surprise_card.wav")));
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playRollDiceSound() {
        playSound(rollDiceClip);
    }

    public void playWinnerSound() {
        playSound(winnerClip);
    }

    public void playSnakeSound() {
        playSound(snakeClip);
    }

    public void playLadderSound() {
        playSound(ladderClip);
    }

    public void playSurpriseCardSound() {
        playSound(surpriseCardClip);
    }

    private void playSound(Clip clip) {
        if (clip != null) {
            clip.stop();
            clip.setFramePosition(0);
            clip.start();
        }
    }
}
