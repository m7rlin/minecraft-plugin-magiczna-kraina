package pl.mgtm.magicznakraina.events;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ExplosiveArrowEvent implements Listener {
    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {

        Arrow arrow = (Arrow) event.getEntity();
        Player player = (Player) arrow.getShooter();

        Location arrowHitLocation = arrow.getLocation();

        World world = player.getWorld();

        world.createExplosion(arrowHitLocation, 3, false, true);

        event.setCancelled(true);
    }

}
