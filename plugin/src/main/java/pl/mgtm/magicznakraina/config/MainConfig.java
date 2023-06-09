package pl.mgtm.magicznakraina.config;

import pl.mgtm.magicznakraina.api.config.Config;
import pl.mgtm.magicznakraina.api.config.annotation.Comment;
import pl.mgtm.magicznakraina.api.config.annotation.ConfigName;

@ConfigName("merlin.yml")
public interface MainConfig extends Config {

    default boolean getDebug() {
        return false;
    }

    public void setDebug(boolean debug);

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

    void setSerduszkoModule(SerduszkoModuleConfig serduszkoModuleConfig);

    default CleverSleepModuleConfig getCleverSleepModule() {
        return new CleverSleepModuleConfig(true, 50);
    }

    void setCleverSleepModule(CleverSleepModuleConfig cleverSleepModuleConfig);
    default WelcomeModuleConfig getWelcomeModule() {
        return new WelcomeModuleConfig(true);
    }

    void setWelcomeModule(WelcomeModuleConfig welcomeModuleConfig);
    default SpawnModuleConfig getSpawnModule() {
        return new SpawnModuleConfig(true);
    }

    void setSpawnModule(SpawnModuleConfig spawnModuleConfig);

    default HomeModuleConfig getHomeModule() {
        return new HomeModuleConfig(true);
    }

    void setHomeModule(HomeModuleConfig homeModuleConfig);
    default KitsModuleConfig getKitsModule() {
        return new KitsModuleConfig(true);
    }

    void setKitsModule(KitsModuleConfig kitsModuleConfig);

}


