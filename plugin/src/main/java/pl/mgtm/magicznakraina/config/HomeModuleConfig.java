package pl.mgtm.magicznakraina.config;

import java.io.Serializable;

public class HomeModuleConfig implements Serializable {

    // Module status on/off
    private boolean enabled;

    private boolean homeEnabled = true;


    public HomeModuleConfig() {
    }

    public HomeModuleConfig(boolean moduleEnabled) {
        this.enabled = moduleEnabled;
    }

    public boolean getEnabled() {
        return enabled;
    }
    public void setEnabled(boolean status) {
        enabled = status;
    }


    public boolean getHomeEnabled() {
        return homeEnabled;
    }
    public void setHomeEnabled(boolean status) {
        homeEnabled = status;
    }

}
