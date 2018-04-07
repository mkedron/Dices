package mkedron.vistula.pl.dices.dao;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import mkedron.vistula.pl.dices.model.Player;
import mkedron.vistula.pl.dices.model.Players;

/**
 * Created by mkked on 03.03.2018.
 */

public class PlayerDao {

    private Serializer serializer;
    private Reader reader;
    private InputStream inputStream;
    private String DATA_FILE;
    private List<Player> playersList;
    private Scanner scanner;
    private String xmlPlayersData;
    private static PlayerDao instance = new PlayerDao();

    private PlayerDao() {
        prepareDatasource();
        readXmlDataFromScanner();
    }

    public static PlayerDao getInstance() {
        return instance;
    }

    private void prepareDatasource() {
        setDataFileName();
        prepareInputStreamFromFile();
        prepareScanner();
        prepareSerializer();
    }

    public Player getPlayer(final String name) {
        parsePlayersFromReader();
        Player player = null;
        for(Player p : playersList) {
            if(p.getName().equals(name)) {
                player = p;
            }
        }
        if(player != null) {
            return player;
        }
        return null;

    }

    public void savePlayer(Player player) {


    }

    public List<Player> getPlayers(Integer playerNumber) {
        parsePlayersFromReader();
        return playersList;
    }

    private void prepareReader() {
        reader = new StringReader(xmlPlayersData);
    }

    private void prepareSerializer() {
        serializer = new Persister();
    }

    private void prepareScanner() {
        scanner = new Scanner(inputStream);
    }

    private void prepareInputStreamFromFile() {
        inputStream = getInputStreamFromFile();
    }

    private InputStream getInputStreamFromFile() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return classLoader.getResourceAsStream(DATA_FILE);
    }

    private void setDataFileName() {
        DATA_FILE = "players.xml";
    }

    private void parsePlayersFromReader() {
        playersList = Collections.EMPTY_LIST;
        try {
            prepareReader();
            Players players = serializer.read(Players.class, reader, false);
            playersList = players.getPlayers();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void readXmlDataFromScanner() {
        String xmlPlayersData = "";
        while(scanner.hasNext()) {
            xmlPlayersData += scanner.next();
        }
        this.xmlPlayersData = xmlPlayersData;
    }
}
