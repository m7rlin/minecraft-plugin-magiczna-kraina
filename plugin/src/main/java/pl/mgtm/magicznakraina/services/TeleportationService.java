package pl.mgtm.magicznakraina.services;

import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TeleportationService {
    private Map<UUID, ArrayList<UUID>> requests = new HashMap<>();

    private ArrayList<UUID> dummyRecord = new ArrayList<>();

    public boolean checkTeleportationRequests(UUID player, UUID target) {
        Bukkit.getPlayer(player).sendMessage("§8[§9TeleportationService§8]§r checkTeleportationRequests();");

        if (requests.size() > 0) {
            return requests.get(player).contains(target);
        }

        return false;
    }

    public void createTeleporationRequest(UUID player, UUID target) {
        Bukkit.getPlayer(player).sendMessage("§8[§9TeleportationService§8]§r createTeleportationRequest();");

        requests.put(player, dummyRecord);
        ArrayList<UUID> request = requests.get(player);
        request.add(target);

        requests.replace(player, requests.get(player), request);
    };

    public void removeTeleportationRequest(UUID player, UUID target) {
        Bukkit.getPlayer(player).sendMessage("§8[§9TeleportationService§8]§r removeTeleportationRequest();");
        ArrayList<UUID> request = requests.get(player);
        request.remove(target);

        requests.replace(player, requests.get(player), request);
    };

    public void flushTeleportationRequests(UUID player) {
        requests.get(player).clear();
    };
};
