package pl.mgtm.magicznakraina.modules.spawn.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.command.CommandInfo;
import pl.mgtm.magicznakraina.command.PluginCommand;
import pl.mgtm.magicznakraina.config.SpawnModuleConfig;

@CommandInfo(name = "spawn", permission = "", requiresPlayer = true)
public class SpawnCommand extends PluginCommand {
    private MagicznaKraina pl = MagicznaKraina.getInstance();

    @Override
    public void execute(Player player, String[] args) {
        super.execute(player, args);

        SpawnModuleConfig config = pl.getMainConfig().getSpawnModule();

        // Sprawdzamy, czy spawn jest ustawiony
        if (config.getSpawnEnabled() && config.getSpawn() != null) {

            player.teleport(config.getSpawn());
            player.sendMessage(ChatColor.GREEN + "Przeteleportowano na spawn serwera.");
        } else {
            player.sendMessage(ChatColor.RED + "Wybacz, ale aktualnie nie ma aktywnego spawnu, do którego możesz się przenieść.");
        }
    }

}
