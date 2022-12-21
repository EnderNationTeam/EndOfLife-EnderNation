package de.mxscha.en.endoflife.utils.scoreboard.manager.money.api;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.database.mysql.MySQL;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class MoneyAPI implements IMoneyAPI {

    private MySQL mySQL;

    @Override
    public Integer getMoney(Player player) {
        UUID uuid = player.getUniqueId();
        String qry = "SELECT money FROM money WHERE uuid=?";
        try(ResultSet rs = mySQL.query(qry, uuid.toString())) {
            if (rs.next()) {
                return rs.getInt("money");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initPlayer(player);
        return -1;
    }

    @Override
    public void setMoney(Player player, int amount) {
        UUID uuid = player.getUniqueId();
        String qry = "UPDATE money SET money=? WHERE uuid=?";
        mySQL.update(qry, amount, uuid.toString());
    }

    @Override
    public void removeMoney(Player player, int amount) {
        if (getMoney(player) >= amount) {
            setMoney(player, getMoney(player)-amount);
        } else {
            setMoney(player, 0);
        }
    }

    @Override
    public void addMoney(Player player, int amount) {
        setMoney(player, getMoney(player)+amount);
    }

    public void initPlayer(Player player) {
        if (!(isUserExists(player.getUniqueId()))) {
            if (!isNameAlreadyInDataBase(player.getName())) {
                mySQL.update("INSERT INTO money (uuid, name, money) VALUES (?,?,?)", player.getUniqueId().toString(), player.getName(), 0);
            } else {
                mySQL.update("update money SET uuid=? WHERE name=?", player.getUniqueId().toString(), player.getName());
            }
        }
    }

    public boolean isNameAlreadyInDataBase(String playerName) {
        String qry = "SELECT count(*) AS count FROM money WHERE name=?";
        try(ResultSet rs = mySQL.query(qry, playerName)) {
            if (rs.next()) {
                return rs.getInt("count") != 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isUserExists(UUID uuid) {
        String qry = "SELECT count(*) AS count FROM money WHERE uuid=?";
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
        mySQL.update("CREATE TABLE IF NOT EXISTS money (uuid VARCHAR(36) PRIMARY KEY, name VARCHAR(36), money INT(35))");
    }
}