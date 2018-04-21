package mkedron.vistula.pl.dices.game;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.HashMap;
import java.util.Map;

import mkedron.vistula.pl.dices.DicesApplication;
import mkedron.vistula.pl.dices.R;

/**
 * Created by mkked on 21.04.2018.
 */

public class DiceResourceMap {

    private static DiceResourceMap diceResourceMap = new DiceResourceMap();
    private Context context;

    private DiceResourceMap() {
        this.context = DicesApplication.getContext();
        diceMap = new HashMap<>();
        diceMap.put(1,context.getDrawable(R.drawable.dice1));
        diceMap.put(2,context.getDrawable(R.drawable.dice2));
        diceMap.put(3,context.getDrawable(R.drawable.dice3));
        diceMap.put(4,context.getDrawable(R.drawable.dice4));
        diceMap.put(5,context.getDrawable(R.drawable.dice5));
        diceMap.put(6,context.getDrawable(R.drawable.dice6));
    }

    private Map<Integer, Drawable> diceMap;

    public static DiceResourceMap getInstance() {
        return diceResourceMap;
    }

    public Drawable getDrawableByValue(Integer value) {
        return diceMap.get(value);
    }
}
