package pl.mgtm.magicznakraina.commands;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.command.CommandInfo;
import pl.mgtm.magicznakraina.command.PluginCommand;

@CommandInfo(name = "setspawn", permission = "mgtm.setspawn", requiresPlayer = true)
public class SetSpawnCommand extends PluginCommand {

    private MagicznaKraina pl = MagicznaKraina.getInstance();

    @Override
    public void execute(Player player, String[] args) {
        super.execute(player, args);

        FileConfiguration config = pl.getConfig();

        if (args.length == 0) {
            Location loc = player.getLocation();
            double x = loc.getX();
            double y = loc.getY();
            double z = loc.getZ();
            float pitch = loc.getPitch();
            float yaw = loc.getYaw();
            String worldName = loc.getWorld().getName();

            config.set("spawn.x", x);
            config.set("spawn.y", y);
            config.set("spawn.z", z);
            config.set("spawn.yaw", yaw);
            config.set("spawn.pitch", pitch);
            config.set("spawn.world", worldName);

            pl.saveConfig();

            // Ustaw zmienna spawnLocation
            pl.setSpawnLocation(loc);

            player.sendMessage("§aNowy spawn serwera ustawiony!\n" + (config.getBoolean("spawn.enabled", false) ? "§7Użyj §a/spawn §7aby przenieść się na spawn serwera." : "§cSpawn serwera jest wyłączony!"));

        } else {
            player.sendMessage("§c/setspawn");
        }

    }

}
