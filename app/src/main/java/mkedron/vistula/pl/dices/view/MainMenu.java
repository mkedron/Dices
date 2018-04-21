package mkedron.vistula.pl.dices.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mkedron.vistula.pl.dices.R;
import mkedron.vistula.pl.dices.dao.PlayerDao;
import mkedron.vistula.pl.dices.model.Player;
import mkedron.vistula.pl.dices.model.Players;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PlayerDao playerDao = PlayerDao.getInstance();
        setContentView(R.layout.activity_main_menu);
    }

    public void printPlayers(View view) {
        System.out.println("nastepuje proba");
        PlayerDao playerDao = PlayerDao.getInstance();
        System.out.println(playerDao.getPlayers(1));
        System.out.println(playerDao.getPlayer("default"));
        System.out.println(playerDao.getPlayer("test"));

        Intent intent = new Intent(this, ResultsActivity.class);
        startActivity(intent);

    }

    public void addPlayer(View view) {
        PlayerDao playerDao = PlayerDao.getInstance();
        Player player = new Player();
        player.setName("player2");
        player.setId("2");
        player.setScore(43);
        playerDao.savePlayer(player);
    }

    public void newGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void clearResults(View view) {
        PlayerDao playerDao = PlayerDao.getInstance();
        playerDao.clearResults();
    }
}
