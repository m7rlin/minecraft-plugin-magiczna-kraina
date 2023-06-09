package pl.mgtm.magicznakraina.config;

import java.io.Serializable;

public class KitsModuleConfig implements Serializable {

    // Module status on/off
    private boolean enabled;


    public KitsModuleConfig() {
    }

    public KitsModuleConfig(boolean moduleEnabled) {
        enabled = moduleEnabled;
    }

    public boolean getEnabled() {
        return enabled;
    }
    public void setEnabled(boolean status) {
        enabled = status;
    }


}
