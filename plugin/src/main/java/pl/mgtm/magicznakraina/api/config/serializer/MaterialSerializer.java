package pl.mgtm.magicznakraina.api.config.serializer;


import org.bukkit.Material;
import pl.mgtm.magicznakraina.api.config.BukkitConfiguration;


/**
 * Built-in serializer for Material
 *
 * @author Mikołaj Gałązka
 * @see Material
 * @see Serializer
 * @since 1.1.9
 */
public class MaterialSerializer extends Serializer<Material> {

    @Override
    protected void saveObject(String path, Material object, BukkitConfiguration configuration) {
        configuration.set(path, object.toString());
    }

    @Override
    public Material deserialize(String path, BukkitConfiguration configuration) {
        return Material.valueOf(configuration.getString(path));
    }
}
