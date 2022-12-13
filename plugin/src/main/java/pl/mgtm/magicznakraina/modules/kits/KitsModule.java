package pl.mgtm.magicznakraina.modules.kits;

import org.bukkit.plugin.PluginManager;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.modules.kits.commands.KitCommand;


public class KitsModule {

    private MagicznaKraina plugin = MagicznaKraina.getInstance();

    public KitsModule() {
        PluginManager pm = plugin.getServer().getPluginManager();

        // Rejestracja komend]
        plugin.getCommand("kit").setExecutor(new KitCommand());

        if (!plugin.getConfig().contains("kits")) return;



    }
}
