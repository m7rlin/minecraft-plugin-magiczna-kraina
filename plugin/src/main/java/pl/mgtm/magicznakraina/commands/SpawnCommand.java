package pl.mgtm.magicznakraina.commands;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.command.CommandInfo;
import pl.mgtm.magicznakraina.command.PluginCommand;

@CommandInfo(name = "spawn", permission = "", requiresPlayer = true)
public class SpawnCommand extends PluginCommand {

    private MagicznaKraina pl = MagicznaKraina.getInstance();

    @Override
    public void execute(Player player, String[] args) {
        super.execute(player, args);

        FileConfiguration config = pl.getConfig();

        if (args.length == 0) {
            // Sprawdz czy dom jest ustawiony
            if (config.contains("spawn.x") && config.getBoolean("spawn.enabled", false)) {
                player.teleport(pl.getSpawnLocation());
                player.sendMessage(ChatColor.GREEN + "Przeniesiono na spawn serwera.");
            } else {
                player.sendMessage(ChatColor.RED + "Wybacz, ale aktualnie nie ma aktywnego spawnu, do którego możesz się przenieść.");
            }
        } else {
            player.sendMessage(ChatColor.RED + "/spawn");
        }
    }

}
