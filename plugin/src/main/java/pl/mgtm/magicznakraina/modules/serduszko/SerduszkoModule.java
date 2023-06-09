package pl.mgtm.magicznakraina.modules.serduszko;

import org.bukkit.ChatColor;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.module.ModuleInfo;
import pl.mgtm.magicznakraina.module.PluginModule;
import pl.mgtm.magicznakraina.modules.serduszko.commands.ReviveCommand;
import pl.mgtm.magicznakraina.modules.serduszko.commands.SerduszkoCommand;
import pl.mgtm.magicznakraina.modules.serduszko.events.DeathEvent;
import pl.mgtm.magicznakraina.modules.serduszko.events.JoinServerEvent;
import pl.mgtm.magicznakraina.modules.serduszko.events.PreLoginEvent;

@ModuleInfo(name = "serduszko")
public class SerduszkoModule extends PluginModule {

    private final MagicznaKraina pl = MagicznaKraina.getInstance();

    private String bannedPlayerMessage = ChatColor.GRAY + "Straciłeś wszystkie serduszka!\nJeżeli chcesz grać dalej, gracze mogą cie " + ChatColor.GOLD + "wskrzeszyć!\n" + ChatColor.GRAY + "Po więcej informacji dołącz na naszego Discorda\n\n" + ChatColor.AQUA + "https://discord.mgtm.pl/ " + ChatColor.RED + "\n\nZostałeś wykluczony z serwera!";

    public SerduszkoModule() {
        super();

        // Register events
        super.registerEvents(new JoinServerEvent());
        super.registerEvents(new DeathEvent());
        super.registerEvents(new PreLoginEvent());

        // Register commands
        super.registerCommand(new SerduszkoCommand());
        super.registerCommand(new ReviveCommand());
    }

    public String getBannedPlayerMessage() {
        return this.bannedPlayerMessage;
    }
}
