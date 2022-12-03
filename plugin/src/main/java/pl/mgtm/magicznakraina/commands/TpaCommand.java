package pl.mgtm.magicznakraina.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.mgtm.magicznakraina.MagicznaKraina;

public class TpaCommand implements CommandExecutor {
    private MagicznaKraina plugin = MagicznaKraina.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cTylko gracz może użyć tej komendy.");
            return true;
        }

        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("tpa")) {
            player.sendMessage("tpa");
            return true;
        }


        if (cmd.getName().equalsIgnoreCase("tpaccept")) {
            player.sendMessage("tpaacept");
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("tpdeny")) {
            player.sendMessage("tpdeny");
            return true;
        }

        return false;
    }
}
