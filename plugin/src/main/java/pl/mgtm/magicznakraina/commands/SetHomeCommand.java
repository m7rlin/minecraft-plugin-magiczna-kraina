package pl.mgtm.magicznakraina.commands;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.command.CommandInfo;
import pl.mgtm.magicznakraina.command.PluginCommand;

@CommandInfo(name = "sethome", permission = "", requiresPlayer = true)
public class SetHomeCommand extends PluginCommand {

    private MagicznaKraina plugin = MagicznaKraina.getInstance();

    @Override
    public void execute(Player player, String[] args) {
        super.execute(player, args);

        FileConfiguration config = plugin.getConfig();

        Location loc = player.getLocation();
        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();
        float pitch = loc.getPitch();
        float yaw = loc.getYaw();
        String worldName = player.getWorld().getName();

        config.set("users." + player.getUniqueId() + ".home.x", x);
        config.set("users." + player.getUniqueId() + ".home.y", y);
        config.set("users." + player.getUniqueId() + ".home.z", z);
        config.set("users." + player.getUniqueId() + ".home.yaw", yaw);
        config.set("users." + player.getUniqueId() + ".home.pitch", pitch);
        config.set("users." + player.getUniqueId() + ".home.world", worldName);

        plugin.saveConfig();

        player.sendMessage("§aTwój nowy dom został ustawiony.\n§7Użyj §a/home §7aby przenieść się do domu.");
    }
}
