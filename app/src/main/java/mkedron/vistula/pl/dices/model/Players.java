package mkedron.vistula.pl.dices.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by mkked on 03.03.2018.
 */


@Root(name = "highScores")
public class Players {

    @ElementList(name="players", inline = true)
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

    public void addPlayer(Player player) {
        if(players == null) {
            players = new ArrayList<Player>();
        }
        player.setId(String.valueOf(getMaxId()+1));
        players.add(player);
    }

    private Integer getMaxId() {
        Integer maxId = 0;
        for(Player player : players) {
            Integer tmpId = Integer.valueOf(player.getId());
            if(tmpId > maxId) {
                maxId = tmpId;
            }
        }
        return maxId;
    }
}
