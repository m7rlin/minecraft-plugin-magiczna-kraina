package pl.mgtm.magicznakraina.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import pl.mgtm.magicznakraina.MagicznaKraina;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;

public class SuperPickaxeEvent implements Listener {

    private MagicznaKraina pl = MagicznaKraina.getInstance();

    @EventHandler
    public void onBlockMined(BlockBreakEvent e) {

        Player player = e.getPlayer();

        Location blockLocation = e.getBlock().getLocation();


        e.getBlock().setType(Material.AIR);

        //e.getBlock().getWorld().getBlockAt(blockLocation.subtract(1, 0, 0)).setType(Material.AIR);





        player.sendMessage("block destoyed 2");

        e.setCancelled(true);
    }


}
