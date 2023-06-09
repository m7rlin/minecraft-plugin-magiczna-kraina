package pl.mgtm.magicznakraina.config;

import org.bukkit.Location;
import pl.mgtm.magicznakraina.api.config.annotation.ConfigOptional;

import java.io.Serializable;

public class SpawnModuleConfig implements Serializable {

    // Module status on/off
    private boolean enabled;
    @ConfigOptional
    private Location spawn;

    private boolean spawnEnabled = true;

    private boolean teleportOnRespawn = true;


    public SpawnModuleConfig() {
    }

    public SpawnModuleConfig(boolean moduleEnabled) {
        this.enabled = moduleEnabled;
    }

    public boolean getEnabled() {
        return enabled;
    }
    public void setEnabled(boolean status) {
        enabled = status;
    }

    public boolean getTeleportOnRespawn() {
        return teleportOnRespawn;
    }
    public void setTeleportOnRespawn(boolean status) {
        teleportOnRespawn = status;
    }

    public boolean getSpawnEnabled() {
        return spawnEnabled;
    }
    public void setSpawnEnabled(boolean status) {
        spawnEnabled = status;
    }

    public void setSpawn(Location location) {
        spawn = location;
    }

    public Location getSpawn() {
        return spawn;
    }
}
