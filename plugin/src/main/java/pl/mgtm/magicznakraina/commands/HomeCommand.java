package pl.mgtm.magicznakraina.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.command.CommandInfo;
import pl.mgtm.magicznakraina.command.PluginCommand;

@CommandInfo(name = "home", permission = "", requiresPlayer = true)
public class HomeCommand extends PluginCommand {
    private MagicznaKraina pl = MagicznaKraina.getInstance();

    @Override
    public void execute(Player player, String[] args) {
        super.execute(player, args);

        FileConfiguration config = pl.getConfig();

        // Sprawdz czy dom jest ustawiony
        if (config.contains("users." + player.getUniqueId() + ".home")) {
            String worldName = config.getString("users." + player.getUniqueId() + ".home.world");

            if (!player.getWorld().getName().equals(worldName)) {
                player.sendMessage(ChatColor.RED + "Nie możesz teleportować się pomiedzy wymiarami. :(");
                return;
            }

            double x = config.getDouble("users." + player.getUniqueId() + ".home.x");
            double y = config.getDouble("users." + player.getUniqueId() + ".home.y");
            double z = config.getDouble("users." + player.getUniqueId() + ".home.z");
            float pitch = (float) config.getDouble("users." + player.getUniqueId() + ".home.pitch");
            float yaw = (float) config.getDouble("users." + player.getUniqueId() + ".home.yaw");

            Location location = new Location(player.getWorld(), x, y, z, yaw, pitch);

            player.teleport(location);
            player.sendMessage(ChatColor.GREEN + "Przeniesiono do domu.");
        } else {
            player.sendMessage("§cNie posiadasz żadnego ustawionego domu!\nMożesz to zrobić używając komendy §7/sethome");
        }
    }
}
