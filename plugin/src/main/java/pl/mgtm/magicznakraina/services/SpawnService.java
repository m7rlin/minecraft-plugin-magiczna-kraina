package pl.mgtm.magicznakraina.services;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import pl.mgtm.magicznakraina.MagicznaKraina;

public class SpawnService {
    private MagicznaKraina plugin = MagicznaKraina.getInstance();
    private FileConfiguration config = plugin.getConfig();

    private Location spawnLocation;

    public void loadSpawnLocation() {
        if (config.contains("spawn.x")) {
            String worldName = config.getString("spawn.world");

            double x = config.getDouble("spawn.x");
            double y = config.getDouble("spawn.y");
            double z = config.getDouble("spawn.z");

            float pitch = (float) config.getDouble("spawn.pitch");
            float yaw = (float) config.getDouble("spawn.yaw");

            this.spawnLocation = new Location(plugin.getServer().getWorld(worldName), x, y, z, yaw, pitch);
        }
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }

    public void setSpawnLocation(Location spawnLocation) {
        this.spawnLocation = spawnLocation;
    }

    public void setSpawnLocation(World world, double x, double y, double z, float yaw, float pitch) {
        this.spawnLocation = new Location(world, x, y, z, yaw, pitch);
    }
}
