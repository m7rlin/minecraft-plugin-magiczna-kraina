package pl.mgtm.magicznakraina.modules.serduszko;

import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.command.CommandInfo;
import pl.mgtm.magicznakraina.modules.serduszko.commands.ReviveCommand;
import pl.mgtm.magicznakraina.modules.serduszko.commands.SerduszkoCommand;
import pl.mgtm.magicznakraina.modules.serduszko.events.DeathEvent;
import pl.mgtm.magicznakraina.modules.serduszko.events.JoinServerEvent;
import pl.mgtm.magicznakraina.modules.serduszko.events.PreLoginEvent;


public class SerduszkoModule {

    private final MagicznaKraina pl = MagicznaKraina.getInstance();

    private String bannedPlayerMessage = ChatColor.GRAY + "Straciłeś wszystkie serduszka!\nJeżeli chcesz grać dalej, gracze mogą cie " + ChatColor.GOLD + "wskrzeszyć!\n" + ChatColor.GRAY + "Po więcej informacji dołącz na naszego Discorda\n\n" + ChatColor.AQUA + "https://discord.mgtm.pl/ " + ChatColor.RED + "\n\nZostałeś wykluczony z serwera!";

    public SerduszkoModule() {
        PluginManager pm = pl.getServer().getPluginManager();

        // Register events
        pm.registerEvents(new JoinServerEvent(), pl);
        pm.registerEvents(new DeathEvent(), pl);
        pm.registerEvents(new PreLoginEvent(), pl);

        // Register commands
        pl.getCommand(SerduszkoCommand.class.getAnnotation(CommandInfo.class).name()).setExecutor(new SerduszkoCommand());
        pl.getCommand(ReviveCommand.class.getAnnotation(CommandInfo.class).name()).setExecutor(new ReviveCommand());
    }

    public String getBannedPlayerMessage() {
        return this.bannedPlayerMessage;
    }
}
