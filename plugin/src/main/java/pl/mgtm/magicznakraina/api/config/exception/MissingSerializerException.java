package pl.mgtm.magicznakraina.api.config.exception;

public class MissingSerializerException extends InvalidConfigException {

    public MissingSerializerException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public MissingSerializerException(Class<?> clazz) {
        super("Serializer for " + clazz + " does not exists. Did you forget to register it?");
    }

    public MissingSerializerException(String clazz) {
        super("Class " + clazz + " does not exists, can't get Serializer of it");
    }

    public MissingSerializerException(Object object) {
        this(object.getClass());
    }
}