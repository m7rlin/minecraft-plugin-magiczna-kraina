package pl.mgtm.magicznakraina.config;

import org.bukkit.Location;
import pl.mgtm.magicznakraina.api.config.annotation.Comment;
import pl.mgtm.magicznakraina.api.config.annotation.ConfigOptional;

import java.io.Serializable;
import java.util.HashMap;

public class User implements Serializable {
    private transient String username;
    private double hearts = 6.0;
    private int heartsLevel = 0;

    @ConfigOptional
    private boolean bannedOnZeroHearts = false;

    @ConfigOptional
    private Location home;


    private boolean homeUnlocked = false;

    @ConfigOptional
    private boolean enderchestUnlocked = false;

    @ConfigOptional
    private HashMap<String, UserKitConfig> kits;

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

    public void setEnderchestUnlocked(boolean enderchestUnlocked) {
        this.enderchestUnlocked = enderchestUnlocked;
    }

    public boolean getEnderchestUnlocked() {
        return this.enderchestUnlocked;
    }

    public void setBannedOnZeroHearts(boolean status) {
        this.bannedOnZeroHearts = status;
    }

    public boolean getBannedOnZeroHearts() {
        return this.bannedOnZeroHearts;
    }

    public HashMap<String, UserKitConfig> getUserKits() { return kits; }
    public void setUserKits(HashMap<String, UserKitConfig> kits) { this.kits = kits; }
    public void setUserKit(String kitname,  UserKitConfig kit) {
        if (kits == null) kits = new HashMap<>();
        kits.put(kitname,kit);
    }

    public boolean hasKit(String kitname) {
        if (kits != null && kits.get(kitname) != null) return true;
        return false;
    }

    public UserKitConfig getUserKit(String kitname) {
        if (kits == null) return null;
        return kits.get(kitname);
    }

}


