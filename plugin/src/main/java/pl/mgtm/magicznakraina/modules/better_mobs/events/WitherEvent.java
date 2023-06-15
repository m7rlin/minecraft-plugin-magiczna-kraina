package pl.mgtm.magicznakraina.modules.better_mobs.events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.persistence.PersistentDataType;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.modules.better_mobs.BetterMobsModule;

import java.util.Random;

public class WitherEvent implements Listener {

    private final MagicznaKraina pl = MagicznaKraina.getInstance();
    private final Random random = new Random();

    private NamespacedKey phaseKey = new NamespacedKey(pl, "spawned");


    @EventHandler
    public void onWitherSpawn(EntitySpawnEvent event) {
        if (event.getEntityType() != EntityType.WITHER) return;

        Wither wither = (Wither) event.getEntity();
        wither.setCustomName(ChatColor.LIGHT_PURPLE + BetterMobsModule.getRandomWitherName());
        wither.setCustomNameVisible(true);
    }

    @EventHandler
    public void onWitherDamage(EntityDamageByEntityEvent event) {
        if (event.getEntityType() != EntityType.WITHER) return;
        if (!(event.getDamager() instanceof Player)) return;

        Player player = (Player) event.getDamager();

        Wither wither = (Wither) event.getEntity();
        double healthPercentage = wither.getHealth() / wither.getMaxHealth() * 100.0;

        //event.getDamager().sendMessage("isPhaseReached: 1:" + isPhaseReached(wither, (byte) 1) + " 2:" + isPhaseReached(wither, (byte) 2) + "");

        // Phase 1
        if (healthPercentage < 66.0 && !isPhaseReached(wither, (byte) 1)) {
            BetterMobsModule.spawnMobs(wither, EntityType.ZOMBIE, 10);
            BetterMobsModule.spawnMobs(wither, EntityType.SKELETON, 5);
            BetterMobsModule.spawnMobs(wither, EntityType.SPIDER, 3);

            player.sendMessage(ChatColor.LIGHT_PURPLE + wither.getName() + ": " + ChatColor.GRAY + "A masz! ... Co teraz zrobisz?");

            setPhaseReached(wither, (byte) 1);
        }

        // Phase 2
        if (healthPercentage < 33.0 && !isPhaseReached(wither, (byte) 2)) {
            Location witherLocation = wither.getLocation();
            BetterMobsModule.spawnElectricCreepers(witherLocation.getWorld(), witherLocation);

            player.sendMessage(ChatColor.LIGHT_PURPLE + wither.getName() + ": " + ChatColor.GRAY + "Creeperos amigos przyzywam was. Hahaha!");

            setPhaseReached(wither, (byte) 2);
        }
    }

    @EventHandler
    public void onWitherDeath(EntityDeathEvent event) {
        if (event.getEntityType() != EntityType.WITHER) return;

        Wither wither = (Wither) event.getEntity();
        resetPhases(wither);

        if (wither.getKiller() instanceof Player) {
            BetterMobsModule.spawnMobs(wither, EntityType.PIG, 10);
            BetterMobsModule.spawnMobs(wither, EntityType.COW, 2);
            event.setDroppedExp(event.getDroppedExp() * 2);
        }
    }

    private void setPhaseReached(Wither wither, byte phase) {
        wither.getPersistentDataContainer().set(phaseKey, PersistentDataType.BYTE, phase);
    }

    private boolean isPhaseReached(Wither wither, byte phase) {
        return wither.getPersistentDataContainer().getOrDefault(phaseKey, PersistentDataType.BYTE, (byte) 0) >= phase;
    }

    private void resetPhases(Wither wither) {
        wither.getPersistentDataContainer().remove(phaseKey);
    }
}
