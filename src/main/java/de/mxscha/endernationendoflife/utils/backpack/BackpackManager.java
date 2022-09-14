package de.mxscha.endernationendoflife.utils.backpack;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;
import java.util.*;

public class BackpackManager {

    private final Map<UUID, Backpack> map;
    FileConfiguration config = BackpackConfig.getConfig();

    public BackpackManager() {
        map = new HashMap<>();

        load();
    }

    public Backpack getBackpack(UUID uuid) {
        if(map.containsKey(uuid)) {
            return map.get(uuid);
        }

        Backpack backpack = new Backpack(uuid, 6);
        map.put(uuid, backpack);
        return backpack;
    }

    public void setBackpack(UUID uuid, Backpack backpack) {
        map.put(uuid, backpack);
    }

    private void load() {
       config.getStringList("backpacks");
        List<String> uuids = config.getStringList("backpacks");
        uuids.forEach(s -> {
            UUID uuid = UUID.fromString(s);
            String base64 = config.getString("backpack." + s);

            try {
                map.put(uuid, new Backpack(uuid, base64, 6));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void save() {
        List<String> uuids = new ArrayList<>();

        for (UUID uuid : map.keySet()) {
            uuids.add(uuid.toString());
        }

        config.set("backpacks", uuids);
        map.forEach((uuid, backpack) -> config.set("backpack." + uuid.toString(), backpack.toBase64()));
    }
}