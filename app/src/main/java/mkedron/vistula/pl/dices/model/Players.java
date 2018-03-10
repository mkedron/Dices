package mkedron.vistula.pl.dices.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by mkked on 03.03.2018.
 */

@Root(name = "highScores")
public class Players {

    @ElementList
    private List<Player> players;

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "Players{" +
                "players=" + players +
                '}';
    }
}
