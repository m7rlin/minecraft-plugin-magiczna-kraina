package pl.mgtm.magicznakraina.modules.serduszko;

import org.bukkit.plugin.PluginManager;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.modules.serduszko.commands.KitCommand;


public class SerduszkoModule {

    private MagicznaKraina plugin = MagicznaKraina.getInstance();

    public SerduszkoModule() {
        PluginManager pm = plugin.getServer().getPluginManager();

        // Rejestracja komend]
        plugin.getCommand("kit").setExecutor(new KitCommand());
    }
}
