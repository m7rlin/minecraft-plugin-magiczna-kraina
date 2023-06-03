package pl.mgtm.magicznakraina.modules.vanish;

import org.bukkit.plugin.PluginManager;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.modules.vanish.commands.VanishCommand;
import pl.mgtm.magicznakraina.modules.vanish.events.JoinLeaveEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class VanishModule {

    private final MagicznaKraina pl = MagicznaKraina.getInstance();
    private static Map<UUID, Boolean> vanishedPlayers = new HashMap<>();


    public VanishModule() {
        PluginManager pm = pl.getServer().getPluginManager();

        // Register commands
        pl.getCommand("vanish").setExecutor(new VanishCommand());

        // Register events
        pm.registerEvents(new JoinLeaveEvent(), pl);
    }

    public static Map<UUID, Boolean> getVanishedPlayers() {
        return vanishedPlayers;
    }


}
