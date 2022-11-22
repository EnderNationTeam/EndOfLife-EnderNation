package de.mxscha.en.endoflife.utils.manager.backpack.manager;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.database.mysql.MySQL;
import de.mxscha.en.endoflife.utils.manager.backpack.Backpack;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BackpackManager {

    private final Map<UUID, Backpack> map;
    private MySQL mySQL;

    public BackpackManager() {
        mySQL = EndoflifeCore.getInstance().getMySQL();
        map = new HashMap<>();
        load();
    }

    public Backpack getBackpack(UUID uuid) {
        if(map.containsKey(uuid)) {
            return map.get(uuid);
        }

        Backpack backpack = new Backpack(uuid, 4);
        map.put(uuid, backpack);
        return backpack;
    }

    public void setBackpack(UUID uuid, Backpack backpack) {
        map.put(uuid, backpack);
    }

    public String get(UUID uuid) {
        String qry = "SELECT items FROM backpacks WHERE uuid=?";
        try(ResultSet rs = mySQL.query(qry, uuid.toString())) {
            if (rs.next()) {
                return rs.getString("items");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void load() {
        Bukkit.getOnlinePlayers().forEach(s -> {
            UUID uuid = s.getUniqueId();
            if(get(uuid) == null)
                initPlayer(uuid);
            String base64 = get(uuid);
            try {
                map.put(uuid, new Backpack(uuid, base64, 4));
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
        map.forEach((uuid, backpack) -> save(uuid, backpack.toBase64()));
    }

    public void save(UUID uuid, String backpack) {
        String qry = "UPDATE backpacks SET items=? WHERE uuid=?";
        mySQL.update(qry, backpack, uuid.toString());
    }

    public void initPlayer(UUID uuid) {
        if (get(uuid) == null)
            mySQL.update("INSERT INTO backpacks (uuid, items) VALUES (?,?)", uuid.toString(), "rO0ABXcEAAAAJHBwcHBwcHBwcHBwcHBwcHBwcHBwcHBwcHBwcHBwcHBwcHBwcA==");
    }
}