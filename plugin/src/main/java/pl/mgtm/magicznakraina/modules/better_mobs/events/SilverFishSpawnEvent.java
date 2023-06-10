package pl.mgtm.magicznakraina.modules.better_mobs.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import pl.mgtm.magicznakraina.modules.better_mobs.BetterMobsModule;

public class SilverFishSpawnEvent implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();

        // Check if the broken block is Stone
        if (block.getType() == Material.STONE || block.getType() == Material.DEEPSLATE) {
            // Get the Y-coordinate of the broken block
            int blockY = block.getLocation().getBlockY();

            // Check the height and the chance to spawn Silverfish
            int spawnChance = 0;
            if (blockY < 30) {
                spawnChance = 15; // 15% chance for height below 30
            } else if (blockY < 50) {
                spawnChance = 5; // 5% chance for height below 50
            } else if (blockY < 100) {
                spawnChance = 3; // 3% chance for height below 100
            } else if (blockY < 150) {
                spawnChance = 2; // 2% chance for height below 150
            }

            // Check if Silverfish should be spawned
            if (spawnChance > 0 && BetterMobsModule.shouldSpawnSilverfish(spawnChance)) {
                block.getWorld().spawnEntity(block.getLocation().add(.5, 0, .5), EntityType.SILVERFISH);
            }
        }
    }
}
