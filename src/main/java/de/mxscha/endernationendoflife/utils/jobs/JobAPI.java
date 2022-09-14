package de.mxscha.endernationendoflife.utils.jobs;

import de.mxscha.endernationendoflife.EndoflifeCore;
import de.mxscha.endernationendoflife.utils.money.MySQL.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class JobAPI implements IJobAPI{

    private MySQL mySQL;

    @Override
    public String getJob(UUID uuid) {
        String qry = "SELECT job FROM jobs WHERE uuid=?";
        try (ResultSet set = mySQL.query(qry, uuid.toString())){
            if (set.next())
                return set.getString("job");
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public void setJob(UUID uuid, Job job) {
        String qry = "UPDATE jobs SET job=? WHERE uuid=?";
        mySQL.update(qry, job.getTranslation(), uuid.toString());
    }

    @Override
    public void fireFromJob(UUID uuid, Job job) {
        setJob(uuid, Job.UNEMPLOYED);
    }

    public void initPlayer(UUID uuid) {
        mySQL.update("INSERT INTO jobs (uuid, job) VALUES (?,?)", uuid.toString(), Job.UNEMPLOYED.getTranslation());
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

    public void createTables() {
        this.mySQL = EndoflifeCore.getInstance().getMySQL();
        mySQL.update("CREATE TABLE IF NOT EXISTS jobs (uuid VARCHAR(36), job VARCHAR(36))");
    }
}
