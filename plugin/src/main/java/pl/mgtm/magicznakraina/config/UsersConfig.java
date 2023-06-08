package pl.mgtm.magicznakraina.config;


import pl.mgtm.magicznakraina.api.config.Config;
import pl.mgtm.magicznakraina.api.config.annotation.ConfigName;
import pl.mgtm.magicznakraina.api.config.annotation.ConfigOptional;

import java.util.HashMap;
import java.util.Map;

@ConfigName("users.yml")
public interface UsersConfig extends Config {

    default String getTest() {
        return "merlin is here";
    }

   @ConfigOptional
    default HashMap<String, User> getUsers() {
       return null;
    }

    public void setUsers(HashMap<String, User> users);

}