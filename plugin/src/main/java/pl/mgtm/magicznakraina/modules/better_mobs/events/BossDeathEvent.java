package pl.mgtm.magicznakraina.modules.better_mobs.events;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class BossDeathEvent implements Listener {

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();

        // Check if the entity killed is a mob
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;

            // Check if the entity is a Hydra
            if (livingEntity.getScoreboardTags().contains("Hydra")) {
                // Drop double XP
                event.setDroppedExp(event.getDroppedExp() * 2);

                // Play extra sound effect
                entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_WITHER_DEATH, 1.0f, 1.0f);

                if (event.getEntity().getKiller() instanceof Player) {
                    Player player = event.getEntity().getKiller();
                    player.sendMessage(ChatColor.RED + "Hydra: "+ ChatColor.GRAY + "Nie dasz rady mnie zabić....");
                }

                // Spawn 2 additional mobs of the same type
                int numExtraMobs = 2;
                EntityType entityType = livingEntity.getType();
                for (int i = 0; i < numExtraMobs; i++) {
                    Entity extraMob = livingEntity.getWorld().spawnEntity(livingEntity.getLocation(), entityType);
                    if (extraMob instanceof LivingEntity) {
                        LivingEntity livingExtraMob = (LivingEntity) extraMob;
                        livingExtraMob.setRemoveWhenFarAway(true);
                    }
                }
            }
        }
    }
}
