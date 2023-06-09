package pl.mgtm.magicznakraina.config;

import pl.mgtm.magicznakraina.api.config.annotation.Comment;

import java.io.Serializable;

public class CleverSleepModuleConfig implements Serializable {

    // Module status on/off
    private boolean enabled;
    private int playersInBedPercentage;


    public CleverSleepModuleConfig() {
    }

    public CleverSleepModuleConfig(boolean moduleEnabled, int playersInBedPercentage) {
        this.enabled = moduleEnabled;
        this.playersInBedPercentage = playersInBedPercentage;
    }

    public boolean getEnabled() {
        return enabled;
    }


    public void setPlayersInBedPercentage(int playersInBedPercentage) {
        this.playersInBedPercentage = playersInBedPercentage;
    }

    @Comment("How many percent people of the server must be in bed in order to sleep.")
    public int getPlayersInBedPercentage() {
        return playersInBedPercentage;
    }
}
