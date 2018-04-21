package mkedron.vistula.pl.dices.dao;

import android.support.annotation.NonNull;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import java.util.Scanner;

import mkedron.vistula.pl.dices.DicesApplication;
import mkedron.vistula.pl.dices.model.Player;
import mkedron.vistula.pl.dices.model.Players;

/**
 * Created by mkked on 03.03.2018.
 */

public class PlayerDao {

    private Serializer serializer;
    private static final String DATA_FILE = "players.xml";
    private static PlayerDao instance = new PlayerDao();

    public static PlayerDao getInstance() {
        return instance;
    }

    private PlayerDao() {
        prepareDatasource();
        if(!getFile().exists()) {
            createDefaultFile();
        }
    }

    private void createDefaultFile() {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream is = classLoader.getResourceAsStream(DATA_FILE);
            OutputStream os = getOutputStreamToFile();
            byte[] buffer = new byte[1024];
            try {
                is.read(buffer);
                os.write(buffer);
                os.flush();
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void clearResults() {
        if(getFile().exists()) {
            getFile().delete();
        }
        createDefaultFile();
    }

    private void prepareDatasource() {
        prepareSerializer();
    }

    public Player getPlayer(final String name) {
        List<Player> playersList = parsePlayersFromReader().getPlayers();
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
        Players players = parsePlayersFromReader();
        players.addPlayer(player);
        parsePlayersToResource(players);

    }

    public List<Player> getPlayers(Integer playerNumber) {
        return parsePlayersFromReader().getPlayers();
    }

    private Players parsePlayersFromReader() {
        Players players = null;
        try {
            Reader reader = getReader();
            players = serializer.read(Players.class, reader, false);
        } catch (Exception e) {
            e.getStackTrace();
        }

        return players;

    }

    private void parsePlayersToResource(Players players) {
        try {
            System.out.println("savePlayers : "+players.toString());
            OutputStream os = getOutputStreamToFile();
            serializer.write(players,getOutputStreamToFile());

            os.flush();
            os.close();
            getXmlDataString();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Reader getReader() {
        String xmlData = getXmlDataString();
        Reader reader = new StringReader(xmlData);
        return reader;
    }

    private String getXmlDataString() {
        String xmlPlayersData = "";
        Scanner scanner = getScanner();
        while(scanner.hasNext()) {
            xmlPlayersData += scanner.next();
        }
        System.out.println("xmlData : "+xmlPlayersData);
        return xmlPlayersData;
    }

    private Scanner getScanner() {
        InputStream inputStream = getInputStreamFromFile();
        Scanner scanner = new Scanner(inputStream);
        return scanner;
    }

    private void prepareSerializer() {
        serializer = new Persister();

    }

    private InputStream getInputStreamFromFile() {

        File file = getFile();
        try {
            InputStream is = new FileInputStream(file);
            return is;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private OutputStream getOutputStreamToFile() {
        File file = getFile();
        try {
            return new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }

    @NonNull
    private File getFile() {
        return new File(DicesApplication.getContext().getFilesDir(), DATA_FILE);
    }

}
