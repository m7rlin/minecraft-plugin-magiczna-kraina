package pl.mgtm.magicznakraina.commands;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.command.CommandInfo;
import pl.mgtm.magicznakraina.command.PluginCommand;

@CommandInfo(name = "kit", permission = "", requiresPlayer = true)
public class KitCommand extends PluginCommand {
    private MagicznaKraina plugin = MagicznaKraina.getInstance();

    @Override
    public void execute(Player player, String[] args) {
        super.execute(player, args);

        FileConfiguration config = plugin.getConfig();

        if (args.length != 0) {
            player.sendMessage(ChatColor.RED + "Work in progress!");
        } else {
            player.sendMessage(ChatColor.RED + "/kit <nazwa zestawu>");
        }
    }


}
