package pl.mgtm.magicznakraina.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.command.CommandInfo;
import pl.mgtm.magicznakraina.command.PluginCommand;

@CommandInfo(name = "enderchest", permission = "", requiresPlayer = true)
public class EnderchestCommand extends PluginCommand {
    private MagicznaKraina pl = MagicznaKraina.getInstance();

    @Override
    public void execute(Player player, String[] args) {
        super.execute(player, args);

        FileConfiguration config = pl.getConfig();

        if (args.length == 0) {
            Inventory enderChest = player.getEnderChest();

            // Otwieramy enderchest gracza
            player.openInventory(enderChest);

            player.sendMessage(ChatColor.GREEN + "Twoja skrzynia kresu została otworzona");
        } else if (args.length == 1) {
            // Otwieramy encherchesta innego gracza, ! DOSTEPNE TYLKO DLA ADMINOW !

            if (player.isOp()) {
                Player target = Bukkit.getPlayer(args[0]);

                if (target != null) {
                    target.openInventory(target.getEnderChest());
                    player.sendMessage((!target.isOnline() ? ChatColor.RED + "Ten gracz jest offline\n" : "")  + ChatColor.GREEN + "Skrzynia kresu gracza " + ChatColor.BLUE + target.getName() + ChatColor.GREEN + " została otworzona");
                } else {
                    player.sendMessage(ChatColor.RED + "Ten gracz nie jest online");
                }
            } else {
                insufficientPermissions(player);
            }

        }
    }

}
