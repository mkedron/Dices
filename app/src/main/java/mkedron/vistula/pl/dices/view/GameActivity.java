package mkedron.vistula.pl.dices.view;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import mkedron.vistula.pl.dices.R;
import mkedron.vistula.pl.dices.dao.PlayerDao;
import mkedron.vistula.pl.dices.game.DiceResourceMap;
import mkedron.vistula.pl.dices.model.Dice;
import mkedron.vistula.pl.dices.model.Player;

public class GameActivity extends AppCompatActivity {

    public static final String SCORE = "Wynik : ";
    public static final String MAX_SCORE = " max : ";
    public static final String GAME_COUNTER = "Rozgrywka : ";
    private Dice diceLeft;
    private Dice diceRight;
    private Dialog dialog;
    private PlayerDao playerDao;
    private DiceResourceMap diceResourceMap;
    private TextView scoreTextView;
    private TextView highScoreTextView;
    private TextView counterTextView;
    private Button playGameButton;
    private Integer gameCounter = 1;
    private Integer score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        diceResourceMap = DiceResourceMap.getInstance();
        diceLeft = new Dice(getImageViewById(R.id.diceLeft));
        diceRight = new Dice(getImageViewById(R.id.diceRight));
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        highScoreTextView = (TextView) findViewById(R.id.highScoreTextView);
        counterTextView = (TextView) findViewById(R.id.counterTextView);
        playGameButton = (Button) findViewById(R.id.playButton);
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
        enablePlayGameButton(true);
        enableSaveResultButton(false);
        moveToMainMenuActivity();
    }

    @NonNull
    private Player prepareNewPlayer() {
        EditText playerEditText = (EditText) dialog.findViewById(R.id.playerName);
        Player player = new Player();
        player.setName(playerEditText.getText().toString());
        player.setScore(score);
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
        diceLeft.play();
        diceRight.play();
        updateScore();
        printScore();
        gameCounter++;
        if(gameCounter > 10) {
            enableSaveResultButton(true);
            enablePlayGameButton(false);
        }

    }

    private void updateScore() {
        if(score < getScore()) {
            score = getScore();
        }
    }

    private ImageView getImageViewById(int diceId) {
        return (ImageView) findViewById(diceId);
    }

    private void printScore() {
        scoreTextView.setText(SCORE +String.valueOf(getScore()));
        highScoreTextView.setText(MAX_SCORE+String.valueOf(score));
        counterTextView.setText(GAME_COUNTER +gameCounter);

    }

    private void enablePlayGameButton(boolean enabled) {
        playGameButton.setEnabled(enabled);
    }

    private void enableSaveResultButton(boolean enabled) {
        if(enabled) {
            findViewById(R.id.saveResultsButton).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.saveResultsButton).setVisibility(View.INVISIBLE);
        }
    }


}
