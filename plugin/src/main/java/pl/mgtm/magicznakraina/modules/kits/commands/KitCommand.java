package pl.mgtm.magicznakraina.modules.kits.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.command.CommandInfo;
import pl.mgtm.magicznakraina.command.PluginCommand;
import pl.mgtm.magicznakraina.config.Kit;
import pl.mgtm.magicznakraina.config.User;
import pl.mgtm.magicznakraina.config.UserKitConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@CommandInfo(name = "kit", permission = "", requiresPlayer = true)
public class KitCommand extends PluginCommand {
    private MagicznaKraina pl = MagicznaKraina.getInstance();

    @Override
    public void execute(Player player, String[] args) {
        super.execute(player, args);

        HashMap<String, Kit> kits = pl.getKitsConfig().getKits();
        HashMap<String, Kit> enabledKits = new HashMap<>();

        kits.forEach((key, kit) -> {
            if (kit.getEnabled()) enabledKits.put(key, kit);
        });

        Set<String> enabledKitNames = enabledKits.keySet();

        if (args.length != 0) {
            String chosenKit = args[0].toLowerCase();

            if (!enabledKitNames.contains(chosenKit)) {
                player.sendMessage(ChatColor.RED + "Kit '" + chosenKit + "' nie istnieje!");
                player.sendMessage(ChatColor.GRAY + "Lista dotępnych zestawów: " + ChatColor.WHITE + String.join(", ", enabledKitNames));
                return;
            }

            HashMap<String, User> users = pl.getUserConfig().getUsers();
            User user = users.get(player.getUniqueId().toString());

            UserKitConfig userKit = user.getUserKit(chosenKit);

            // Check if user already used that kit
            if (userKit != null && userKit.getUseCount() > 0) {
                player.sendMessage(ChatColor.RED + "Kit '" + chosenKit + "' został już użyty!");
                return;
            }

            for (String kitname : enabledKitNames) {
                if (!kitname.equalsIgnoreCase(chosenKit)) {
                    continue;
                }

                Kit choosenKit = enabledKits.get(kitname);
                List<ItemStack> kitItems = choosenKit.getItems();

                for (ItemStack item : kitItems) {
                    player.getInventory().addItem(new ItemStack(item));
                }
            }

            if (userKit == null) userKit = new UserKitConfig(player.getUniqueId().toString());
            userKit.addUseCount();

            user.setUserKit(chosenKit, userKit);

            pl.getUserConfig().setUsers(users);

            player.sendMessage(ChatColor.GREEN + "Przedmioty z zestawu '" + ChatColor.GOLD + chosenKit + ChatColor.GREEN + "' zostały dodane do Twojego ekwipunku.");
        } else {
            player.sendMessage(ChatColor.RED + "/kit <nazwa zestawu>");
            player.sendMessage(ChatColor.GRAY + "Lista dotępnych zestawów: " + ChatColor.WHITE + String.join(", ", enabledKitNames));

            //TODO: add gui system for kits
            /*
            GUIAPI.GUI kitsGUI = new GUIAPI.GUI("Kity", 9);

            kitsGUI.setItem(0, new GUIItem(new ItemStack(Material.DIAMOND, 1), "<reset><red>Admin"));
            kitsGUI.setClickAction(0, (p,isRightClick) -> {
                player.sendMessage("Kliknales w GUI :).");
            });

            kitsGUI.setItem(1, new GUIItem(new ItemStack(Material.WOODEN_PICKAXE, 1), "Start"));
            kitsGUI.setClickAction(1, (p,isRightClick) -> {
                player.sendMessage("Oto twoj przedmiot.");
            });

            kitsGUI.setItem(2, new GUIItem(new ItemStack(Material.COOKED_BEEF, 1), "Jedzonko", "<red>Niedostępne!","<blue>Od 20 VI możliwe do użycia."));
            kitsGUI.setClickAction(2, (p,isRightClick) -> {
                player.sendMessage("Oto twoj przedmiot.");
            });

            GUIAPI.openGUI(player, kitsGUI);
            */


        }
    }
}
