package de.mxscha.en.endoflife.utils.scoreboard.manager.job.api;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.database.mysql.MySQL;
import de.mxscha.en.endoflife.utils.scoreboard.manager.job.Job;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class JobAPI implements IJobAPI{

    private MySQL mySQL;
    private final int firstMaxXPFromJob = 200;

    @Override
    public String getJob(Player player) {
        UUID uuid = player.getUniqueId();
        String qry = "SELECT job FROM jobs WHERE uuid=?";
        try (ResultSet set = mySQL.query(qry, uuid.toString())){
            if (set.next())
                return set.getString("job");
        } catch (Exception ignored) {

        }
        return null;
    }

    @Override
    public void setJob(Player player, Job job) {
        UUID uuid = player.getUniqueId();
        String qry = "UPDATE jobs SET job=? WHERE uuid=?";
        mySQL.update(qry, job.getTranslation(), uuid.toString());
        initPlayer(player);
    }

    public boolean hasJob(Player player) {
        UUID uuid = player.getUniqueId();
        String qry = "SELECT count(*) AS count FROM jobs WHERE uuid=?";
        try(ResultSet rs = mySQL.query(qry, uuid.toString())) {
            if (rs.next()) {
                return rs.getInt("count") != 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void fireFromJob(Player player, Job job) {
        setJob(player, Job.UNEMPLOYED);
    }

    // ------------------------------------------------------------------------
    // Job XP & Level

    public int getJobLevel(Player player) {
        if (hasJob(player)) {
            UUID uuid = player.getUniqueId();
            String qry = "SELECT level FROM jobinfo WHERE uuid=?";
            try (ResultSet set = mySQL.query(qry, uuid.toString())){
                if (set.next())
                    return set.getInt("level");
            } catch (Exception ignored) {

            }
        }
        return -1;
    }

    public int getJobXP(Player player) {
        if (hasJob(player)) {
            UUID uuid = player.getUniqueId();
            String qry = "SELECT xp FROM jobinfo WHERE uuid=?";
            try (ResultSet set = mySQL.query(qry, uuid.toString())){
                if (set.next())
                    return set.getInt("xp");
            } catch (Exception ignored) {

            }
        }
        return -1;
    }

    public int getJobMaxXP(Player player) {
        if (hasJob(player)) {
            UUID uuid = player.getUniqueId();
            String qry = "SELECT maxxp FROM jobinfo WHERE uuid=?";
            try (ResultSet set = mySQL.query(qry, uuid.toString())){
                if (set.next())
                    return set.getInt("maxxp");
            } catch (Exception ignored) {

            }
        }
        return -1;
    }

    public void addXP(Player player, int xp) {
        setXP(player, getJobXP(player)+xp);
    }

    public void setXP(Player player, int xp) {
        UUID uuid = player.getUniqueId();
        String qry = "UPDATE jobinfo SET xp=? WHERE uuid=?";
        mySQL.update(qry, xp, uuid.toString());
    }

    public void levelUp(Player player) {
        if (getJobXP(player) > getJobMaxXP(player)) {
            int overXP = getJobXP(player)-getJobMaxXP(player);
            setXP(player, overXP);
        } else
            setXP(player, 0);
        setLevel(player, getJobLevel(player)+1);
    }

    public void setLevel(Player player, int level) {
        UUID uuid = player.getUniqueId();
        String qry = "UPDATE jobinfo SET level=? WHERE uuid=?";
        mySQL.update(qry, level, uuid.toString());
    }

    public void setMaxXP(Player player, int maxXP) {
        UUID uuid = player.getUniqueId();
        String qry = "UPDATE jobinfo SET maxxp=? WHERE uuid=?";
        mySQL.update(qry, maxXP, uuid.toString());
    }

    public void initPlayer(Player player) {
        if (!(isUserExists(player.getUniqueId()))) {
            if (!isNameAlreadyInDataBase(player.getName())) {
                mySQL.update("INSERT INTO jobs (uuid, name, job) VALUES (?,?,?)", player.getUniqueId().toString(), player.getName(), Job.UNEMPLOYED.getTranslation());
                mySQL.update("INSERT INTO jobinfo (uuid, name, level, xp, maxxp) VALUES (?,?,?,?,?)", player.getUniqueId().toString(), player.getName(), 1, 0, firstMaxXPFromJob);
            } else {
                mySQL.update("update jobs SET uuid=? WHERE name=?", player.getUniqueId().toString(), player.getName());
                mySQL.update("update jobinfo SET uuid=? WHERE name=?", player.getUniqueId().toString(), player.getName());
            }
        }
    }

    public boolean isNameAlreadyInDataBase(String playerName) {
        String qry = "SELECT count(*) AS count FROM jobs WHERE name=?";
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
        String qry = "SELECT count(*) AS count FROM jobs WHERE uuid=?";
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
        mySQL.update("CREATE TABLE IF NOT EXISTS jobs (uuid VARCHAR(36) PRIMARY KEY, name VARCHAR(36), job VARCHAR(36))");
        mySQL.update("CREATE TABLE IF NOT EXISTS jobinfo (uuid VARCHAR(36) PRIMARY KEY, name VARCHAR(36), level INT(35), xp INT(35), maxxp INT(35))");
    }
}
