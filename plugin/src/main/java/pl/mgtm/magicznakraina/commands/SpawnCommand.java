package pl.mgtm.magicznakraina.commands;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.templates.CommandInfo;
import pl.mgtm.magicznakraina.templates.PluginCommand;

@CommandInfo(name = "spawn", permission = "", requiresPlayer = true)
public class SpawnCommand extends PluginCommand {
    private final MagicznaKraina plugin = MagicznaKraina.getInstance();

    @Override
    public void execute(Player player, String[] args) {
        super.execute(player, args);
        FileConfiguration config = plugin.getConfig();

        if (config.contains("spawn.x") && config.getBoolean("spawn.enabled", false)) {
            player.teleport(plugin.spawnService.getSpawnLocation());
            player.sendMessage(ChatColor.GREEN + "Przeteleportowano na spawn serwera.");
        } else {
            player.sendMessage(ChatColor.RED + "Wybacz, ale aktualnie nie ma aktywnego spawnu, do którego możesz się przenieść.");
        }
    }
}
