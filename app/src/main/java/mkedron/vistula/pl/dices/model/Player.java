package mkedron.vistula.pl.dices.model;

import android.support.annotation.NonNull;

import org.simpleframework.xml.Element;

/**
 * Created by mkked on 03.03.2018.
 */
@Element(name = "player")
public class Player implements Comparable<Player>{

    @Element
    private String id;

    @Element
    private String name;

    @Element
    private Integer score;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return name != null ? name.equals(player.name) : player.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public String getResultString() {
        String format = "ID : %s %s - %s";
        return String.format(format,id, name,score);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    @Override
    public int compareTo(@NonNull Player o) {
        return score.compareTo(o.getScore());
    }
}
