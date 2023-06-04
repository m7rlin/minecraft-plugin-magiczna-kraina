package pl.mgtm.magicznakraina.api.config.serializer;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pl.mgtm.magicznakraina.api.config.BukkitConfiguration;
import pl.mgtm.magicznakraina.api.config.exception.InvalidConfigFileException;

/**
 * Built-in serializer for PotionEffect
 *
 * @author Mikołaj Gałązka
 * @see PotionEffect
 * @see Serializer
 * @since 1.0
 */
public class PotionEffectSerializer extends Serializer<PotionEffect> {

    @Override
    protected void saveObject(String path, PotionEffect object, BukkitConfiguration configuration) {
        configuration.set(path + ".type", object.getType().getName());
        configuration.set(path + ".duration", object.getDuration());
        configuration.set(path + ".amplifier", object.getAmplifier());
    }

    @Override
    public PotionEffect deserialize(String path, BukkitConfiguration configuration) {
        PotionEffectType type = PotionEffectType.getByName(configuration.getString(path + ".type"));
        int duration = configuration.getInt(path + ".duration");
        int amplifier = configuration.getInt(path + ".amplifier");

        if (type == null) {
            throw new InvalidConfigFileException("Invalid PotionEffect type (path: " + path + ")");
        }

        return new PotionEffect(type, duration, amplifier);
    }
}
