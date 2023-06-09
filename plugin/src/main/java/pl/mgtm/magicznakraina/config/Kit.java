package pl.mgtm.magicznakraina.config;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.w3c.dom.Text;
import pl.mgtm.magicznakraina.api.config.annotation.Comment;
import pl.mgtm.magicznakraina.api.config.annotation.ConfigOptional;

import java.io.Serializable;
import java.util.List;

public class Kit implements Serializable {

    private boolean enabled = false;

    private transient String name;

    private ItemStack itemSlot;


    private List<ItemStack> items;

    private int cooldown = 0;
    private int uses = 1;


    public Kit() {
    }

    public Kit(String name, ItemStack itemSlot, List<ItemStack> items) {
        this.name = name;
        TextComponent itemName = Component.text(name);
        this.itemSlot = itemSlot;
        this.itemSlot.getItemMeta().displayName(itemName);
        this.items = items;
    }

    public Kit(String name, ItemStack itemSlot) {
        this.name = name;
        TextComponent itemName = Component.text(name);
        this.itemSlot = itemSlot;
        this.itemSlot.getItemMeta().displayName(itemName);
    }

    public boolean getEnabled() {return enabled;}
    public void setEnabled(boolean status) { enabled = status;}

    public String getName() { return name; }

    public List<ItemStack> getItems() { return items;}

    public void setItems(List<ItemStack> items) { this.items = items; }
    public void addItem(ItemStack item) { items.add(item); }

}


