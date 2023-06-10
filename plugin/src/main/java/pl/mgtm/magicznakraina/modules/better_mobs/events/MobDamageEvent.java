package pl.mgtm.magicznakraina.modules.better_mobs.events;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MobDamageEvent implements Listener {

    private final Random random = new Random();

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();


        // Check if the damaged entity is a player
        if (entity instanceof Player) {

            Player player = (Player) entity;

            //player.sendMessage("damage");
            // Check if the attacking entity is a hostile monster
            if (event.getDamager().getType().equals(EntityType.ZOMBIE)
                    || event.getDamager().getType().equals(EntityType.SPIDER)) {

                // Blast the player into the air
                if (random.nextInt(4) == 0) { // 25% chance
                    //player.sendMessage("To the air!");

                    // Only works when player is jumping
                    //player.setVelocity(player.getLocation().getDirection().multiply(0).setY(2));

                    // Blast player to the air
                    player.setVelocity(player.getVelocity().add(new Vector(0, 1, 0)));
                }
                // Take away player's weapon
                if (random.nextInt(50) == 0) { // 2% chance
                    // Remove player hand item
                    //player.getInventory().setItemInMainHand(null);

                    // Give it the attacker monster
                    ItemStack weapon = player.getInventory().getItemInMainHand();

                    // Get only weapons
                    if (!weapon.getType().equals(Material.SHIELD)
                            && !weapon.getType().toString().endsWith("_SWORD")
                            && !weapon.getType().toString().endsWith("_AXE")
                            && !weapon.getType().toString().endsWith("_PICKAXE")
                            && !weapon.getType().toString().endsWith("_SHOVEL")
                            && !weapon.getType().toString().endsWith("_HOE")
                    ) {
                        return;
                    }

                    if (weapon != null && !weapon.getType().equals(Material.AIR)) {
                        player.getInventory().setItemInMainHand(null);

                        if (event.getDamager() instanceof Monster) {
                            Monster attacker = (Monster) event.getDamager();
                            EntityEquipment equipment = attacker.getEquipment();
                            if (equipment != null) {
                                if (equipment.getItemInMainHand().getType().equals(Material.AIR)) {

                                    List<Component> lore = new ArrayList<>();
                                    lore.add(Component.text("Taken from player"));
                                    weapon.lore(lore);

                                    equipment.setItemInMainHand(weapon);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
