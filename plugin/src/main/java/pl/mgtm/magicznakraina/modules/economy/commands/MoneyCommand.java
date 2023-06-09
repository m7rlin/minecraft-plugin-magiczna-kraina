package pl.mgtm.magicznakraina.modules.economy.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.command.CommandInfo;
import pl.mgtm.magicznakraina.command.PluginCommand;

@CommandInfo(name = "money", permission = "", requiresPlayer = true)
public class MoneyCommand extends PluginCommand {
    private MagicznaKraina pl = MagicznaKraina.getInstance();

    @Override
    public void execute(Player player, String[] args) {
        super.execute(player, args);

        player.sendMessage(ChatColor.GRAY + "Twoje saldo: " + ChatColor.GREEN + pl.getEco().formatBalance(pl.getEco().getBalance(player)));
    }

}
