package pl.mgtm.magicznakraina.modules.better_mobs.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import pl.mgtm.magicznakraina.modules.better_mobs.BetterMobsModule;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MobSpawnEvent implements Listener {

    private final Random random = new Random();

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        if (event.getEntity() instanceof Monster) {
            Monster monster = (Monster) event.getEntity();

            Entity entity = event.getEntity();

            if (entity instanceof LivingEntity) {

                LivingEntity livingEntity = (LivingEntity) entity;

                // Check if the entity is a hostile mob
                if (BetterMobsModule.isHostileMob(livingEntity)) {
                    // Check if the mob should be spawned as a Hydra
                    if (BetterMobsModule.shouldSpawnAsHydra()) {
                        livingEntity.setCustomName(ChatColor.RED + "Hydra");
                        livingEntity.setCustomNameVisible(true);
                        livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(livingEntity.getMaxHealth() * 2);
                        livingEntity.setHealth(livingEntity.getMaxHealth());
                        livingEntity.addScoreboardTag("Hydra");
                    } else if (entity.getType() == EntityType.ZOMBIE) {
                        // Check if the mob should have extra speed
                        if (BetterMobsModule.shouldSpawnZombieWithExtraSpeed()) {
                            Zombie zombie = (Zombie) entity;
                            zombie.setBaby(false);
                            zombie.setAdult();
                            double speed = ThreadLocalRandom.current().nextDouble(0.2, 0.6);
                            zombie.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(speed); // Adjust the speed
                        }
                    }
                }

            }

            // Spawn monsters with armor and sword


            if (!monster.getType().equals(EntityType.ZOMBIE) && !monster.getType().equals(EntityType.SPIDER) && !monster.getType().equals(EntityType.SKELETON)) {
                return;
            }

            // Spawn with enchanted armor and weapons
            if (random.nextInt(10) == 0) { // 10% chance
                ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
                helmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
                monster.getEquipment().setHelmet(helmet);
            }
            if (random.nextInt(10) == 0) { // 10% chance
                ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
                chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
                monster.getEquipment().setChestplate(chestplate);
            }
            if (random.nextInt(10) == 0) { // 10% chance
                ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
                leggings.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
                monster.getEquipment().setLeggings(leggings);
            }
            if (random.nextInt(10) == 0) { // 10% chance
                ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
                boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
                monster.getEquipment().setBoots(boots);
            }
            if (random.nextInt(10) == 0) { // 10% chance
                ItemStack weapon = new ItemStack(Material.DIAMOND_SWORD);
                weapon.addEnchantment(Enchantment.DAMAGE_ALL, 2);
                monster.getEquipment().setItemInMainHand(weapon);
            }

        }
    }


}
