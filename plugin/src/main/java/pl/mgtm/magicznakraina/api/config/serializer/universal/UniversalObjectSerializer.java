package pl.mgtm.magicznakraina.api.config.serializer.universal;

import pl.mgtm.magicznakraina.api.config.BukkitConfiguration;
import pl.mgtm.magicznakraina.api.config.exception.InvalidConfigException;
import pl.mgtm.magicznakraina.api.config.exception.MissingSerializerException;
import pl.mgtm.magicznakraina.api.config.serializer.Serializer;
import pl.mgtm.magicznakraina.api.config.serializer.Serializers;
import pl.mgtm.magicznakraina.api.config.util.TypeUtils;


import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Helper built-in serializer for custom objects which implemenet Serializable interface.
 * It uses reflections to serialize all fields from given Object, which are not transient and static.
 * Class must have default constructor (no-args).
 *
 * @author Mikołaj Gałązka
 * @see Serializer
 * @see Serializable
 * @since 1.1.7
 */
public class UniversalObjectSerializer extends Serializer<Serializable> {

    @Override
    protected void saveObject(String path, Serializable object, BukkitConfiguration configuration) {
        this.validateDefaultConstructor(object.getClass());

        try {
            for (Field field : object.getClass().getDeclaredFields()) {
                if (Modifier.isStatic(field.getModifiers()) || Modifier.isTransient(field.getModifiers())) {
                    continue;
                }

                field.setAccessible(true);
                Object value = field.get(object);

                try {
                    if (TypeUtils.isSimpleType(field.getType())) {
                        configuration.set(path + "." + configuration.getNameStyle().format(field.getName()), value);
                    } else {
                        Serializer<?> serializer = Serializers.of(field.getType());
                        if (serializer == null) {
                            throw new MissingSerializerException(field.getType());
                        }

                        serializer.serialize(path + "." + configuration.getNameStyle().format(field.getName()), value, configuration);
                    }
                } catch (Exception e) {
                    throw new RuntimeException("An error occurred while serializing field '" + field.getName() + "' from class '" + object.getClass().getName() + "'", e);
                }
            }

            configuration.set(path + "." + configuration.getNameStyle().format("type"), object.getClass().getName());
        } catch (IllegalAccessException e) {
            throw new RuntimeException("An error occurred while serializing class '" + object.getClass().getName() + "'", e);
        }
    }

    @Override
    public Serializable deserialize(String path, BukkitConfiguration configuration) {
        String classPath = configuration.getString(path + ".type");
        Class<?> clazz;

        try {
            clazz = Class.forName(classPath);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("An error occurred while deserializing class '" + classPath + "'", e);
        }

        this.validateDefaultConstructor(clazz);
        if (!Serializable.class.isAssignableFrom(clazz)) {
            throw new RuntimeException("Class " + classPath + " does not implements Serializable");
        }

        Serializable instance;
        try {
            instance = (Serializable) clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Could not create instance of class (" + classPath + ") with default constructor", e);
        }

        try {
            for (Field field : clazz.getDeclaredFields()) {
                if (Modifier.isStatic(field.getModifiers()) || Modifier.isTransient(field.getModifiers())) {
                    continue;
                }

                field.setAccessible(true);

                Class<?> type = field.getType();
                if (TypeUtils.isSimpleType(type)) {
                    field.set(instance, configuration.get(path + "." + configuration.getNameStyle().format(field.getName())));
                } else {
                    Serializer<?> serializer = Serializers.of(field.getType());
                    if (serializer == null) {
                        throw new MissingSerializerException(field.getType());
                    }

                    field.set(instance, serializer.deserialize(path + "." + configuration.getNameStyle().format(field.getName()), configuration));
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Could not deserialize " + classPath, e);
        }

        return instance;
    }

    private void validateDefaultConstructor(Class<?> clazz) {
        try {
            clazz.getConstructor(); // Get no-args constructor for test
        } catch (NoSuchMethodException e) {
            throw new InvalidConfigException("Class " + clazz.getName() + " does not have a default constructor");
        }
    }
}
