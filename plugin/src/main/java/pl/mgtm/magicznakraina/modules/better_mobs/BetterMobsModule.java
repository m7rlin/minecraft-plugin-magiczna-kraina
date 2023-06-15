package pl.mgtm.magicznakraina.modules.better_mobs;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.module.ModuleInfo;
import pl.mgtm.magicznakraina.module.PluginModule;
import pl.mgtm.magicznakraina.modules.better_mobs.events.*;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@ModuleInfo(name = "better_mobs")
public class BetterMobsModule extends PluginModule {

    private final MagicznaKraina pl = MagicznaKraina.getInstance();

    private static List<String> witherNames = Arrays.asList("Kociak", "Mega Hydraulik", "Halinka");


    public BetterMobsModule() {
        super();

        // Register commands
        //super.registerCommand(new SpawnCommand());
        //super.registerCommand(new SetSpawnCommand());

        // Register events
        super.registerEvents(new MobDamageEvent());
        super.registerEvents(new MobDeathEvent());
        super.registerEvents(new MobSpawnEvent());
        super.registerEvents(new BossDeathEvent());
        super.registerEvents(new SilverFishSpawnEvent());
        super.registerEvents(new WitherEvent());

    }

    public static boolean isTakenFromPlayer(ItemStack item) {
        ItemMeta itemMeta = item.getItemMeta();

        // Check if the item has lore indicating it was taken from a player
        if (itemMeta != null && itemMeta.getLore() != null) {
            for (String loreLine : itemMeta.getLore()) {
                if (loreLine.contains("Taken from player")) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean isHostileMob(LivingEntity entity) {
        EntityType entityType = entity.getType();
        return entityType.isAlive() && entityType != EntityType.PLAYER && entityType.isSpawnable();
    }

    public static boolean shouldSpawnAsHydra() {
        int chance = ThreadLocalRandom.current().nextInt(1, 11); // 10% chance
        return chance == 1;
    }

    public static boolean shouldSpawnZombieWithExtraSpeed() {
        int chance = ThreadLocalRandom.current().nextInt(1, 5); // 25% chance
        return chance == 1;
    }

    public static boolean shouldSpawnSilverfish(int spawnChance) {
        int chance = ThreadLocalRandom.current().nextInt(1, 101); // 1-100
        return chance <= spawnChance;
    }

    public static String getRandomWitherName() {
        return witherNames.get((int) (Math.random() * witherNames.size()));
    }

    public static void spawnMobs(LivingEntity target, EntityType entityType, int count) {
        World world = target.getWorld();
        Location targetLocation = target.getLocation();

        for (int i = 0; i < count; i++) {
            Location randomLocation = getRandomLocationAround(targetLocation, 5);
            world.spawnEntity(randomLocation, entityType);
        }
    }

    public static void spawnElectricCreepers(World world, Location center) {
        Location[] locations = {
                center.clone().add(20, 0, 20),
                center.clone().add(20, 0, -20),
                center.clone().add(-20, 0, 20),
                center.clone().add(-20, 0, -20)
        };

        for (Location location : locations) {
            // Spawn electric creeper
            Creeper creeper = (Creeper)world.spawnEntity(location, EntityType.CREEPER);
            creeper.setPowered(true);
        }
    }

    public static Location getRandomLocationAround(Location center, int radius) {
        double angle = Math.random() * Math.PI * 2;
        int x = (int) (center.getX() + radius * Math.cos(angle));
        int z = (int) (center.getZ() + radius * Math.sin(angle));
        int y = center.getWorld().getHighestBlockYAt(x, z);
        return new Location(center.getWorld(), x + 0.5, y + 1, z + 0.5);
    }

}
