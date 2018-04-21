package mkedron.vistula.pl.dices.view;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mkedron.vistula.pl.dices.R;
import mkedron.vistula.pl.dices.dao.PlayerDao;
import mkedron.vistula.pl.dices.model.Player;

import static mkedron.vistula.pl.dices.R.id.results;

public class ResultsActivity extends AppCompatActivity {

    public PlayerDao playerDao;

    private void init() {
        playerDao = PlayerDao.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        init();

        fillUpListWithResults();


    }

    private void fillUpListWithResults() {
        List<Player> players = getSortedPlayers();
        ArrayAdapter adapter = prepareArrayAdapter(players);
        setUpListView(adapter);
    }

    private void setUpListView(ArrayAdapter adapter) {
        ListView listView = (ListView) findViewById(results);
        listView.setAdapter(adapter);
    }

    @NonNull
    private ArrayAdapter prepareArrayAdapter(List<Player> players) {
        ArrayList arrayPlayers = new ArrayList();
        for(Player player : players) {
            arrayPlayers.add(player.getResultString());
        }
        return (ArrayAdapter) new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, arrayPlayers);
    }

    @NonNull
    private List<Player> getSortedPlayers() {
        List<Player> players = playerDao.getPlayers(3);
        Collections.sort(players, Collections.<Player>reverseOrder());
        return players;
    }

}
