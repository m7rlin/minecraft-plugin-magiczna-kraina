package pl.mgtm.magicznakraina.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.command.CommandInfo;
import pl.mgtm.magicznakraina.command.PluginCommand;

import java.util.Arrays;

@CommandInfo(name = "guitest", permission = "", requiresPlayer = true)
public class GuiTestingCommand extends PluginCommand {
    private MagicznaKraina plugin = MagicznaKraina.getInstance();

    @Override
    public void execute(Player player, String[] args) {
        super.execute(player, args);

        // TODO: GUI Builder, I just dont have a mind today to do that
        Inventory inventory = Bukkit.createInventory(null, 9, "Test GUI");

        inventory.addItem(createGuiItem(Material.IRON_PICKAXE, ChatColor.DARK_RED + "Ban Hammer", "Zbanuj kogoś tym przepięknym", "i malusim młotem :)", "", ChatColor.GOLD + "LEGENDARY"));

        player.openInventory(inventory);

        player.sendMessage(ChatColor.GREEN + "Opened a gui!");
    }

    public ItemStack createGuiItem(Material material, String name, String... lore) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);

        return item;
    }
}
