package pl.mgtm.magicznakraina.modules.kits.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.templates.CommandInfo;
import pl.mgtm.magicznakraina.templates.PluginCommand;
import pl.mgtm.magicznakraina.helpers.ConfigHelpers;

import java.util.List;
import java.util.Set;

// TODO: Use slightly different types (for example there is used Set? why?)
// also fix some other things
@CommandInfo(name = "kit", permission = "", requiresPlayer = true)
public class KitCommand extends PluginCommand {
    private final MagicznaKraina plugin = MagicznaKraina.getInstance();

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
            String chosenKit = args[0].toLowerCase();

            if (!kits.contains(chosenKit)) {
                player.sendMessage(ChatColor.RED + "Kit '" + chosenKit +"' nie istnieje!");
                player.sendMessage(ChatColor.GRAY +  "Lista dotępnych zestawów: " + ChatColor.WHITE + String.join(", ", kits));
                return;
            }

            // Sprawdz czy uzytkownik uzyl wczesniej komendy
            if (ConfigHelpers.playerUsedKit(player.getUniqueId(), chosenKit)) {
                player.sendMessage(ChatColor.RED + "Kit '" + chosenKit +"' został już użyty!");
                return;
            }

            for (String kitname : kits) {
                List<String> kititems = (List<String>) config.getList("kits." + kitname + ".items");

                if (!kitname.equalsIgnoreCase(chosenKit)) { continue; }

                for (String x : kititems) {
                    String materialName = x.split(",")[0];
                    Material itemMaterial = Material.matchMaterial(materialName);

                    if (itemMaterial != null) {
                        Integer amount;

                        try {
                            amount =  Integer.parseInt(x.split(",")[1]);
                        } catch (NumberFormatException e) {
                            amount = 1;
                        }

                        player.getInventory().addItem(new ItemStack(itemMaterial, amount));
                    }
                }
            }

            ConfigHelpers.addPlayerKit(player.getUniqueId(), chosenKit);

            player.sendMessage(ChatColor.GREEN + "Przedmioty z zestawu '" + chosenKit +"' zostały dodane do Twojego ekwipunku.");
        } else {
            player.sendMessage(ChatColor.RED + "/kit <nazwa zestawu>");
            player.sendMessage(ChatColor.GRAY + "Lista dotępnych zestawów: " + ChatColor.WHITE +  String.join(", ", kits));
        }
    }
}
