package pl.mgtm.magicznakraina.helpers;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class GuiHelpers {
    public static ItemStack createItem(Material material, Integer amount, String itemName) {
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(itemName);

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack createItemWithLore(Material material, Integer amount, String itemName, String... itemLore) {
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(itemName);
        itemMeta.setLore(Arrays.asList(itemLore));

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}
