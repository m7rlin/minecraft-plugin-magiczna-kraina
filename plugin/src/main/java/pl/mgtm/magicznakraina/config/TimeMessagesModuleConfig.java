package pl.mgtm.magicznakraina.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TimeMessagesModuleConfig implements Serializable {

    // Module status on/off
    private boolean enabled;
    // Can players be revived

    private String prefix = "";
    private String suffix = "";

    private List<TimeMessage> messages;


    public TimeMessagesModuleConfig() {
    }

    public TimeMessagesModuleConfig(boolean moduleEnabled) {
        this.enabled = moduleEnabled;

        List<TimeMessage> timeMessages = new ArrayList<>();

        List<String> messages = new ArrayList<>();
        messages.add("hi ok");

        TimeMessage timeMessage = new TimeMessage(messages, 2, 0);
        timeMessages.add(timeMessage);

        this.messages = timeMessages;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean status) {
        enabled = status;
    }


    public List<TimeMessage> getMessages() {
        return messages;
    }

    public String getPrefix() {return prefix;}
    public String getSuffix() {return suffix;}
    public void setSuffix(String suffix) {this.suffix = suffix;}
    public void setPrefix(String prefix) {this.prefix = prefix;}
}
