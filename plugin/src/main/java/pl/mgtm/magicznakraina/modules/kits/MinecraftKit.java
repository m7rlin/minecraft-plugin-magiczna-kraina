package pl.mgtm.magicznakraina.modules.kits;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public interface MinecraftKit {
    String name = "unknown";
    String label = "unknown";
    Boolean useOnce = true;
    Integer delay = 0;

    ItemStack[] items = new ItemStack[Material.BARRIER.getId()];
}
