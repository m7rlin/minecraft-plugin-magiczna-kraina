package pl.mgtm.magicznakraina.helpers;

import org.bukkit.entity.Player;
import pl.mgtm.magicznakraina.MagicznaKraina;

import java.util.ArrayList;
import java.util.List;

public class ServerHelper {

    public ServerHelper() {
        throw new RuntimeException("Cannot initialize helper class!");
    }

    public static List<String> getOnlinePlayerNames() {
        final MagicznaKraina pl = MagicznaKraina.getInstance();
        List<String> playerNames = new ArrayList<>();

        // Get online players
        for (Player player : pl.getServer().getOnlinePlayers()) {
            playerNames.add(player.getName());
        }

        return playerNames;
    }

}
