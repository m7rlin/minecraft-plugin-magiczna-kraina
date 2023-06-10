package pl.mgtm.magicznakraina.modules.better_mobs.events;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import pl.mgtm.magicznakraina.modules.better_mobs.BetterMobsModule;


public class MobDeathEvent implements Listener {

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();

        // Check if the entity killed is a boss (Ender Dragon or Wither)
        if (entity.getType().equals(EntityType.ENDER_DRAGON)
                || entity.getType().equals(EntityType.WITHER)) {


            // Increase boss health and damage
            entity.setMaxHealth(entity.getMaxHealth() * 2);
            entity.setHealth(entity.getMaxHealth());
        }


        // Check if the entity killed is a Zombie or Spider
        if (entity.getType().equals(EntityType.ZOMBIE) || entity.getType().equals(EntityType.SPIDER) || entity.getType().equals(EntityType.SKELETON)) {
            // Remove all item drop from entity
            //event.getDrops().clear();

            event.getDrops().removeIf(item ->
                    item.getType().equals(Material.SHIELD)
                    || item.getType().toString().endsWith("_SWORD")
                    || item.getType().toString().endsWith("_AXE")
                    || item.getType().toString().endsWith("_PICKAXE")
                    || item.getType().toString().endsWith("_SHOVEL")
                    || item.getType().toString().endsWith("_HOE")
                    || item.getType().toString().endsWith("_HELMET")
                    || item.getType().toString().endsWith("_CHESTPLATE")
                    || item.getType().toString().endsWith("_LEGGINGS")
                    || item.getType().toString().endsWith("_BOOTS"));

            //Bukkit.getPlayer("Merlin_PlayGames").sendMessage(event.getDrops() + "");

            if (entity instanceof Monster) {
                Monster monster = (Monster) entity;
                EntityEquipment equipment = monster.getEquipment();
                if (equipment != null) {
                    // Check if the main hand item is not empty
                    ItemStack mainHandItem = equipment.getItemInMainHand();
                    if (mainHandItem != null && !mainHandItem.getType().equals(Material.AIR)) {
                        // If the item is not generated by plugin
                        if (BetterMobsModule.isTakenFromPlayer(mainHandItem)) {
                            // Clear item lore
                            mainHandItem.lore(null);
                            // Drop the main hand item on death
                            event.getDrops().add(mainHandItem);
                        }

                    }
                }
            }
        }
    }

}