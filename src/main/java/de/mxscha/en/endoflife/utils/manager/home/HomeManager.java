package de.mxscha.en.endoflife.utils.manager.home;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.database.mysql.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class HomeManager {

    private static Player player;
    private int HomeAmount;
    MySQL mySQL;

    private String getWorld(Player player) {
        UUID uuid = player.getUniqueId();
        String qry = "SELECT world FROM homes WHERE uuid=?";
        try(ResultSet rs = mySQL.query(qry, uuid.toString())) {
            if (rs.next()) {
                return rs.getString("world");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int getX(Player player) {
        UUID uuid = player.getUniqueId();
        String qry = "SELECT x FROM homes WHERE uuid=?";
        try(ResultSet rs = mySQL.query(qry, uuid.toString())) {
            if (rs.next()) {
                return rs.getInt("x");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private int getY(Player player) {
        UUID uuid = player.getUniqueId();
        String qry = "SELECT y FROM homes WHERE uuid=?";
        try(ResultSet rs = mySQL.query(qry, uuid.toString())) {
            if (rs.next()) {
                return rs.getInt("y");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private int getZ(Player player) {
        UUID uuid = player.getUniqueId();
        String qry = "SELECT z FROM homes WHERE uuid=?";
        try(ResultSet rs = mySQL.query(qry, uuid.toString())) {
            if (rs.next()) {
                return rs.getInt("z");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public Location getHome(Player player) {
        if (isUserExists(player.getUniqueId())) {
            World world =  Bukkit.getWorld(getWorld(player));
            return new Location(world, getX(player), getY(player), getZ(player));
        } else
            return null;
    }

    public void setHome(Player player, Location location) {
        if (!(isUserExists(player.getUniqueId()))) {
            if (!isNameAlreadyInDataBase(player.getName())) {
                mySQL.update("INSERT INTO homes (uuid, name, world, x, y, z) VALUES (?,?,?,?,?,?)", player.getUniqueId().toString(), player.getName(), location.getWorld().getName(), location.getX(), location.getY(), location.getZ());
            } else {
                mySQL.update("update homes SET uuid=? WHERE name=?", player.getUniqueId().toString(), player.getName());
            }
        }
    }

    public boolean isNameAlreadyInDataBase(String playerName) {
        String qry = "SELECT count(*) AS count FROM homes WHERE name=?";
        try(ResultSet rs = mySQL.query(qry, playerName)) {
            if (rs.next()) {
                return rs.getInt("count") != 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isUserExists(UUID uuid) {
        String qry = "SELECT count(*) AS count FROM homes WHERE uuid=?";
        try(ResultSet rs = mySQL.query(qry, uuid.toString())) {
            if (rs.next()) {
                return rs.getInt("count") != 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createTables() {
        this.mySQL = EndoflifeCore.getInstance().getMySQL();
        mySQL.update("CREATE TABLE IF NOT EXISTS homes (uuid VARCHAR(36) PRIMARY KEY, name VARCHAR(36), world VARCHAR(36), x INT(35), y INT(35), z INT(35))");
     }
}


