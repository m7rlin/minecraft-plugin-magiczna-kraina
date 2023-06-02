package pl.mgtm.magicznakraina.modules.kits;

import org.bukkit.plugin.PluginManager;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.modules.kits.commands.KitCommand;


public class KitsModule {

    private final MagicznaKraina pl = MagicznaKraina.getInstance();

    public KitsModule() {
        PluginManager pm = pl.getServer().getPluginManager();

        // Register commands
        pl.getCommand("kit").setExecutor(new KitCommand());
    }
}
