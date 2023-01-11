package de.mxscha.en.endoflife.utils.manager.home;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.database.mysql.MySQL;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/*
 * Author: Keksgauner
 * Time: 09.01.2023
 */
public class HomeManager {

    private MySQL mySQL;

    public HomeList getHomes(Player player) {
        UUID uuid = player.getUniqueId();
        String qry = "SELECT homes FROM homes WHERE uuid=?";
        try(ResultSet rs = mySQL.query(qry, uuid.toString())) {
            if (rs.next()) {
                return new HomeList().fromJson(rs.getString("homes"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new HomeList();
    }

    public Home getHome(Player player, String home) {
        for(Home homes : getHomes(player).getList())
            if(homes.getName().equals(home.toLowerCase()))
                return homes;

        return null;
    }

    public void setHome(Player player, String home, Location location) {
        HomeList homeList = getHomes(player);
        homeList.add(new Home(home, location));

        save(player, homeList);
    }

    public void delHome(Player player, String home) {
        HomeList homeList = getHomes(player);
        homeList.remove(home);

        save(player, homeList);
    }

    public void save(Player player, HomeList homeList) {
        if (!isUserExists(player.getUniqueId())) {
            mySQL.update("INSERT INTO homes (uuid, homes) VALUES (?,?)", player.getUniqueId().toString(), homeList.toJson());
        } else {
            mySQL.update("UPDATE homes SET homes=? WHERE uuid=?", homeList.toJson(), player.getUniqueId().toString());
        }
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
        mySQL.update("CREATE TABLE IF NOT EXISTS homes (uuid VARCHAR(36) PRIMARY KEY, homes VARCHAR(6555))");
     }
}


