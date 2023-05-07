package pl.mgtm.magicznakraina.modules.protect_chests.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.TileState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import pl.mgtm.magicznakraina.MagicznaKraina;

// TODO: Formatting
public class PlaceChest implements Listener {

    private MagicznaKraina pl = MagicznaKraina.getInstance();

    @EventHandler
    public void onChestPlace(BlockPlaceEvent e) {
        if (e.getBlock().getType() != Material.CHEST) return;

        if (!(e.getBlock().getState() instanceof TileState)) return;

        Player player = e.getPlayer();


        for (BlockFace face : BlockFace.values()) {
            if (face == BlockFace.SELF) continue;

            Block block = e.getBlock().getRelative(face);
            Chest chest = (Chest) e.getBlock().getState();
            if (block.getType() == Material.CHEST) {
                //player.sendMessage("skrzynka " + face);

                if (!(block.getState() instanceof TileState)) continue;
                TileState state = (TileState) block.getState();
                PersistentDataContainer container = state.getPersistentDataContainer();
                NamespacedKey key = new NamespacedKey(MagicznaKraina.getPlugin(MagicznaKraina.class), "protect_chests");
                if (!container.has(key, PersistentDataType.STRING)) continue;

                if (e.getPlayer().getUniqueId().toString().equalsIgnoreCase(container.get(key, PersistentDataType.STRING)))
                    continue;

                e.setCancelled(true);
                player.sendMessage(ChatColor.RED + "Nie możesz położyć skrzyni koło skrzyni innego gracza.");
                return;
            }

        }

        TileState state = (TileState) e.getBlock().getState();
        PersistentDataContainer container = state.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(MagicznaKraina.getPlugin(MagicznaKraina.class), "protect_chests");
        container.set(key, PersistentDataType.STRING, e.getPlayer().getUniqueId().toString());

        state.update();

        player.sendMessage(ChatColor.GREEN + "Skrzynia automatycznie zablokowana.");
    }

}
