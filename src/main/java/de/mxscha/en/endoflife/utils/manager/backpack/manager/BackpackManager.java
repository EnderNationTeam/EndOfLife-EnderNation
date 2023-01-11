package de.mxscha.en.endoflife.utils.manager.backpack.manager;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.database.mysql.MySQL;
import de.mxscha.en.endoflife.utils.manager.backpack.Backpack;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BackpackManager {

    private final Map<UUID, Backpack> backpacks;
    private final MySQL mySQL;

    public BackpackManager() {
        mySQL = EndoflifeCore.getInstance().getMySQL();
        backpacks = new HashMap<>();
        loadAll();
    }

    public Backpack getBackpack(UUID uuid) {
        if(backpacks.containsKey(uuid)) {
            return backpacks.get(uuid);
        }
        load(uuid, getBase64(uuid));
        return getBackpack(uuid);
    }

    public void setBackpack(UUID uuid, Backpack backpack) {
        backpacks.putIfAbsent(uuid, backpack);
    }

    public String getBase64(UUID uuid) {
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

    public void load(UUID uuid, String base64) {
        Backpack backpack = new Backpack(uuid, base64, 4);
        backpacks.putIfAbsent(uuid, backpack);
        EndoflifeCore.getInstance().getLogger().info("Backpack von " + uuid.toString() + " wurde geladen!");
    }

    public void loadAll() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            initPlayer(player);
            load(player.getUniqueId(), getBase64(player.getUniqueId()));
        });
    }

    public void save(UUID uuid) {
        String qry = "UPDATE backpacks SET items=? WHERE uuid=?";
        mySQL.update(qry, getBase64(uuid), uuid.toString());
    }

    public void save(UUID uuid, String base64) {
        String qry = "UPDATE backpacks SET items=? WHERE uuid=?";
        mySQL.update(qry, base64, uuid.toString());
    }

    public void saveAll() {
        backpacks.forEach((uuid, backpack) -> save(uuid, backpack.toBase64()));
    }

    public void autoDelete(UUID uuid) {
        BukkitTask oldTask = backpacks.get(uuid).getTask();
        if(oldTask != null && oldTask.isCancelled()) {
            oldTask.cancel();
        }

        BukkitTask task = new BukkitRunnable() {
            @Override
            public void run() {
                saveAndDelete(uuid);
            }
        }.runTaskLater(EndoflifeCore.getInstance(), 20 * 120);
        backpacks.get(uuid).setTask(task);
    }

    public void saveAndDelete(UUID uuid) {
        if(backpacks.containsKey(uuid)) {
            save(uuid, backpacks.get(uuid).toBase64());
            backpacks.remove(uuid);
            EndoflifeCore.getInstance().getLogger().info("Backpack von " + uuid.toString() + " wurde endladen!");
        }
    }

    private boolean isUserExists(UUID uuid) {
        String qry = "SELECT count(*) AS count FROM backpacks WHERE uuid=?";
        try(ResultSet rs = mySQL.query(qry, uuid.toString())) {
            if (rs.next()) {
                return rs.getInt("count") != 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isPlayerExist(UUID uuid) {
        return backpacks.containsKey(uuid);
    }

    public void initPlayer(Player player) {
        if (!isUserExists(player.getUniqueId())) {
            mySQL.update("INSERT INTO backpacks(uuid, items) VALUES (?, ?)", player.getUniqueId().toString(), "rO0ABXcEAAAAJHBwcHBwcHBwcHBwcHBwcHBwcHBwcHBwcHBwcHBwcHBwcHBwcA==");
        }
    }
}