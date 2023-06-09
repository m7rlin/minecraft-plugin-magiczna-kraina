package pl.mgtm.magicznakraina.config;

import org.bukkit.Location;
import pl.mgtm.magicznakraina.api.config.annotation.Comment;
import pl.mgtm.magicznakraina.api.config.annotation.ConfigOptional;

import java.io.Serializable;

public class User implements Serializable {
    private transient String username;
    private double hearts = 6.0;
    private int heartsLevel = 0;

    @ConfigOptional
    private boolean bannedOnZeroHearts = false;

    @ConfigOptional
    private Location home;


    private boolean homeUnlocked = false;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public void setHearts(double hearts) {
        this.hearts = hearts;
    }

    public void addHearts(double hearts) {
        this.hearts += hearts;
    }
    public void removeHearts(double hearts) {
        this.hearts -= hearts;
    }

    @Comment("Players hp.")
    public double getHearts() {
        return this.hearts;
    }

    public int getHeartsLevel() {
        return this.heartsLevel;
    }

    public void setHeartsLevel(int heartsLevel) {
        this.heartsLevel = heartsLevel;
    }

    public void addHeartsLevel(int level) {
        this.heartsLevel += level;
    }


    public void setHome(Location location) {
        this.home = location;
    }

    public Location getHome() {
        return this.home;
    }

    public void setBannedOnZeroHearts(boolean status) {
        this.bannedOnZeroHearts = status;
    }

    public boolean getBannedOnZeroHearts() {
        return this.bannedOnZeroHearts;
    }

}


