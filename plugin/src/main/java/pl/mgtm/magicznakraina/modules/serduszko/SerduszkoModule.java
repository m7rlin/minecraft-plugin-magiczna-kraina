package pl.mgtm.magicznakraina.modules.serduszko;

import org.bukkit.plugin.PluginManager;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.modules.serduszko.commands.ReviveCommand;
import pl.mgtm.magicznakraina.modules.serduszko.commands.SerduszkoCommand;
import pl.mgtm.magicznakraina.modules.serduszko.events.DeathEvent;
import pl.mgtm.magicznakraina.modules.serduszko.events.JoinServerEvent;


public class SerduszkoModule {

    private MagicznaKraina plugin = MagicznaKraina.getInstance();

    public SerduszkoModule() {
        PluginManager pm = plugin.getServer().getPluginManager();

        // Rejestracja zdarzeń
        pm.registerEvents(new JoinServerEvent(), plugin);
        pm.registerEvents(new DeathEvent(), plugin);

        // Rejestracja komend
        plugin.getCommand("serduszko").setExecutor(new SerduszkoCommand());
        plugin.getCommand("revive").setExecutor(new ReviveCommand());
    }
}
