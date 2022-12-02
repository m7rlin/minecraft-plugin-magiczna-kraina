package pl.mgtm.magicznakraina.modules.protect_chests.events;

import com.destroystokyo.paper.event.block.TNTPrimeEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.TileState;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import pl.mgtm.magicznakraina.MagicznaKraina;

import java.util.Iterator;
import java.util.List;

public class BreakChest implements Listener {

    private MagicznaKraina pl = MagicznaKraina.getInstance();
    private final String metaKey = "TNTPlayerId";

    @EventHandler
    public void onBreakChest(BlockBreakEvent e) {

        Block block = e.getBlock();

        if (block.getType() != Material.CHEST) return;

        if (!(block.getState() instanceof TileState)) return;

        TileState state = (TileState) block.getState();
        PersistentDataContainer container = state.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(pl, "protect_chests");

        if (!container.has(key, PersistentDataType.STRING)) return;

        if (e.getPlayer().getUniqueId().toString().equalsIgnoreCase(container.get(key, PersistentDataType.STRING))) {
            return;
        }

        e.setCancelled(true);
        e.getPlayer().sendMessage(ChatColor.RED + "Nie możesz zniszczyć zablokowanej skrzyni.");
    }


    @EventHandler
    public void onBreakChest2(EntityExplodeEvent e) {

        List blocks = e.blockList();

        //Bukkit.getPlayer("merlin").sendMessage(e.getEntity().getName() + blocks.toArray());



        String playerId = "";
        if (e.getEntity().hasMetadata(metaKey)) {
            playerId = e.getEntity().getMetadata(metaKey).get(0).asString();
        }
        //Bukkit.getPlayer("merlin").sendMessage("ID: " + playerId);

        for (Iterator<Block> i = blocks.iterator(); i.hasNext(); ) {
            Block block = i.next();
            //Bukkit.getPlayer("merlin").sendMessage(block.getType().toString() + " " + block.getType());
            // Tylko skrzynka
            if (block.getType() == Material.CHEST) {
                // Wybuch wygenerowany przez inny wychuch np. creeper albo inne TNT
                if (playerId.isEmpty()) {

                    //Bukkit.getPlayer("merlin").sendMessage("player id is empty");

                    i.remove();
                    continue;
                }

                if (!(block.getState() instanceof TileState)) continue;
                TileState state = (TileState) block.getState();
                PersistentDataContainer container = state.getPersistentDataContainer();
                NamespacedKey key = new NamespacedKey(MagicznaKraina.getPlugin(MagicznaKraina.class), "protect_chests");

                if (!container.has(key, PersistentDataType.STRING)) continue;

                if (playerId.equalsIgnoreCase(container.get(key, PersistentDataType.STRING)))
                    continue;

                //Bukkit.getPlayer("merlin").sendMessage("Skrzynia zablokowana!");

                i.remove();
            }

            //Bukkit.getPlayer("merlin").sendMessage(e.getEntity().getMetadata(metaKey).get(0).asString());
            //Bukkit.getPlayer("merlin").sendMessage(block.getType().toString());


        }


        //Bukkit.getPlayer("merlin").sendMessage(blocks.iterator().next().toString());

        //e.setCancelled(true);
    }

    @EventHandler
    public void onBreakChest4(TNTPrimeEvent e) {
        // Tylko gracz
        if (!(e.getPrimerEntity() instanceof Player)) return;

        Player player = (Player) e.getPrimerEntity();
        String playerId = player.getUniqueId().toString();

        Block block = e.getBlock();

        // Usun blok
        Bukkit.getWorld(player.getWorld().getUID()).getBlockAt(block.getLocation()).setType(Material.AIR);
        // Stworzenie aktywowanego TNT
        TNTPrimed tnt = (TNTPrimed) Bukkit.getWorld(player.getWorld().getUID()).spawnEntity(block.getLocation().add(0.5, 0, 0.5), EntityType.PRIMED_TNT);
        // Ustawienie czasu detonacji
        tnt.setFuseTicks(40);

        // Ustawienie ID gracza, ktory wysadza TNT
        tnt.setMetadata(metaKey, new FixedMetadataValue(pl, playerId));

        //Bukkit.getPlayer("merlin").sendMessage("Prime event");

        e.setCancelled(true);
    }

    @EventHandler
    public void onBreakChest3(InventoryMoveItemEvent e) {


        // TODO: fix hoppers by placing data container in hopper

        if (e.getSource().getType().toString() != Material.CHEST.name()) return;

        Block block = Bukkit.getWorld("world").getBlockAt(e.getSource().getLocation());

        if (!(block.getState() instanceof TileState)) return;

        TileState state = (TileState) block.getState();
        PersistentDataContainer container = state.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(pl, "protect_chests");

        if (!container.has(key, PersistentDataType.STRING)) return;

        /*
        if (e.().getUniqueId().toString().equalsIgnoreCase(container.get(key, PersistentDataType.STRING))) {
            return;
        }*/

        //Bukkit.getPlayer("merlin").sendMessage(block.getType().toString());

        e.setCancelled(true);
    }


}