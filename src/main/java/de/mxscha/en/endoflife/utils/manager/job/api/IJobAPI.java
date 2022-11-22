package de.mxscha.en.endoflife.utils.manager.job.api;

import de.mxscha.en.endoflife.utils.manager.job.Job;

import java.util.UUID;

public interface IJobAPI {

    String getJob(UUID uuid);
    void setJob(UUID uuid, Job job);
    void fireFromJob(UUID uuid, Job job);
}
