package pl.mgtm.magicznakraina.config;

import pl.mgtm.magicznakraina.api.config.Config;
import pl.mgtm.magicznakraina.api.config.annotation.Comment;
import pl.mgtm.magicznakraina.api.config.annotation.ConfigName;

@ConfigName("test.yml")
public interface TestConfig extends Config {

    @Comment("This is a comment for the message key.")
    default String getMessage() {
        return "This is the default message";
    }

    @Comment("This is a comment for the cooldown key.")
    default int getCooldown() {
        return 10;
    }

    void setCooldown(int cooldown);
}


