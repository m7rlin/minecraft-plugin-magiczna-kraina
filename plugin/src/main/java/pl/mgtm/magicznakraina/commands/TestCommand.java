package pl.mgtm.magicznakraina.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.command.CommandInfo;
import pl.mgtm.magicznakraina.command.PluginCommand;

import java.util.ArrayList;
import java.util.HashMap;

@CommandInfo(name = "test", permission = "", requiresPlayer = true)
public class TestCommand extends PluginCommand {
    private MagicznaKraina plugin = MagicznaKraina.getInstance();

    @Override
    public void execute(Player player, String[] args) {
        super.execute(player, args);

        // Remove item
        //ItemStack item = new ItemStack(Material.CHEST, 10);
        //player.getInventory().removeItem(item);

        /*ArrayList<ItemStack> items2 = new ArrayList<>();
        items2.add(new ItemStack(Material.CHEST, 10));
        items2.add(new ItemStack(Material.CHEST, 10));

        player.getInventory().removeItem(items2.toArray(new ItemStack[0]));*/

       // player.sendMessage(plugin.getServer().getOfflinePlayer("merlin"));

    }
}
