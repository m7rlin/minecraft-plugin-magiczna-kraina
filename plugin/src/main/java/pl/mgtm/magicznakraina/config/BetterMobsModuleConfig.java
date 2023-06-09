package pl.mgtm.magicznakraina.config;

import java.io.Serializable;

public class BetterMobsModuleConfig implements Serializable {

    // Module status on/off
    private boolean enabled;



    public BetterMobsModuleConfig() {
    }

    public BetterMobsModuleConfig(boolean moduleEnabled) {
        this.enabled = moduleEnabled;
    }

    public boolean getEnabled() {
        return enabled;
    }
    public void setEnabled(boolean status) {
        enabled = status;
    }


}
