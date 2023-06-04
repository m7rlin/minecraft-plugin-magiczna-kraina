package pl.mgtm.magicznakraina.api.config.serializer;

import org.bukkit.block.Biome;
import pl.mgtm.magicznakraina.api.config.BukkitConfiguration;


public class BiomeSerializer extends Serializer<Biome> {

    @Override
    protected void saveObject(String path, Biome object, BukkitConfiguration configuration) {
        configuration.set(path, object.toString());
    }

    @Override
    public Biome deserialize(String path, BukkitConfiguration configuration) {
        return Biome.valueOf(configuration.getString(path));
    }
}
