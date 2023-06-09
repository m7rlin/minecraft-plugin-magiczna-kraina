package pl.mgtm.magicznakraina.config;

import pl.mgtm.magicznakraina.api.config.annotation.Comment;

import java.io.Serializable;

public class WelcomeModuleConfig implements Serializable {

    // Module status on/off
    private boolean enabled;

    private String joinMessage = "<gray>[<green>+<gray>] <user>";
    private String leaveMessage = "<gray>[<red>-<gray>] <user>";

    public WelcomeModuleConfig() {}
    public WelcomeModuleConfig(boolean moduleEnabled) { enabled = moduleEnabled; }

    public boolean getEnabled() {
        return enabled;
    }
    public void setEnabled(boolean status) {
        enabled = status;
    }

    @Comment("Player joins the server.")
    public String getJoinMessage() {
        return this.joinMessage;
    }

    @Comment("Player leaves the server.")
    public String getLeaveMessage() {
        return this.leaveMessage;
    }
}
