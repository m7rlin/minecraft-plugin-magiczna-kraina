package pl.mgtm.magicznakraina.config;

import pl.mgtm.magicznakraina.api.config.Config;
import pl.mgtm.magicznakraina.api.config.annotation.Comment;
import pl.mgtm.magicznakraina.api.config.annotation.ConfigName;

@ConfigName("merlin.yml")
public interface MainConfig extends Config {

    /*
    @Comment("API key")
    default String getApiKey() {
        return "YOUR_API_KEY_HERE";
    }

    @Comment("API server url")
    default String getApiUrl() {
        return "https://api.example.com/";
    }
    */

    default SerduszkoModuleConfig getSerduszkoModule() {
        return new SerduszkoModuleConfig(true, true);
    }

    default CleverSleepModuleConfig getCleverSleepModule() {
        return new CleverSleepModuleConfig(true, 50);
    }

    default WelcomeModuleConfig getWelcomeModule() {
        return new WelcomeModuleConfig();
    }

}


