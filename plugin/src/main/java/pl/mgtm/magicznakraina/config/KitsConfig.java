package pl.mgtm.magicznakraina.config;


import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import pl.mgtm.magicznakraina.api.config.Config;
import pl.mgtm.magicznakraina.api.config.annotation.ConfigName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ConfigName("kits.yml")
public interface KitsConfig extends Config {


    default HashMap<String, Kit> getKits() {
        HashMap<String, Kit> defaultKits = new HashMap<>();

        List<ItemStack> items = new ArrayList<>();

        items.add(new ItemStack(Material.WOODEN_SWORD));
        items.add(new ItemStack(Material.LEATHER_HELMET));
        items.add(new ItemStack(Material.LEATHER_CHESTPLATE));
        items.add(new ItemStack(Material.LEATHER_LEGGINGS));
        items.add(new ItemStack(Material.LEATHER_BOOTS));
        items.add(new ItemStack(Material.COOKED_CHICKEN,8));
        items.add(new ItemStack(Material.COOKED_BEEF, 16));

        ItemStack itemslot = new ItemStack(Material.COOKED_BEEF);
        itemslot.getItemMeta().displayName(Component.text("Start"));

        Kit kit = new Kit("start", itemslot);
        kit.setItems(items);
        defaultKits.put(kit.getName(), kit);

        return defaultKits;
    }

    public void setKits(HashMap<String, Kit> users);

}