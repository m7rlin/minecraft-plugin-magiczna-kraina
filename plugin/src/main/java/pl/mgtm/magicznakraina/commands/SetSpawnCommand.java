package pl.mgtm.magicznakraina.commands;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.templates.CommandInfo;
import pl.mgtm.magicznakraina.templates.PluginCommand;

// TODO: Change § (color char) for coloring to ChatColor from org.bukkit
@CommandInfo(name = "setspawn", permission = "mgtm.setspawn", requiresPlayer = true)
public class SetSpawnCommand extends PluginCommand {
    private final MagicznaKraina plugin = MagicznaKraina.getInstance();

    @Override
    public void execute(Player player, String[] args) {
        super.execute(player, args);

        FileConfiguration config = plugin.getConfig();

        if (!config.getBoolean("spawn.enabled", false)) {
            player.sendMessage("§cSpawn serwera jest wyłączony!");
            return;
        }

        Location location = player.getLocation();
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        float pitch = location.getPitch();
        float yaw = location.getYaw();
        String worldName = location.getWorld().getName();

        config.set("spawn.x", x);
        config.set("spawn.y", y);
        config.set("spawn.z", z);
        config.set("spawn.yaw", yaw);
        config.set("spawn.pitch", pitch);
        config.set("spawn.world", worldName);

        plugin.saveConfig();

        plugin.spawnService.setSpawnLocation(location);

        player.sendMessage("§aNowy spawn serwera ustawiony!\n");
        player.sendMessage("§7Użyj §a/spawn §7aby przenieść się na spawn serwera.");
    }
}
