package pl.mgtm.magicznakraina.modules.home.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.command.CommandInfo;
import pl.mgtm.magicznakraina.command.PluginCommand;
import pl.mgtm.magicznakraina.config.User;

import java.util.HashMap;

@CommandInfo(name = "sethome", permission = "", requiresPlayer = true)
public class SetHomeCommand extends PluginCommand {

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

        // Set players home location
        user.setHome(player.getLocation());

        // Save config
        pl.getUserConfig().setUsers(users);

        player.sendMessage("§aTwój nowy dom został ustawiony.\n§7Użyj §a/home §7aby przenieść się do domu.");
    }
}
