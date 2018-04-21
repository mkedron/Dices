package mkedron.vistula.pl.dices.model;

import android.widget.ImageView;

import java.util.Random;

import mkedron.vistula.pl.dices.game.DiceResourceMap;

/**
 * Created by mkked on 21.04.2018.
 */

public class Dice {
    private Random random;
    private ImageView imageView;
    private Integer score;
    private DiceResourceMap diceResourceMap;

    public Dice(ImageView imageView) {
        this.imageView = imageView;
        random = new Random();
        diceResourceMap = DiceResourceMap.getInstance();
    }

    public void rand() {
        score = getRandDiceValue();
        imageView.setImageDrawable(diceResourceMap.getDrawableByValue(score));
    }

    private Integer getRandDiceValue() {
        return random.nextInt(6)+1;
    }

    public Integer getScore() {
        return score;
    }
}
