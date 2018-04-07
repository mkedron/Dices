package mkedron.vistula.pl.dices.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mkedron.vistula.pl.dices.R;
import mkedron.vistula.pl.dices.dao.PlayerDao;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void printPlayers(View view) {
        System.out.println("nastepuje proba");
        PlayerDao playerDao = PlayerDao.getInstance();
        System.out.println(playerDao.getPlayers(1));
        System.out.println(playerDao.getPlayer("default"));
        System.out.println(playerDao.getPlayer("test"));
    }
}
