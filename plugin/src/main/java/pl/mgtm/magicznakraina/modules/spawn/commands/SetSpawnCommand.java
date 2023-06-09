package pl.mgtm.magicznakraina.modules.spawn.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.command.CommandInfo;
import pl.mgtm.magicznakraina.command.PluginCommand;
import pl.mgtm.magicznakraina.config.SpawnModuleConfig;

@CommandInfo(name = "setspawn", permission = "mgtm.setspawn", requiresPlayer = true)
public class SetSpawnCommand extends PluginCommand {
    private MagicznaKraina pl = MagicznaKraina.getInstance();

    @Override
    public void execute(Player player, String[] args) {
        super.execute(player, args);

        SpawnModuleConfig config = pl.getMainConfig().getSpawnModule();

        if (!config.getSpawnEnabled()) {
            player.sendMessage(ChatColor.RED + "Spawn serwera jest wyłączony!");
            return;
        }

        Location location = player.getLocation();

        // Set spawn in config
        config.setSpawn(location);
        // Save spawn to config
        pl.getMainConfig().setSpawnModule(config);

        player.sendMessage(ChatColor.GREEN + "Nowy spawn serwera ustawiony!\n");
        player.sendMessage("§7Użyj §a/spawn §7aby przenieść się na spawn serwera.");
    }
}
