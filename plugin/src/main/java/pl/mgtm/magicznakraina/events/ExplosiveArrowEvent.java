package pl.mgtm.magicznakraina.events;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

// TODO: Get some info from Marcin about this - is it even gonna be used?
public class ExplosiveArrowEvent implements Listener {
    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        Arrow arrow = (Arrow) event.getEntity();
        Player player = (Player) arrow.getShooter();
        World world = player.getWorld();

        world.createExplosion(arrow.getLocation(), 3, false, true);

        event.setCancelled(true);
    }

}
