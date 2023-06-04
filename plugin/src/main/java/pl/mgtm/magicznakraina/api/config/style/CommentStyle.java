package pl.mgtm.magicznakraina.api.config.style;

/**
 * Allows to select how comments will look in config's file
 *
 * @author Mikołaj Gałązka
 * @since 1.0
 */
public enum CommentStyle {
    /**
     * With INLINE comments will look like this:
     * <code>
     * foo: "bar" // It's example comment
     * </code>
     */
    INLINE,

    /**
     * With ABOVE_CONTENT comments will look like this:
     * <code>
     * // It's example comment
     * foo: "bar"
     * </code>
     */
    ABOVE_CONTENT
}
