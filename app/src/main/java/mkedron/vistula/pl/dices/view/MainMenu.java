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
        PlayerDao playerDao = new PlayerDao();

        playerDao.getPlayers(1);
    }
}
