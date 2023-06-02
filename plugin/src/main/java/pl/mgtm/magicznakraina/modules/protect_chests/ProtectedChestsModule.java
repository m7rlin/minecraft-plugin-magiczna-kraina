package pl.mgtm.magicznakraina.modules.protect_chests;

import org.bukkit.plugin.PluginManager;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.modules.protect_chests.commands.LockCommand;
import pl.mgtm.magicznakraina.modules.protect_chests.events.BreakChest;
import pl.mgtm.magicznakraina.modules.protect_chests.events.OpenChest;
import pl.mgtm.magicznakraina.modules.protect_chests.events.PlaceChest;

public class ProtectedChestsModule {

    private final MagicznaKraina pl = MagicznaKraina.getInstance();

    public ProtectedChestsModule() {
        PluginManager pm = pl.getServer().getPluginManager();

        // Register commands
        pl.getCommand("lock").setExecutor(new LockCommand());

        // Register events
        pm.registerEvents(new OpenChest(), pl);
        pm.registerEvents(new PlaceChest(), pl);
        pm.registerEvents(new BreakChest(), pl);
    }
}
