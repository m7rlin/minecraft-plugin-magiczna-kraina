package pl.mgtm.magicznakraina.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.command.CommandInfo;
import pl.mgtm.magicznakraina.command.PluginCommand;

@CommandInfo(name = "sethome", permission = "", requiresPlayer = true)
public class SetHomeCommand extends PluginCommand {

    private MagicznaKraina pl = MagicznaKraina.getInstance();

    @Override
    public void execute(Player player, String[] args) {
        super.execute(player, args);

        FileConfiguration cofnig = pl.getConfig();

        if (args.length == 0) {
            Location loc = player.getLocation();
            double x = loc.getX();
            double y = loc.getY();
            double z = loc.getZ();
            float pitch = loc.getPitch();
            float yaw = loc.getYaw();
            String worldName = player.getWorld().getName();

            cofnig.set("users." + player.getUniqueId() + ".home.x", x);
            cofnig.set("users." + player.getUniqueId() + ".home.y", y);
            cofnig.set("users." + player.getUniqueId() + ".home.z", z);
            cofnig.set("users." + player.getUniqueId() + ".home.yaw", yaw);
            cofnig.set("users." + player.getUniqueId() + ".home.pitch", pitch);
            cofnig.set("users." + player.getUniqueId() + ".home.world", worldName);

            pl.saveConfig();

            player.sendMessage("§aTwój nowy dom został ustawiony.\n§7Użyj §a/home §7aby przenieść się do domu.");

        } else {
            player.sendMessage(ChatColor.RED + "/sethome");
        }

    }
}
