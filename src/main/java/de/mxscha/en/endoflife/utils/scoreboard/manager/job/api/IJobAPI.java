package de.mxscha.en.endoflife.utils.scoreboard.manager.job.api;

import de.mxscha.en.endoflife.utils.scoreboard.manager.job.Job;
import org.bukkit.entity.Player;

public interface IJobAPI {

    String getJob(Player player);
    void setJob(Player player, Job job);
    void fireFromJob(Player player, Job job);
}
