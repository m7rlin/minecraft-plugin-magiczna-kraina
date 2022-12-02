package pl.mgtm.magicznakraina.modules.protect_chests.commands;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.command.CommandInfo;
import pl.mgtm.magicznakraina.command.PluginCommand;

@CommandInfo(name = "lock", permission = "", requiresPlayer = true)
public class LockCommand extends PluginCommand {

    private MagicznaKraina pl = MagicznaKraina.getInstance();

    @Override
    public void execute(Player player, String[] args) {
        super.execute(player, args);

        FileConfiguration config = pl.getConfig();

        if (args.length == 0) {


            player.sendMessage(ChatColor.GREEN + "Blokowanie skrzynki...");

        } else {
            player.sendMessage(ChatColor.RED + "/lock");
        }
    }
}
