package pl.mgtm.magicznakraina.config;

import org.bukkit.Location;
import pl.mgtm.magicznakraina.api.config.annotation.Comment;
import pl.mgtm.magicznakraina.api.config.annotation.ConfigOptional;

import javax.annotation.Nullable;
import java.io.Serializable;

public class User implements Serializable {
    private transient String username;
    private double hearts = 6.0;
    private int heartsLevel = 0;

//    @ConfigOptional
//    private Location home;

    private boolean homeUnlocked = false;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public void setHearts(double hearts) {
        this.hearts = hearts;
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


//    public void setHome(Location location) {
//        this.home = location;
//    }
//
//    public Location getHome() {
//        return this.home;
//    }

}


