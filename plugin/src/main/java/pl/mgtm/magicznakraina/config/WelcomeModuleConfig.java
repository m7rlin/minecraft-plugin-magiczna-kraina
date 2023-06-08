package pl.mgtm.magicznakraina.config;

import pl.mgtm.magicznakraina.api.config.annotation.Comment;

import java.io.Serializable;

public class WelcomeModuleConfig implements Serializable {

    private String joinMessage = "<gray>[<green>+<gray>] <user>";
    private String leaveMessage = "<gray>[<red>-<gray>] <user>";

    public WelcomeModuleConfig() {

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
