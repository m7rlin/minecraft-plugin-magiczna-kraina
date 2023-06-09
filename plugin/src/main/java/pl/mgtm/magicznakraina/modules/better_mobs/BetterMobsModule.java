package pl.mgtm.magicznakraina.modules.better_mobs;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.module.ModuleInfo;
import pl.mgtm.magicznakraina.module.PluginModule;
import pl.mgtm.magicznakraina.modules.better_mobs.events.*;

import java.util.concurrent.ThreadLocalRandom;

@ModuleInfo(name = "better_mobs")
public class BetterMobsModule extends PluginModule {

    private final MagicznaKraina pl = MagicznaKraina.getInstance();


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

}
