package de.mxscha.en.endoflife.utils.manager.job.api;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.database.mysql.MySQL;
import de.mxscha.en.endoflife.utils.manager.job.Job;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class JobAPI implements IJobAPI{

    private MySQL mySQL;
    private final int firstMaxXPFromJob = 200;

    @Override
    public String getJob(UUID uuid) {
        String qry = "SELECT job FROM jobs WHERE uuid=?";
        try (ResultSet set = mySQL.query(qry, uuid.toString())){
            if (set.next())
                return set.getString("job");
        } catch (Exception ignored) {

        }
        return null;
    }

    @Override
    public void setJob(UUID uuid, Job job) {
        String qry = "UPDATE jobs SET job=? WHERE uuid=?";
        mySQL.update(qry, job.getTranslation(), uuid.toString());
    }

    public boolean hasJob(UUID uuid) {
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
    public void fireFromJob(UUID uuid, Job job) {
        setJob(uuid, Job.UNEMPLOYED);
    }

    // ------------------------------------------------------------------------
    // Job XP & Level

    public int getJobLevel(UUID uuid) {
        if (hasJob(uuid)) {
            String qry = "SELECT level FROM jobinfo WHERE uuid=?";
            try (ResultSet set = mySQL.query(qry, uuid.toString())){
                if (set.next())
                    return set.getInt("level");
            } catch (Exception ignored) {

            }
        }
        return -1;
    }

    public int getJobXP(UUID uuid) {
        if (hasJob(uuid)) {
            String qry = "SELECT xp FROM jobinfo WHERE uuid=?";
            try (ResultSet set = mySQL.query(qry, uuid.toString())){
                if (set.next())
                    return set.getInt("xp");
            } catch (Exception ignored) {

            }
        }
        return -1;
    }

    public int getJobMaxXP(UUID uuid) {
        if (hasJob(uuid)) {
            String qry = "SELECT maxxp FROM jobinfo WHERE uuid=?";
            try (ResultSet set = mySQL.query(qry, uuid.toString())){
                if (set.next())
                    return set.getInt("maxxp");
            } catch (Exception ignored) {

            }
        }
        return -1;
    }

    public void addXP(UUID uuid, int xp) {
        setXP(uuid, getJobXP(uuid)+xp);
    }

    public void setXP(UUID uuid, int xp) {
        String qry = "UPDATE jobinfo SET xp=? WHERE uuid=?";
        mySQL.update(qry, xp, uuid.toString());
    }

    public void levelUp(UUID uuid) {
        if (getJobXP(uuid) > getJobMaxXP(uuid)) {
            int overXP = getJobXP(uuid)-getJobMaxXP(uuid);
            setXP(uuid, overXP);
        } else
            setXP(uuid, 0);
        setLevel(uuid, getJobLevel(uuid)+1);
    }

    public void setLevel(UUID uuid, int level) {
        String qry = "UPDATE jobinfo SET level=? WHERE uuid=?";
        mySQL.update(qry, level, uuid.toString());
    }

    public void setMaxXP(UUID uuid, int maxXP) {
        String qry = "UPDATE jobinfo SET maxxp=? WHERE uuid=?";
        mySQL.update(qry, maxXP, uuid.toString());
    }

    public void initPlayer(UUID uuid) {
        mySQL.update("INSERT INTO jobs (uuid, job) VALUES (?,?)", uuid.toString(), Job.UNEMPLOYED.getTranslation());
        mySQL.update("INSERT INTO jobinfo (uuid, player, level, xp, maxxp) VALUES (?,?,?,?,?)", uuid.toString(), Bukkit.getPlayer(uuid).getName(), 0, 0, firstMaxXPFromJob);
    }

    public void createTables() {
        this.mySQL = EndoflifeCore.getInstance().getMySQL();
        mySQL.update("CREATE TABLE IF NOT EXISTS jobs (uuid VARCHAR(36) PRIMARY KEY, job VARCHAR(36))");
        mySQL.update("CREATE TABLE IF NOT EXISTS jobinfo (uuid VARCHAR(36) PRIMARY KEY, player VARCHAR(36), level INT(35), xp INT(35), maxxp INT(35))");
    }
}
