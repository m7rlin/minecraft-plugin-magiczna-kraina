package pl.mgtm.magicznakraina.config;

import java.io.Serializable;
import java.util.List;

public class TimeMessage implements Serializable {

    private boolean enabled = false;
    private int interval;

    private List<String> content;

    public TimeMessage() {
    }

    public TimeMessage(int interval, List<String> content) {
        this.interval = interval;
        this.content = content;
    }

    public void setEnabled(boolean status) {
        enabled = status;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setInterval(int status) {
        interval = status;
    }

    public int getInterval() {
        return interval;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    public List<String> getContent() {
        return content;
    }
}
