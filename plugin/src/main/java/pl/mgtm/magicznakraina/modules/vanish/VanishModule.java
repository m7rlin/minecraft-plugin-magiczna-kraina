package pl.mgtm.magicznakraina.modules.vanish;

import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.module.ModuleInfo;
import pl.mgtm.magicznakraina.module.PluginModule;
import pl.mgtm.magicznakraina.modules.vanish.commands.VanishCommand;
import pl.mgtm.magicznakraina.modules.vanish.events.JoinLeaveEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ModuleInfo(name = "vanish")
public class VanishModule extends PluginModule {

    private final MagicznaKraina pl = MagicznaKraina.getInstance();
    private static Map<UUID, Boolean> vanishedPlayers = new HashMap<>();


    public VanishModule() {
        super();

        // Register commands
        super.registerCommand(new VanishCommand());

        // Register events
        super.registerEvents(new JoinLeaveEvent());
    }

    public static Map<UUID, Boolean> getVanishedPlayers() {
        return vanishedPlayers;
    }


}
