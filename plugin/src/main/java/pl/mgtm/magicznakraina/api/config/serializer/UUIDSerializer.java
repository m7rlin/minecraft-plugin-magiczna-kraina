package pl.mgtm.magicznakraina.api.config.serializer;

import pl.mgtm.magicznakraina.api.config.BukkitConfiguration;

import java.util.UUID;

/**
 * Built-in serializer for UUID
 *
 * @author Mikołaj Gałązka
 * @see UUID
 * @see Serializer
 * @since 1.0
 */
public class UUIDSerializer extends Serializer<UUID> {

    @Override
    protected void saveObject(String path, UUID object, BukkitConfiguration configuration) {
        configuration.set(path, object.toString());
    }

    @Override
    public UUID deserialize(String path, BukkitConfiguration configuration) {
        return UUID.fromString(configuration.getString(path));
    }
}
