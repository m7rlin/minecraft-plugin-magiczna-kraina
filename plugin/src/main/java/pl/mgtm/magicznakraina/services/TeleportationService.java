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
        if (requests.size() > 0) {
            if (requests.get(player) != null) {
                return requests.get(player).contains(target);
            }
        }

        return false;
    }

    public void createTeleporationRequest(UUID player, UUID target) {
        requests.put(player, dummyRecord);
        ArrayList<UUID> request = requests.get(player);
        request.add(target);

        requests.replace(player, requests.get(player), request);
    };

    public void removeTeleportationRequest(UUID player, UUID target) {
        ArrayList<UUID> request = requests.get(player);
        request.remove(target);

        requests.replace(player, requests.get(player), request);
    };

    public void flushTeleportationRequests(UUID player) {
        if (requests.size() > 0) {
            if (requests.get(player) != null) {
                requests.get(player).clear();
            }
        }
    };
};
