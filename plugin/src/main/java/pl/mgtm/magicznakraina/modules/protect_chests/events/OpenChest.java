package pl.mgtm.magicznakraina.modules.protect_chests.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.TileState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import pl.mgtm.magicznakraina.MagicznaKraina;

import java.util.Arrays;

// TODO: Refactor (my god those ifs)
public class OpenChest implements Listener {

    @EventHandler
    public void onOpenChest(PlayerInteractEvent e) {
        if (!e.hasBlock()) return;

        if (e.getClickedBlock().getType() != Material.CHEST) return;

        if (!e.getAction().isRightClick()) return;

        if (!(e.getClickedBlock().getState() instanceof TileState)) return;

        TileState state = (TileState) e.getClickedBlock().getState();

        PersistentDataContainer container = state.getPersistentDataContainer();

        NamespacedKey key = new NamespacedKey(MagicznaKraina.getPlugin(MagicznaKraina.class), "protect_chests");

        //e.getPlayer().sendMessage("keys:"+container.getKeys());
        //e.getPlayer().sendMessage(container.get(key, PersistentDataType.STRING));

        if (!container.has(key, PersistentDataType.STRING)) return;

        if (e.getPlayer().getUniqueId().toString().equalsIgnoreCase(container.get(key, PersistentDataType.STRING)))
            return;

        e.setCancelled(true);
        e.getPlayer().sendMessage(ChatColor.RED + "Nie możesz otworzyć zablokowanej skrzyni.");

    }
}
