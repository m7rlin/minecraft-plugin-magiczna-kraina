package pl.mgtm.magicznakraina.modules.kits.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.command.CommandInfo;
import pl.mgtm.magicznakraina.command.PluginCommand;

import java.util.List;
import java.util.Set;

@CommandInfo(name = "kit", permission = "", requiresPlayer = true)
public class KitCommand extends PluginCommand {
    private MagicznaKraina plugin = MagicznaKraina.getInstance();

    @Override
    public void execute(Player player, String[] args) {
        super.execute(player, args);

        FileConfiguration config = plugin.getConfig();

        if (!config.isConfigurationSection("kits")) {
            player.sendMessage(ChatColor.RED + "Ta funkcja jest obecnie niedostępna.");
            return;
        }

        Set<String> kits = config.getConfigurationSection("kits").getKeys(false);

        if (args.length != 0) {
            String kitNameArg = args[0].toLowerCase();

            if (!kits.contains(kitNameArg)) {
                player.sendMessage(ChatColor.RED + "Kit '" + kitNameArg +"' nie istnieje!");
                player.sendMessage(ChatColor.GRAY +  "Lista dotępnych zestawów: " + ChatColor.WHITE + String.join(", ", kits));
                return;
            }

            for (String kitname : kits) {
                //player.sendMessage(kitname);
                List<String> kititems = (List<String>) config.getList("kits." + kitname + ".items");

                if (!kitname.equalsIgnoreCase(kitNameArg)) { continue; }

                for (String x : kititems) {

                    String materialName = x.split(",")[0];
                    Material itemMaterial = Material.matchMaterial(materialName);

                    if (itemMaterial != null) {
                        Integer amount;
                        try {
                            amount =  Integer.parseInt(x.split(",")[1]);
                        }catch (NumberFormatException e) {
                            amount = 1;
                        }

                        player.getInventory().addItem(new ItemStack(itemMaterial, amount));
                    }
                }
            }

            player.sendMessage(ChatColor.GREEN + "Przedmioty z zestawu '" + kitNameArg +"' zostały dodane do Twojego ekwipunku.");


        } else {
            player.sendMessage(ChatColor.RED + "/kit <nazwa zestawu>");
            player.sendMessage(ChatColor.GRAY + "Lista dotępnych zestawów: " + ChatColor.WHITE +  String.join(", ", kits));
        }
    }


}
