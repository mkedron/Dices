package mkedron.vistula.pl.dices.dao;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import mkedron.vistula.pl.dices.model.Player;
import mkedron.vistula.pl.dices.model.Players;

/**
 * Created by mkked on 03.03.2018.
 */

public class PlayerDao {
    public Player getPlayer(String name) {
        return null;
    }

    public void savePlayer(Player player) {

    }

    public List<Player> getPlayers(Integer playerNumber) {

        String xmlPlayersData = "";
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("players.xml");
        Scanner scanner = new Scanner(inputStream);
        while(scanner.hasNext()) {
            xmlPlayersData += scanner.next();
        }
        Serializer serializer = new Persister();
        Reader reader = new StringReader(xmlPlayersData);
        try {
            Players players = serializer.read(Players.class,reader,false);
            System.out.println(players);
            return players.getPlayers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }
}
