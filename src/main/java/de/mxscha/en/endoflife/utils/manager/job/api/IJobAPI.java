package de.mxscha.en.endoflife.utils.manager.job.api;

import de.mxscha.en.endoflife.utils.manager.job.Job;
import org.bukkit.entity.Player;

public interface IJobAPI {

    String getJob(Player player);
    void setJob(Player player, Job job);
    void fireFromJob(Player player, Job job);
}
