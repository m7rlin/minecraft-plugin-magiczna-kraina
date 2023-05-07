package pl.mgtm.magicznakraina.modules.protect_chests;

import org.bukkit.plugin.PluginManager;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.modules.protect_chests.commands.LockCommand;
import pl.mgtm.magicznakraina.modules.protect_chests.events.BreakChest;
import pl.mgtm.magicznakraina.modules.protect_chests.events.OpenChest;
import pl.mgtm.magicznakraina.modules.protect_chests.events.PlaceChest;

public class ProtectedChestsModule {

    private MagicznaKraina plugin = MagicznaKraina.getInstance();

    public ProtectedChestsModule() {
        PluginManager pm = plugin.getServer().getPluginManager();

        plugin.getCommand("lock").setExecutor(new LockCommand());

        pm.registerEvents(new OpenChest(), plugin);
        pm.registerEvents(new PlaceChest(), plugin);
        pm.registerEvents(new BreakChest(), plugin);
    }
}
