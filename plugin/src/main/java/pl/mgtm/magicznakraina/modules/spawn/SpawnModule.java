package pl.mgtm.magicznakraina.modules.spawn;

import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.module.ModuleInfo;
import pl.mgtm.magicznakraina.module.PluginModule;
import pl.mgtm.magicznakraina.modules.spawn.commands.SetSpawnCommand;
import pl.mgtm.magicznakraina.modules.spawn.commands.SpawnCommand;
import pl.mgtm.magicznakraina.modules.spawn.events.RespawnEvent;

@ModuleInfo(name = "spawn")
public class SpawnModule extends PluginModule {

    private final MagicznaKraina pl = MagicznaKraina.getInstance();


    public SpawnModule() {
        super();

        // Register commands
        super.registerCommand(new SpawnCommand());
        super.registerCommand(new SetSpawnCommand());

        // Register events
        super.registerEvents(new RespawnEvent());

    }
}
