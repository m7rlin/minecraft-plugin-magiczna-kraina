package pl.mgtm.magicznakraina.modules.better_mobs.events;

import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Random;

public class ExplosiveAnimalsEvent implements Listener {

    private static final double EXPLOSION_CHANCE = 0.05; // 5% chance of explosion

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();

        // Check if the killed entity is a chicken or pig
        if (entity.getType() != EntityType.CHICKEN && entity.getType() != EntityType.PIG) return;
        // Check if the player is the killer
        if (!(entity.getKiller() instanceof Player)) return;

        double randomDouble = Math.random();

        //entity.getKiller().sendMessage("randomDouble: " + randomDouble);

        // Roll the chance for explosion
        if (shouldExplode()) {
            //entity.getKiller().sendMessage("boom");


            // Perform explosion
            entity.getWorld().createExplosion(entity.getLocation(), 3f);

            // Play funny chicken sound
            entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_CHICKEN_HURT, 1f, 1f);

            // Spawn red particle effects with custom options
            spawnDustParticles(entity.getLocation());
        }

    }

    private boolean shouldExplode() {
        Random random = new Random();
        return random.nextDouble() <= EXPLOSION_CHANCE;
    }

    private void spawnDustParticles(org.bukkit.Location location) {
        for (int i = 0; i < 10; i++) {
            location.getWorld().spawnParticle(
                    Particle.REDSTONE,
                    location,
                    0,
                    new Particle.DustOptions(Color.RED, 1)
            );
        }
    }
}
