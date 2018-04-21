package mkedron.vistula.pl.dices.view;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Random;

import mkedron.vistula.pl.dices.R;
import mkedron.vistula.pl.dices.dao.PlayerDao;
import mkedron.vistula.pl.dices.game.DiceResourceMap;
import mkedron.vistula.pl.dices.model.Dice;
import mkedron.vistula.pl.dices.model.Player;

public class GameActivity extends AppCompatActivity {

    private Dice diceLeft;
    private Dice diceRight;
    private Dialog dialog;
    private PlayerDao playerDao;
    private DiceResourceMap diceResourceMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        diceResourceMap = DiceResourceMap.getInstance();
        diceLeft = new Dice(getImageViewById(R.id.diceLeft));
        diceRight = new Dice(getImageViewById(R.id.diceRight));
        playerDao = PlayerDao.getInstance();

    }

    public void saveResult(View view) {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.save_result_layout);
        dialog.show();
    }

    public void savePlayer(View view) {
        Player player = prepareNewPlayer();
        playerDao.savePlayer(player);
        dialog.cancel();
        moveToMainMenuActivity();
    }

    @NonNull
    private Player prepareNewPlayer() {
        EditText playerEditText = (EditText) dialog.findViewById(R.id.playerName);
        Player player = new Player();
        player.setName(playerEditText.getText().toString());
        player.setScore(getScore());
        return player;
    }

    private void moveToMainMenuActivity() {
        Intent intent = new Intent(this,MainMenu.class);
        startActivity(intent);
    }

    private Integer getScore() {
        Integer diceLeftResult = Integer.valueOf(diceLeft.getScore());
        Integer diceRightResult = Integer.valueOf(diceRight.getScore());
        Integer result = diceLeftResult+diceRightResult;
        return result;
    }

    public void playGame(View view) {
        diceLeft.rand();
        diceRight.rand();
        enableSaveResultButton();

    }

    private ImageView getImageViewById(int diceId) {
        return (ImageView) findViewById(diceId);
    }

    private void enableSaveResultButton() {
        findViewById(R.id.saveResultsButton).setVisibility(View.VISIBLE);
    }


}
