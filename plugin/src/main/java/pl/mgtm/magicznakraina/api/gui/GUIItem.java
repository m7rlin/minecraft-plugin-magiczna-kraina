package pl.mgtm.magicznakraina.api.gui;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GUIItem {
    private ItemStack itemStack;

    public GUIItem(ItemStack itemStack, String name, String... lore) {
        this.itemStack = itemStack;
        this.setName(name);
        this.setLore(lore);
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public GUIItem setName(String name) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            Component component = MiniMessage.miniMessage().deserialize(name);
            meta.displayName(component);
            itemStack.setItemMeta(meta);
        }
        return this;
    }

    public GUIItem setLore(String... lore) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            List<Component> components = new ArrayList<>();
            for (String line : lore) {
                components.add(MiniMessage.miniMessage().deserialize(line));
            }
            meta.lore(components);
            itemStack.setItemMeta(meta);
        }
        return this;
    }

}