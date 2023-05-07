package pl.mgtm.magicznakraina.modules.kits;

import org.bukkit.plugin.PluginManager;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.modules.kits.commands.KitCommand;


public class KitsModule {
    private final MagicznaKraina plugin = MagicznaKraina.getInstance();

    public KitsModule() {
        PluginManager pm = plugin.getServer().getPluginManager();

        plugin.getCommand("kit").setExecutor(new KitCommand());
    }
}
