package pl.mgtm.magicznakraina.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.command.CommandInfo;
import pl.mgtm.magicznakraina.command.PluginCommand;
import pl.mgtm.magicznakraina.helpers.ServerHelper;

import java.util.ArrayList;
import java.util.List;

@CommandInfo(name = "fly", permission = "mgtm.fly", requiresPlayer = true, usage = "/fly [gracz]")
public class FlyCommand extends PluginCommand {

    private final MagicznaKraina pl = MagicznaKraina.getInstance();
    @Override
    public void execute(Player player, String[] args) {
        super.execute(player, args);

        if (args.length > 1) {
            super.commandUsage(player);
            return;
        }

        if (args.length < 1) {
            player.setAllowFlight(!player.getAllowFlight());
            player.sendMessage(ChatColor.RED + "Tryb lotu przełączony na " + (player.getAllowFlight() ? ChatColor.GREEN + "włączony" : ChatColor.YELLOW + "wyłączony.") + ChatColor.RED + ".");
            return;
        }

        Player targetPlayer = pl.getServer().getPlayer(args[0]);

        if (targetPlayer == null || !targetPlayer.isOnline()) {
            player.sendMessage(ChatColor.RED +"Podany gracz nie jest online.");
            return;
        }

        targetPlayer.setAllowFlight(!targetPlayer.getAllowFlight());
        player.sendMessage(ChatColor.RED + "Tryb lotu przełączony na " + (targetPlayer.getAllowFlight() ? ChatColor.GREEN + "włączony" : ChatColor.YELLOW + "wyłączony") + ChatColor.RED + " dla " + ChatColor.YELLOW + targetPlayer.getName() + ChatColor.RED + ".");
    }

    @Override
    public List<String> tabAutocomplete(CommandSender sender, Command command, String label, String[] args) {
        super.tabAutocomplete(sender,command,label,args);
        
        if (args.length == 1) {
            return ServerHelper.getOnlinePlayerNames();
        }

        return new ArrayList<>();
    }
}
