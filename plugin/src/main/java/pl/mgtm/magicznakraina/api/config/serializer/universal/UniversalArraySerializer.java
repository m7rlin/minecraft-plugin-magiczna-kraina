package pl.mgtm.magicznakraina.api.config.serializer.universal;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import pl.mgtm.magicznakraina.MagicznaKraina;
import pl.mgtm.magicznakraina.api.config.BukkitConfiguration;
import pl.mgtm.magicznakraina.api.config.exception.InvalidConfigFileException;
import pl.mgtm.magicznakraina.api.config.exception.MissingSerializerException;
import pl.mgtm.magicznakraina.api.config.serializer.Serializer;
import pl.mgtm.magicznakraina.api.config.serializer.Serializers;
import pl.mgtm.magicznakraina.api.config.util.ConversionUtils;
import pl.mgtm.magicznakraina.api.config.util.TypeUtils;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class UniversalArraySerializer extends Serializer<Object[]> {

    @Override
    protected void saveObject(String path, Object[] object, BukkitConfiguration configuration) {
        if (object.length == 0) {
            throw new IllegalStateException("Can't set empty array to config");
        }

        Class<?> generic = TypeUtils.getArrayGeneric(object);
        boolean simple = TypeUtils.isSimpleType(generic);

        Serializer<?> serializer = simple ? null : Serializers.of(generic);
        if (!simple && serializer == null) {
            throw new MissingSerializerException(generic);
        }

        if (MagicznaKraina.ConfigAPIDebug) {
            Bukkit.getLogger().info("ARRAY SERIALIZATION ==========================");
            Bukkit.getLogger().info(object.getClass().getName() + " " + object.toString());
            Bukkit.getLogger().info("===============================================");
        }

        configuration.set(path + ".type", generic.getName());

        int index = 0;
        for (Object element : object) {
            if (simple) {
                configuration.set(path + "." + index, element);
            } else {
                serializer.serialize(path + "." + index, element, configuration);
            }

            index++;
        }
    }

    @Override
    public Object[] deserialize(String path, BukkitConfiguration configuration) {
        ConfigurationSection section = configuration.getConfigurationSection(path);
        Set<String> keys = section.getKeys(false);
        keys.stream()
                .filter(key -> !key.equals("type"))
                .forEach(key -> {
                    if (ConversionUtils.asInt(key) == Integer.MIN_VALUE) {
                        throw new InvalidConfigFileException("Invalid index: " + key + " in " + path + " (should be integer)");
                    }
                });

        String type = section.getString("type");
        Objects.requireNonNull(type, "Serializer type is not defined for " + path);

        try {
            Class<?> typeClass = Class.forName(type);
            boolean simple = TypeUtils.isSimpleType(typeClass);

            Serializer<?> serializer = simple ? null : Serializers.of(typeClass);
            if (!simple && serializer == null) {
                throw new MissingSerializerException(type);
            }

            int length = Collections.max(keys
                    .stream()
                    .filter(key -> !key.equals("type"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList())) + 1;

            Object[] array = (Object[]) Array.newInstance(typeClass, length);
            for (String key : keys) {
                if (key.equals("type")) {
                    continue;
                }

                int index = Integer.parseInt(key);
                if (simple) {
                    array[index] = configuration.get(path + "." + index);
                    continue;
                }

                array[index] = serializer.deserialize(path + "." + index, configuration);
            }

            return array;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
