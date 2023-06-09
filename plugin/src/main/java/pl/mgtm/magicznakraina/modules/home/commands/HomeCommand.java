package pl.mgtm.magicznakraina.modules.home.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.command.CommandInfo;
import pl.mgtm.magicznakraina.command.PluginCommand;
import pl.mgtm.magicznakraina.config.User;

import java.util.HashMap;

@CommandInfo(name = "home", permission = "", requiresPlayer = true)
public class HomeCommand extends PluginCommand {
    private MagicznaKraina pl = MagicznaKraina.getInstance();

    @Override
    public void execute(Player player, String[] args) {
        super.execute(player, args);

        boolean homeEnabled = pl.getMainConfig().getHomeModule().getHomeEnabled();

        if (!homeEnabled) {
            player.sendMessage(ChatColor.RED + "System homów jest aktualnie wyłączony.");
            return;
        }

        HashMap<String, User> users = pl.getUserConfig().getUsers();
        User user = users.get(player.getUniqueId().toString());

        Location playerHome = user.getHome();

        // Check if home is set
        if (playerHome != null) {
            String worldName = playerHome.getWorld().getName();

            if (!player.getWorld().getName().equals(worldName)) {
                player.sendMessage(ChatColor.RED + "Nie możesz teleportować się pomiedzy wymiarami. :(");
                return;
            }

            player.teleport(playerHome);
            player.sendMessage(ChatColor.GREEN + "Przeniesiono do domu.");
        } else {
            player.sendMessage("§cNie posiadasz żadnego ustawionego domu!\nMożesz to zrobić używając komendy §7/sethome");
        }
    }
}
