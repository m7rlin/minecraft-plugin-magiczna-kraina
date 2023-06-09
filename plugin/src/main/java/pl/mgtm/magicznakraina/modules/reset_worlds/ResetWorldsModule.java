package pl.mgtm.magicznakraina.modules.reset_worlds;

import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.module.ModuleInfo;
import pl.mgtm.magicznakraina.module.PluginModule;
import pl.mgtm.magicznakraina.modules.reset_worlds.commands.ResetWorldsCommand;
import pl.mgtm.magicznakraina.modules.reset_worlds.events.PortalEvent;

@ModuleInfo(name = "reset_worlds")
public class ResetWorldsModule extends PluginModule {

    private final MagicznaKraina pl = MagicznaKraina.getInstance();

    private boolean netherDisabled = false;
    private boolean endDisabled = false;


    public ResetWorldsModule() {
        super();

        super.registerCommand(new ResetWorldsCommand(this));

        // Register events
        super.registerEvents(new PortalEvent(this));

    }

    public boolean getNetherDisabled() { return netherDisabled; }
    public boolean getEndDisabled() { return endDisabled; }

    public void setNetherDisabled(boolean value) {  netherDisabled = value; }
    public void setEndDisabled(boolean value) {  endDisabled = value; }


}
