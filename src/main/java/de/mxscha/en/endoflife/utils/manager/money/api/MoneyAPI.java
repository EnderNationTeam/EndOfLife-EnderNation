package de.mxscha.en.endoflife.utils.manager.money.api;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.database.mysql.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class MoneyAPI implements IMoneyAPI {

    private MySQL mySQL;

    @Override
    public Integer getMoney(UUID uuid) {
        String qry = "SELECT money FROM money WHERE uuid=?";
        try(ResultSet rs = mySQL.query(qry, uuid.toString())) {
            if (rs.next()) {
                return rs.getInt("money");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initPlayer(uuid);
        return -1;
    }

    @Override
    public void setMoney(UUID uuid, int amount) {
        String qry = "UPDATE money SET money=? WHERE uuid=?";
        mySQL.update(qry, amount, uuid.toString());
    }

    @Override
    public void removeMoney(UUID uuid, int amount) {
        String qry = "UPDATE money SET money=? WHERE uuid=?";
        if (getMoney(uuid) >= amount) {
            setMoney(uuid, getMoney(uuid)-amount);
        } else {
            setMoney(uuid, 0);
        }
    }

    @Override
    public void addMoney(UUID uuid, int amount) {
        int currentCoins = getMoney(uuid);
        String qry = "UPDATE money SET money=? WHERE uuid=?";
        setMoney(uuid, getMoney(uuid)+amount);
    }

    public void initPlayer(UUID uuid) {
        mySQL.update("INSERT INTO money (uuid, money) VALUES (?,?)", uuid.toString(), 0);
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
        mySQL.update("CREATE TABLE IF NOT EXISTS money (uuid VARCHAR(36) PRIMARY KEY, money INT(35))");
    }
}