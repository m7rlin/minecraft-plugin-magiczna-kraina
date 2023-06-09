package pl.mgtm.magicznakraina.config;

import java.io.Serializable;

public class UserKitConfig implements Serializable {

    private transient String uuid;

    private int useCount = 0;


    public UserKitConfig() {
    }

    public UserKitConfig(String uuid) {
        this.uuid = uuid;
    }


    public int getUseCount() {
        return useCount;
    }

    public void setUseCount(int useCount) {
        this.useCount = useCount;
    }

    public void addUseCount(int amount) {
        this.useCount += amount;
    }

    public void addUseCount() {
        this.useCount += 1;
    }

    public void removeUseCount(int amount) {
        this.useCount -= amount;
    }

    public void removeUseCount() {
        this.useCount -= 1;
    }

    ;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}
